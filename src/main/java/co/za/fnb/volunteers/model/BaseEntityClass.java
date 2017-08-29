package co.za.fnb.volunteers.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Base class for all model classes.
 */
@MappedSuperclass
public abstract class BaseEntityClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SuppressWarnings({"UnusedDeclaration"})
    private Long id;

    @Transient
    private boolean doNotAutoUpdateDateUpdated = false;

    @Transient
    private boolean doNotAutoUpdateDateCreated = false;

    @Version
    protected int version;

    @Column(name = "date_created")
    protected Date dateCreated;

    @Column(name = "date_updated")
    protected Date dateUpdated;

    public Long getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }


    public void setDateUpdated(Date dateUpdated) {
        doNotAutoUpdateDateUpdated = true;
        this.dateUpdated = dateUpdated;
    }

    public void setDateCreated(Date dateCreated) {
        doNotAutoUpdateDateCreated = (dateCreated != null);
        this.dateCreated = dateCreated;
    }

    @PrePersist
    protected void onCreate() {
        if (!doNotAutoUpdateDateCreated) {
            dateCreated = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (!doNotAutoUpdateDateUpdated) {
            dateUpdated = new Date();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + getId();
    }
}
