package co.za.fnb.volunteers.dto.donation;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ProductDto {
    private Long productId;
    private Integer max_units;
    private String description;
    private String country;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getMax_units() {
        return max_units;
    }

    public void setMax_units(Integer max_units) {
        this.max_units = max_units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
