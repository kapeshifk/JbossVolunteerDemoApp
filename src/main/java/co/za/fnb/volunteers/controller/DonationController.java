package co.za.fnb.volunteers.controller;

import co.za.fnb.volunteers.dto.donation.ExternalServicesDto;
import co.za.fnb.volunteers.dto.donation.DonationItemDto;
import co.za.fnb.volunteers.model.Volunteer;
import co.za.fnb.volunteers.service.DonationLocal;
import co.za.fnb.volunteers.service.VolunteerLocal;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.List;

@Path("/api")
@RolesAllowed({"admin","user"})
@Stateless
public class DonationController {

    private static final Logger LOG = Logger.getLogger(DonationController.class);

    @EJB
    private DonationLocal donationBean;

    @EJB
    private VolunteerLocal volunteerBean;

    @EJB
    private UserController userController;

    @GET
    @Path("/donation")
    @Produces({"application/json"})
    public ExternalServicesDto getDonationData() {
        return donationBean.getExternalServiceData();
    }

    @GET
    @Path("/donations")
    @Produces({"application/json"})
    public List<DonationItemDto> getDonations() {
        final String userId = userController.getAuthenticatedUserId().getLogin();
        return donationBean.getDonationItems(userId);
    }

    @POST
    @Path("/donation")
    @Consumes({"application/json"})
    public void saveDonation(DonationItemDto donationItems) {
        final String userId = userController.getAuthenticatedUserId().getLogin();

        LOG.debugf("saveDonation(%s) for %s", donationItems, userId);

        Volunteer volunteer = volunteerBean.findVolunteerByUsername(userId);
        donationBean.saveDonationForUser(volunteer, donationItems);
    }
}
