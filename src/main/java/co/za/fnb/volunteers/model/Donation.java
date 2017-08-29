package co.za.fnb.volunteers.model;

import co.za.fnb.volunteers.service.common.DonationNamedQueryConstants;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "donation")
@NamedQuery(name = DonationNamedQueryConstants.FIND_DONATION_BY_USER_ID,
        query = "select dp from Donation dp where dp.volunteer.username = :userId")
public class Donation extends BaseEntityClass {

    @ManyToOne
    private Volunteer volunteer;

    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "INDEX")
    private List<DonationItem> donationItems = new ArrayList<DonationItem>();


    public Donation() {
    }

    public Donation(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public List<DonationItem> getDonationItems() {
        return Collections.unmodifiableList(donationItems);
    }

    public void addDonationItems(DonationItem donationItem) {
        donationItem.setDonation(this);
        donationItems.add(donationItem);
    }

    public void removeDonationItems(DonationItem donationItem) {
        donationItems.remove(donationItem);
        if (donationItem != null) {
            donationItem.setDonation(null);
        }
    }

    public void updateTasksFromList(List<DonationItem> newDonationItemList) {
        donationItems =  new ArrayList<DonationItem>();
        for (DonationItem donationItem : newDonationItemList) {
            addDonationItems(donationItem);
        }
    }

}
