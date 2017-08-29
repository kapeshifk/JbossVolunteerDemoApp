package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.EntityBean;
import co.za.fnb.volunteers.dto.donation.DonationItemDto;
import co.za.fnb.volunteers.dto.donation.ExternalServicesDto;
import co.za.fnb.volunteers.model.DonationItem;

import javax.ejb.Local;

@Local
public interface Donation_ItemLocal extends EntityBean<DonationItem> {
}
