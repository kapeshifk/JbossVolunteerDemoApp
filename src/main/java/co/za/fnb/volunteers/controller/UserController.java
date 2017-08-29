package co.za.fnb.volunteers.controller;

import co.za.fnb.volunteers.dto.user.AuthenticatedUserDto;
import co.za.fnb.volunteers.model.Volunteer;
import co.za.fnb.volunteers.service.VolunteerLocal;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.security.Principal;
import java.util.LinkedList;

@Path("/api")
@RolesAllowed({"admin", "user"})
@Stateless
public class UserController {

    private final static Logger LOG = Logger.getLogger(UserController.class);

    @EJB
    private VolunteerLocal volunteerBean;

    @Context
    private HttpServletRequest httpRequest;

    @GET
    @Path("/user")
    @Produces({"application/json"})
    public AuthenticatedUserDto getAuthenticatedUserId() {
        final Principal userPrincipal = httpRequest.getUserPrincipal();
        if (userPrincipal == null) {
            return new AuthenticatedUserDto("Guest", new String[]{});
        } else {
            final LinkedList<String> roles = new LinkedList<String>();
            checkAndAddRole("admin", roles);
            checkAndAddRole("user", roles);
            final String[] rolesArray = roles.toArray(new String[roles.size()]);

            Volunteer volunteer = volunteerBean.findVolunteerByUsername(userPrincipal.getName());
            return new AuthenticatedUserDto(volunteer, rolesArray);
        }
    }

    private void checkAndAddRole(String role, LinkedList<String> roles) {
        if (httpRequest.isUserInRole(role)) {
            roles.add(role);
        }
    }

    @DELETE
    @Path("/session")
    public void logout() {
        LOG.debug("logout the current user");
        httpRequest.getSession().invalidate();
    }

}
