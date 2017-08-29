package co.za.fnb.volunteers.model;

import co.za.fnb.volunteers.service.common.VolunteerNamedQueryConstants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name = "volunteer")
@NamedQuery(name = VolunteerNamedQueryConstants.FIND_USER_BY_USER_ID,
        query = "select vol from Volunteer vol where vol.username = :userId")
public class Volunteer extends BaseEntityClass {

    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @NotEmpty
    @Column(name = "encrypted_password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "reg_date")
    private Date registrationDate;

    @Column
    private Integer status;


    @ManyToMany(mappedBy = "volunteers")
    private Collection<Role> roles = new HashSet<Role>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean checkPassword(String password) {
        if (password == null) {
            return false;
        } else {
            return password.equals(this.password);
        }
    }

    public void changePassword(String passwordOld, String passwordNew) {
        if (checkPassword(passwordOld)) {
            this.password = passwordNew;
        }
    }

    public static Volunteer createUser(String username, String password, String firstName, String lastName) {
        Volunteer volunteer = new Volunteer();
        volunteer.setUsername(username);
        volunteer.password = password;
        volunteer.firstName = firstName;
        volunteer.lastName = lastName;
        return volunteer;
    }

    public void addRole(Role role) {
        if (!(roles.contains(role))) {
            roles.add(role);
            role.addUser(this);
        }
    }
}
