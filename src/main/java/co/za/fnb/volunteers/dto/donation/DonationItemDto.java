package co.za.fnb.volunteers.dto.donation;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class DonationItemDto {

    private Long id;
    private Long donationId;
    private Integer quantity;
    private String drop_off_point;
    private String comment;
    private ProductDto[] products;

    public DonationItemDto() {
    }

    public DonationItemDto(Integer quantity, String drop_off_point, String comment, ProductDto[] products, Long donationId) {
        this.quantity = quantity;
        this.drop_off_point = drop_off_point;
        this.comment = comment;
        this.products = products;
        this.donationId = donationId;
    }

    public DonationItemDto(Long id, Integer quantity, String drop_off_point, String comment, ProductDto[] products, Long donationId) {
        this.id = id;
        this.quantity = quantity;
        this.drop_off_point = drop_off_point;
        this.comment = comment;
        this.products = products;
        this.donationId = donationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDonationId() {
        return donationId;
    }

    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDrop_off_point() {
        return drop_off_point;
    }

    public void setDrop_off_point(String drop_off_point) {
        this.drop_off_point = drop_off_point;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ProductDto[] getProducts() {
        return products;
    }

    public void setProducts(ProductDto[] products) {
        this.products = products;
    }
}
