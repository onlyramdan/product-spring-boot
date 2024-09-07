package com.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Supplier;
import com.domain.models.repos.SupplierRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id){
        return supplierRepo.findById(id).orElse(null);
    }
    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }
    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }
    public Supplier findByEmail(String email){
        return supplierRepo.findByEmail(email);
    }
    public List<Supplier> findbyName(String name){
        return supplierRepo.findByNameContains(name);
    }
    public List<Supplier> findbyNameStartingWith(String name){
        return supplierRepo.findByNameStartingWith(name);
    }
}
