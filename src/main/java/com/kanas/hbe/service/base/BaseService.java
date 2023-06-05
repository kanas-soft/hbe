package com.kanas.hbe.service.base;

import com.kanas.hbe.domain.entity.base.BaseEntity;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Interface to a Service bean, that should handle all common operations related
 * to entities.
 *
 * @param <E> - entity type.
 */
public interface BaseService<E extends BaseEntity> {
    /**
     * Save the entity.
     *
     * @param entity to store.
     *
     * @return the persisted entity.
     */
    default E save(@NotNull E entity) {
        Assert.notNull(entity, "Entity cannot be null");
        getLogger().debug("Request to save entity of type: {}!", getType().getSimpleName());

        return getJpaRepository().saveAndFlush(entity);
    }

    /**
     * Get entity by its identifier.
     *
     * @param id - identifier of the entity.
     *
     * @return the entity.
     */
    default Optional<E> findById(Long id) {
        Assert.notNull(id, "Identifier cannot be null");
        getLogger().debug("Request to fetch entity of type {} with ID: {}!", getType().getSimpleName(), id);

        return getJpaRepository().findById(id);
    }

    /**
     * Get all the entities.
     *
     * @param pageable - pagination information
     *
     * @return a page of entities.
     */
    default Page<E> findAll(Pageable pageable) {
        getLogger().debug("Request to get all {}s!", getType().getSimpleName());
        return getJpaRepository().findAll(pageable);
    }

    /**
     * Get all the entities.
     *
     * @return a list of entities.
     */
    default List<E> findAll() {
        getLogger().debug("Request to get all {}s!", getType().getSimpleName());
        return getJpaRepository().findAll();
    }

    /**
     * Delete an entity.
     *
     * @param entity - to delete.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    default void delete(@NotNull E entity) {
        Assert.notNull(entity, "Entity cannot be null!");
        getLogger().debug("Request to delete entity of type: {}!", getType().getSimpleName());

        getJpaRepository().delete(entity);
    }

    /**
     * Delete entity by its identifier.
     *
     * @param id - identifier of the entity.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    default void deleteById(@NotNull Long id) {
        Assert.notNull(id, "Identifier cannot be null");
        getLogger().debug("Request to delete entity of type: {}, with ID: {}!", getType().getSimpleName(), id);

        getJpaRepository().deleteById(id);
    }

    /**
     * Delete all given entities.
     *
     * @param entities - to delete.
     */
    @Transactional(propagation = Propagation.REQUIRED)
    default void deleteAll(Collection<E> entities) {
        getJpaRepository().deleteAll(entities);
    }

    /**
     *
     * Retrieve the corresponding {@link JpaRepository} to the entity.
     *
     * @return repository.
     */
    JpaRepository<E, Long> getJpaRepository();

    /**
     * Get current class logger.
     *
     * @return logger.
     */
    Logger getLogger();

    /**
     * Get current class type.
     *
     * @return type.
     */
    Class<E> getType();
}
