package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.dao.BaseEntityBean;
import co.za.fnb.volunteers.model.Category;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
@RolesAllowed({"admin","user"})
public class CategoryBean extends BaseEntityBean<Category> implements CategoryLocal {

    final static Logger LOG = Logger.getLogger(CategoryBean.class);

    public CategoryBean() {
        super(Category.class);
    }
}
