package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dto.external.CategoryRequestDto;
import co.za.fnb.volunteers.dto.external.CategoryResponseDto;
import co.za.fnb.volunteers.dto.external.DropOffRequestDto;
import co.za.fnb.volunteers.dto.external.DropOffResponseDto;
import co.za.fnb.volunteers.ws.*;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Stateless
@RolesAllowed({"admin","user"})
public class ExternalServiceBean implements ExternalServiceLocal {
    final static Logger LOG = Logger.getLogger(ExternalServiceBean.class);

    @EJB
    private DataImporterService dataImporterService;

    @Override
    public List<CategoryResponseDto> getCategories(List<CategoryRequestDto> requests){

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

        List<Future<CategoryResponseDto>> futures = new ArrayList<Future<CategoryResponseDto>>();
        for (final CategoryRequestDto request : requests) {
            Callable<CategoryResponseDto> callable = new Callable<CategoryResponseDto>() {
                public CategoryResponseDto call() throws Exception {
                    CategoryResponseDto response;
                    // process multi-threading SOAP requests
                    response = callCategoriesWS(request);
                    return response;
                }
            };
            futures.add(service.submit(callable));
        }

        service.shutdown();

        List<CategoryResponseDto> outputs = new ArrayList<CategoryResponseDto>();
        for (Future<CategoryResponseDto> future : futures) {
            try {
                outputs.add(future.get());
            } catch (InterruptedException e) {
                LOG.log(Logger.Level.DEBUG, e);
            } catch (ExecutionException e) {
                LOG.log(Logger.Level.DEBUG, e);
            }
        }
        return outputs;
    }

    @Override
    public List<DropOffResponseDto> getDropOffPoints(List<DropOffRequestDto> requests){

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(threads);

        List<Future<DropOffResponseDto>> futures = new ArrayList<Future<DropOffResponseDto>>();
        for (final DropOffRequestDto request : requests) {
            Callable<DropOffResponseDto> callable = new Callable<DropOffResponseDto>() {
                public DropOffResponseDto call() throws Exception {
                    DropOffResponseDto response;
                    // process multi-threading SOAP requests
                    response = callDropOffPointsWS(request);
                    return response;
                }
            };
            futures.add(service.submit(callable));
        }

        service.shutdown();

        List<DropOffResponseDto> outputs = new ArrayList<DropOffResponseDto>();
        for (Future<DropOffResponseDto> future : futures) {
            try {
                outputs.add(future.get());
            } catch (InterruptedException e) {
                LOG.log(Logger.Level.DEBUG, e);
            } catch (ExecutionException e) {
                LOG.log(Logger.Level.DEBUG, e);
            }
        }
        return outputs;
    }

    private CategoryResponseDto callCategoriesWS(CategoryRequestDto requestDto) {
        CategoryResponseDto response = new CategoryResponseDto();
        try {
            Categories categoriesService = new Categories();
            CategoriesPortType portType = categoriesService.getPort(CategoriesPortType.class);
            CategoriesRequestType requestType = new CategoriesRequestType();
            requestType.setID(requestDto.getId());
            CategoriesResponseType responseType = portType.getCategories(requestType);
            response.setId(responseType.getID());
            response.setDescription(responseType.getDescription());
            response.setMaxUnits(responseType.getMaxUnits());
            response.setCountry(responseType.getCountry());
        } catch (Exception e) {
            // Stub response for testing purposes
            CategoriesResponseType responseType = getFakeCategories(requestDto.getId());
            response.setId(responseType.getID());
            response.setDescription(responseType.getDescription());
            response.setMaxUnits(responseType.getMaxUnits());
            response.setCountry(responseType.getCountry());
        }
        return response;
    }


    private DropOffResponseDto callDropOffPointsWS(DropOffRequestDto requestDto) {
        DropOffResponseDto response = new DropOffResponseDto();
        try {
            DropOffPoints dropOffService = new DropOffPoints();
            DropOffPointsPortType portType = dropOffService.getPort(DropOffPointsPortType.class);
            DropOffPointsRequestType requestType = new DropOffPointsRequestType();
            requestType.setID(requestDto.getId());
            DropOffPointsResponseType responseType = portType.getDropOffPoints(requestType);
            response.setId(responseType.getID());
            response.setDescription(responseType.getDescription());
            response.setAddress(responseType.getAddress());
            response.setContactNumber(responseType.getContactNumber());
        } catch (Exception e) {
            // Stub response for testing purposes
            DropOffPointsResponseType responseType = getStubbedDropOffPoints(requestDto.getId());
            response.setId(responseType.getID());
            response.setDescription(responseType.getDescription());
            response.setAddress(responseType.getAddress());
            response.setContactNumber(responseType.getContactNumber());
        }
        return response;
    }

    private CategoriesResponseType getFakeCategories(String id){
        Map<String,CategoriesResponseType> categoriesResponseTypeHashMap = new HashMap<String,CategoriesResponseType>();
        CategoriesResponseType responseType = new CategoriesResponseType();
        responseType.setID("1");
        responseType.setDescription("Blankets");
        responseType.setMaxUnits("10");
        responseType.setCountry("South Africa");
        categoriesResponseTypeHashMap.put("1", responseType);

        responseType = new CategoriesResponseType();
        responseType.setID("2");
        responseType.setDescription("Food");
        responseType.setMaxUnits("5");
        responseType.setCountry("South Africa");
        categoriesResponseTypeHashMap.put("2", responseType);

        responseType = new CategoriesResponseType();
        responseType.setID("3");
        responseType.setDescription("Consumables");
        responseType.setMaxUnits("1");
        responseType.setCountry("South Africa");
        categoriesResponseTypeHashMap.put("3", responseType);

        responseType = new CategoriesResponseType();
        responseType.setID("4");
        responseType.setDescription("Clothes");
        responseType.setMaxUnits("5");
        responseType.setCountry("South Africa");
        categoriesResponseTypeHashMap.put("4", responseType);

        return categoriesResponseTypeHashMap.get(id);
    }

    private DropOffPointsResponseType getStubbedDropOffPoints(String id){
        Map<String,DropOffPointsResponseType> dropOffPointsResponseTypeMap = new HashMap<String,DropOffPointsResponseType>();
        DropOffPointsResponseType responseType = new DropOffPointsResponseType();
        responseType.setID("1");
        responseType.setDescription("Wilderness Hotel");
        responseType.setAddress("6 George Rd, Wilderness, 6560");
        responseType.setContactNumber("044 877 1110");
        dropOffPointsResponseTypeMap.put("1", responseType);

        responseType = new DropOffPointsResponseType();
        responseType.setID("2");
        responseType.setDescription("Gift of Givers Knysna");
        responseType.setAddress("5");
        responseType.setContactNumber("0800786911");
        dropOffPointsResponseTypeMap.put("2", responseType);

        return dropOffPointsResponseTypeMap.get(id);
    }
}
