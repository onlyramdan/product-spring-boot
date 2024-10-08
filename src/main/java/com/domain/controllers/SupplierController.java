package com.domain.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.dto.SupplierData;
import com.domain.models.entities.Supplier;
import com.domain.services.SupplierService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping 
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
          if(errors.hasErrors()){
             for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
                
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
          }
            Supplier supplier = modelMapper.map(supplierData, Supplier.class);
            responseData.setStatus(true);
            responseData.setPayload(supplierService.save(supplier));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id){
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierData supplierData, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();
          if(errors.hasErrors()){
             for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
                
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
          }
            Supplier supplier = modelMapper.map(responseData, Supplier.class);
            responseData.setStatus(true);
            responseData.setPayload(supplierService.save(supplier));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long Id){
        supplierService.removeOne(Id);
    }

    @PostMapping("/search/byemail")
    public Supplier FindByEmail(@RequestBody SearchData searchData){
      return supplierService.findByEmail (searchData.getSearchKey());
    }

    @PostMapping("/search/byname")    
    public List<Supplier> findByName(@RequestBody SearchData searchData){
      return supplierService.findbyName(searchData.getSearchKey());
    }

    @PostMapping("/search/byname/startwith")    
    public List<Supplier> findByNameStartingWith(@RequestBody SearchData searchData){
      return supplierService.findbyNameStartingWith(searchData.getSearchKey());
    }

    @PostMapping("/search/bynameoremail")    
    public List<Supplier> findByNameOrEmail(@RequestBody SearchData searchData){
      return supplierService.findbyNameOrEmail(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }

}
