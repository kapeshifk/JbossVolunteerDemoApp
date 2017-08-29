package co.za.fnb.volunteers.dto.external;

public class CategoryResponseDto {
    public String id;
    public String country;
    public String description;
    public String maxUnits;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxUnits() {
        return maxUnits;
    }

    public void setMaxUnits(String maxUnits) {
        this.maxUnits = maxUnits;
    }
}
