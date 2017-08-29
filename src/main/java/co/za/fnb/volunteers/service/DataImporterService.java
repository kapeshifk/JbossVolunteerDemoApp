package co.za.fnb.volunteers.service;

import co.za.fnb.volunteers.model.*;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

@Singleton
@Startup
@RunAs("admin")
public class DataImporterService {

    private static final Logger LOGGER = Logger.getLogger(DataImporterService.class);
    private static final String USERNAME1 = "8602040000000";
    private static final String USERNAME2 = "9203260000000";
    private static final String USERNAME3 = "7501120000000";
    private static final String USERNAME4 = "8023120000000";
    private static final String USER_PASSWORD = "encrypted@fnb";
    private static final String ADMIN1 = "9001010000000";
    private static final String ADMIN2 = "9002020000000";
    private static final String ADMIN_PASSWORD = "encrypted@admin";

    @PersistenceContext
    private EntityManager em;


    @PostConstruct
    public void insertTestData() {
        List<Volunteer> allVolunteers = findAllUsers();
        if (allVolunteers.isEmpty()) {
            final Role userRole = new Role("user");
            em.persist(userRole);
            final Role adminRole = new Role("admin");
            em.persist(adminRole);

            List<String> users = Arrays.asList(USERNAME1, USERNAME2, USERNAME3, USERNAME4);

            LOGGER.info("Creating new test users");
            for (int i = 0; i < users.size(); i++) {
                Volunteer volunteer = Volunteer.createUser(users.get(i), USER_PASSWORD, "John " + (i + 1), "Snow #" + i);
                volunteer.addRole(userRole);

                em.persist(volunteer);
            }

            List<String> admins = Arrays.asList(ADMIN1, ADMIN2);

            for (int i = 0; i < admins.size(); i++) {
                Volunteer volunteer = Volunteer.createUser(admins.get(i), ADMIN_PASSWORD, "Daenerys " + (i + 1), "Targaryen #" + i);
                volunteer.addRole(adminRole);
                volunteer.addRole(userRole);

                em.persist(volunteer);
            }
            LOGGER.info("Test users created.");
            em.flush();
        } else {
            LOGGER.info("Predefined users already exists");
        }
    }

    private List<Volunteer> findAllUsers() {
        LOGGER.debug("findAllUsers()");
        Query query = em.createQuery("select user from Volunteer user");
        return (List<Volunteer>) query.getResultList();
    }

}
