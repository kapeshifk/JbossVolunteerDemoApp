package co.za.fnb.volunteers.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Entity
@Table(name = "role")
public class Role extends BaseEntityClass {

    public Role(String name) {
        this.name = name;
    }

    @Basic
    private String name;

    @ManyToMany
    private Collection<Volunteer> volunteers = new HashSet<Volunteer>();

    public Role() {
    }

    public String getName() {
        return name;
    }

    public Collection<Volunteer> getVolunteers() {
        return Collections.unmodifiableCollection(volunteers);
    }

    public void addUser(Volunteer volunteer) {
        if (!(volunteers.contains(volunteer))) {
            volunteers.add(volunteer);
            volunteer.addRole(this);
        }
    }

}
