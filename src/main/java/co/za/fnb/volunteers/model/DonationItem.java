package co.za.fnb.volunteers.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donation_item")
public class DonationItem extends BaseEntityClass {

    @ManyToOne
    private Donation donation;

    @ManyToMany()
    private List<Category> products = new ArrayList<Category>();

    @Column
    private Integer quantity;

    @Column
    private String drop_off_point;

    @Column
    private String comment;

    public DonationItem() {
    }

    public DonationItem(Donation donation, List<Category> products, Integer quantity, String drop_off_point, String comment) {
        this.donation = donation;
        this.products = products;
        this.quantity = quantity;
        this.drop_off_point = drop_off_point;
        this.comment = comment;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public List<Category> getProducts() {
        return products;
    }

    public void addDonationItems(Category product) {
        products.add(product);
    }

    public void removeDonationItems(Category product) {
        products.remove(product);
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
}
