package com.example.basespringbootproject.controllers;

import com.example.basespringbootproject.services.impl.BaseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseController<S extends BaseServiceImpl, M> {

    protected final S service;

    public BaseController(S service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get element by ID")
    public ResponseEntity<M> getOne(@PathVariable("id") final Long id) throws Throwable {
        return (ResponseEntity<M>) service.findById(id)
                .map((obj) -> new ResponseEntity<>(obj, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Operation(summary = "Find all elements")
    public ResponseEntity<Page<M>> getAll(Pageable pageable) {
        Page<M> page = service.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete element by ID")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
