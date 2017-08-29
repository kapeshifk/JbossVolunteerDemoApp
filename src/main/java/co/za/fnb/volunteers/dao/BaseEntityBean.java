package co.za.fnb.volunteers.dao;

import co.za.fnb.volunteers.model.BaseEntityClass;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Superclass for all our repositories to avoid direct use of the {@link EntityManager} and to
 * provide common operations out of the box.
 * @param <E> the class of the entity to be persisted/queried.
 */
@Stateless
@RolesAllowed({"admin","user"})
public class BaseEntityBean<E extends BaseEntityClass> implements EntityBean<E> {

    private EntityManager entityManager;

    private Class<E> entityClass;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public BaseEntityBean() {
    }

    public BaseEntityBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Creates a typed query that returns the same type as defined in the implementing bean.
     *
     * @param jpql the query string.
     *
     * @return the results found.
     */
    protected TypedQuery<E> createQuery(String jpql) {
        return entityManager.createQuery(jpql, entityClass);
    }

    /**
     * Create a typed query that returns an entity of a type different to that defined by the implementing repository.
     *
     * @param jpql the query string.
     * @param <E> the entity class.
     * @param type the class type to be returned.
     *
     * @return the results found.
     */
    protected <E> TypedQuery<E> createQuery(String jpql, Class<E> type) {
        return entityManager.createQuery(jpql, type);
    }

    /**
     * Creates a named query that returns the same type as defined in the implementing repository.
     *
     * @param queryName the query name.
     *
     * @return the results found.
     */
    protected TypedQuery<E> createNamedQuery(String queryName) {
        return createNamedQuery(queryName, entityClass);
    }

    /**
     * Create a typed query that returns an entity of a type different to that defined by the implementing repository.
     *
     * @param queryName the query name.
     * @param <E> the entity class.
     * @param type the class type to be returned.
     *
     * @return the results found.
     */
    protected <E> TypedQuery<E> createNamedQuery(String queryName, Class<E> type) {
        return entityManager.createNamedQuery(queryName, type);
    }

    @Override
    public E findById(long entityId) {
        return entityManager.find(entityClass, entityId);
    }

    @Override
    public <E> List<E> findAll(Class<E> clazz) {
        return entityManager.createQuery("SELECT o from " + clazz.getSimpleName() + " o ", clazz).getResultList();
    }

    @Override
    public E merge(E entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(E entity) {
        entityManager.remove(entity);
    }
}
