package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.BaseEntityBean;
import co.za.fnb.volunteers.dto.donation.ExternalServicesDto;
import co.za.fnb.volunteers.dto.donation.DonationItemDto;
import co.za.fnb.volunteers.dto.donation.ProductDto;
import co.za.fnb.volunteers.dto.external.CategoryRequestDto;
import co.za.fnb.volunteers.dto.external.CategoryResponseDto;
import co.za.fnb.volunteers.dto.external.DropOffRequestDto;
import co.za.fnb.volunteers.dto.external.DropOffResponseDto;
import co.za.fnb.volunteers.model.Category;
import co.za.fnb.volunteers.model.Donation;
import co.za.fnb.volunteers.model.DonationItem;
import co.za.fnb.volunteers.model.Volunteer;
import co.za.fnb.volunteers.service.common.DonationNamedQueryConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.stream.JsonParser;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Stateless
@RolesAllowed({"admin","user"})
public class DonationBean extends BaseEntityBean<Donation> implements DonationLocal {

    final static Logger LOG = Logger.getLogger(DonationBean.class);

    @EJB
    private ExternalServiceLocal externalServiceLocal;

    @EJB
    private Donation_ItemLocal donation_itemLocal;

    @EJB
    private CategoryLocal categoryLocal;

    public DonationBean() {
        super(Donation.class);
    }

    @Override
    public ExternalServicesDto getExternalServiceData() {
        LOG.debug("Get External Service Data");
        return findExternalServiceData();
    }

    @Override
    public void saveDonationForUser(Volunteer volunteer, DonationItemDto donationItems) {
        Donation donation = new Donation(volunteer);

        //Save Category
        List<Category> categories = new ArrayList<>();
        ProductDto[] productDtos = donationItems.getProducts();
        for(ProductDto prodDto : productDtos){
            Category category = new Category(prodDto.getDescription(), prodDto.getMax_units(), prodDto.getCountry());
            categoryLocal.persist(category);
            categories.add(category);
        }

        //Save Donation Item
        DonationItem donationItem = new DonationItem(donation, categories, productDtos.length, donationItems.getDrop_off_point(), donationItems.getComment());
        donation.addDonationItems(donationItem);
        donation_itemLocal.persist(donationItem);

        //Save Donation
        this.persist(donation);
        System.out.println(donationItems);
        LOG.debugf("Comment(%s)", donationItems.getComment());
        LOG.debugf("Drop Off Point(%s)", donationItems.getDrop_off_point());
        LOG.debugf("Items(%s)", donationItems.getProducts().length);
    }

    @Override
    public List<DonationItemDto> getDonationItems(String userId) {
        //TODO : Use a better queries to fetch all donations
        final Donation donation = findDonation(userId);
        List<DonationItemDto> itemDtos = new ArrayList<>();
        List<DonationItem> items = donation.getDonationItems();
        for (int i = 0; i < items.size(); i++) {
            final DonationItem donationItem = items.get(i);
            final ProductDto[] productDtos = new ProductDto[donationItem.getProducts().size()];
            for(int j = 0; j < productDtos.length; i++){
                Category category  = donationItem.getProducts().get(j);
                ProductDto productDto = new ProductDto();
                productDto.setProductId(category.getId());
                productDto.setDescription(category.getDescription());
                productDto.setMax_units(category.getMaxUnits());
                productDto.setCountry(category.getCountry());
            }
            final DonationItemDto donationItemDto =
                    new DonationItemDto(donationItem.getId(), donationItem.getQuantity(), donationItem.getDrop_off_point(), donationItem.getComment(), productDtos, donationItem.getDonation().getId());
            itemDtos.add(donationItemDto);
        }

        String json = new Gson().toJson(itemDtos);
        LOG.debug(json);

        return itemDtos;
    }

    private Donation findDonation(String userId) {
        LOG.debugf("findDonation(%s)", userId);

        return Donation.class.cast(
                createNamedQuery(DonationNamedQueryConstants.FIND_DONATION_BY_USER_ID).
                setParameter("userId", userId).
                getSingleResult());
    }

    private DonationItem createNewItem(DonationItemDto donationItemDto) {
        LOG.debugf("createNewItem(%s)", donationItemDto.toString());
        final DonationItem donationItem = new DonationItem(
                null,
                null,
                donationItemDto.getQuantity(),
                donationItemDto.getDrop_off_point(),
                donationItemDto.getComment()
        );
        donation_itemLocal.persist(donationItem);
        return donationItem;
    }



    private ExternalServicesDto findExternalServiceData() {
        List<CategoryResponseDto> categories = getCategories();
        List<DropOffResponseDto> dropOffPoints = getDropOffPoints();
        ExternalServicesDto donationDto = new ExternalServicesDto(categories, dropOffPoints);
        return donationDto;
    }

    private List<CategoryResponseDto> getCategories() {
        List<CategoryRequestDto> requests = new ArrayList<CategoryRequestDto>();
        CategoryRequestDto input = new CategoryRequestDto();
        input.setId("1");
        requests.add(input);
        input = new CategoryRequestDto();
        input.setId("2");
        requests.add(input);
        input = new CategoryRequestDto();
        input.setId("3");
        requests.add(input);
        input = new CategoryRequestDto();
        input.setId("4");
        requests.add(input);
        input = new CategoryRequestDto();
        input.setId("5");
        requests.add(input);


        return externalServiceLocal.getCategories(requests);
    }

    private List<DropOffResponseDto> getDropOffPoints() {
        List<DropOffRequestDto> requests = new ArrayList<DropOffRequestDto>();
        DropOffRequestDto request = new DropOffRequestDto();
        request.setId("1");
        requests.add(request);
        request = new DropOffRequestDto();
        request.setId("2");
        requests.add(request);

        return externalServiceLocal.getDropOffPoints(requests);
    }
}
