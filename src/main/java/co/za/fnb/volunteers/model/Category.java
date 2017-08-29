package co.za.fnb.volunteers.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseEntityClass {

    @Basic
    private String description;

    @Column(name = "max_units")
    private Integer maxUnits;

    @Column
    private String country;

    public Category() {
    }

    public Category(String description, Integer maxUnits, String country) {
        this.description = description;
        this.maxUnits = maxUnits;
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxUnits() {
        return maxUnits;
    }

    public void setMaxUnits(Integer maxUnits) {
        this.maxUnits = maxUnits;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
