package com.example.basespringbootproject.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    /**
     * Save an entry
     *
     * @param obj the entry to save.
     * @return the persisted entity.
     */
    T save(T obj);

    /**
     * Updates entry.
     *
     * @param obj the entry to update.
     * @return the persisted entity.
     */
    T update(T obj);

    /**
     * Partially updates entry.
     *
     * @param obj the entry to update partially.
     * @return the persisted entity.
     */
    Optional<T> partialUpdate(T obj);

    /**
     * Get the entry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<T> findById(Long id);

    /**
     * Get all the entries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Get all the entries
     *
     * @return the list of entities
     */
    List<T> findAll();

    /**
     * Delete the "id" entry.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
