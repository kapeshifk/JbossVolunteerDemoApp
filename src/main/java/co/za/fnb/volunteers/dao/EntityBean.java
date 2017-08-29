package co.za.fnb.volunteers.dao;

import co.za.fnb.volunteers.model.BaseEntityClass;

import javax.ejb.Local;
import java.util.List;

/**
 * Contract for all entities, outlining generic CRUD operations.
 * @param <E> the entity type queried.
 */
@Local
public interface EntityBean<E extends BaseEntityClass> {

    /**
     * Merges a new entity.
     *
     * @param entity    the entity to create.
     * @return the persisted entity.
     */
    E merge(E entity);

    /**
     * Merges a new entity.
     *
     * @param entity    the entity to create.
     * @return the persisted entity.
     */
    void persist(E entity);

    /**
     * Merges a new entity.
     *
     * @param entity    the entity to create.
     * @return the persisted entity.
     */
    void remove(E entity);

    /**
     * Finds an entity by primary key.
     *
     * @param id the ID of the entity to be found.
     * @return the entity found.
     */
    E findById(long id);

    /**
     * Finds all entities of given type.
     *
     * @return the entity found.
     */
    <E> List<E> findAll(Class<E> entityClass);
}