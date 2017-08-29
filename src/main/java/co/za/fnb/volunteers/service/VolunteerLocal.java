package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.EntityBean;
import co.za.fnb.volunteers.model.Volunteer;

import javax.ejb.Local;

@Local
public interface VolunteerLocal extends EntityBean<Volunteer> {

    Volunteer findVolunteerByUsername(String username);

}
