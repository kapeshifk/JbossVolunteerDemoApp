package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.BaseEntityBean;
import co.za.fnb.volunteers.model.Donation;
import co.za.fnb.volunteers.model.DonationItem;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
@RolesAllowed({"admin","user"})
public class Donation_ItemBean extends BaseEntityBean<DonationItem> implements Donation_ItemLocal {

    final static Logger LOG = Logger.getLogger(Donation_ItemBean.class);

    public Donation_ItemBean() {
        super(DonationItem.class);
    }
}
