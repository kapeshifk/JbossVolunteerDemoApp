package co.za.fnb.volunteers.dto.user;

import co.za.fnb.volunteers.model.Volunteer;

public class AuthenticatedUserDto {

    private String login;
    private String name;
    private String[] roles;

    public AuthenticatedUserDto(String login, String[] roles) {
        this.login = login;
        this.roles = roles;
    }

    public AuthenticatedUserDto(Volunteer volunteer, String[] roles) {
        this.login = volunteer.getUsername();
        this.roles = roles;
        this.name = volunteer.getFirstName();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
