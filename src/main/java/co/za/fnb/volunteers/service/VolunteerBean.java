package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.BaseEntityBean;
import co.za.fnb.volunteers.model.Volunteer;
import co.za.fnb.volunteers.service.common.VolunteerNamedQueryConstants;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
@RolesAllowed({"admin","user"})
public class VolunteerBean extends BaseEntityBean<Volunteer> implements VolunteerLocal {

    final static Logger LOG = Logger.getLogger(VolunteerBean.class);

    public VolunteerBean() {
        super(Volunteer.class);
    }

    @Override
    public Volunteer findVolunteerByUsername(String username) {
        LOG.debugf("findVolunteerByUsername() called for %s", username);
        return Volunteer.class.cast(createNamedQuery(VolunteerNamedQueryConstants.FIND_USER_BY_USER_ID).
                setParameter("userId", username).
                getSingleResult());
    }
}
