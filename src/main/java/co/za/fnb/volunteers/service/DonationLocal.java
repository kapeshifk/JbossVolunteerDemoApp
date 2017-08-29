package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.EntityBean;
import co.za.fnb.volunteers.dto.donation.ExternalServicesDto;
import co.za.fnb.volunteers.dto.donation.DonationItemDto;
import co.za.fnb.volunteers.model.Donation;
import co.za.fnb.volunteers.model.Volunteer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DonationLocal extends EntityBean<Donation> {

    /**
     * Request external data if exists, else use stubbed data
     *
     * @return the Categories and Drop Off Points from external data service
     */
    public ExternalServicesDto getExternalServiceData();

    /**
     * Persist the donation to the database
     *
     * @param volunteer the current user
     * @param donationItems the donation items sent from front-end
     */
    public void saveDonationForUser(Volunteer volunteer, DonationItemDto donationItems);

    /**
     * A quick way to check persisted donations via rest end-point.
     * A better approach could have used Java Stream or equivalent IQuerable.
     *
     * @param userId the current user
     * @return the list of donation items.
     */
    public List<DonationItemDto> getDonationItems(String userId);

}
