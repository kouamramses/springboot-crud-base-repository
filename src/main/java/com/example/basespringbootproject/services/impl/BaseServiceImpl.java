package com.example.basespringbootproject.services.impl;

import com.example.basespringbootproject.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<R extends JpaRepository<M, Long>, M> implements BaseService<M> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public M save(M obj) {
        return repository.save(obj);
    }

    @Override
    public M update(M obj) {
        return repository.save(obj);
    }

    @Override
    public Optional<M> partialUpdate(M obj) {
        return Optional.empty();
    }

    @Override
    public Optional<M> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<M> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<M> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {

    }
}
