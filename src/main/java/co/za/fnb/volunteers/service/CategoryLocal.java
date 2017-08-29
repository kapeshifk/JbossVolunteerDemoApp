package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.EntityBean;
import co.za.fnb.volunteers.model.Category;

import javax.ejb.Local;

@Local
public interface CategoryLocal extends EntityBean<Category> {

}
