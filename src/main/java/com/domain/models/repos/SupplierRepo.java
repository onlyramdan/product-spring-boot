package com.domain.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Supplier;

import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    
    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameStartingWith(String name);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
