package com.domain.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

import jakarta.websocket.server.PathParam;

public interface ProductRepo extends CrudRepository < Product, Long > {
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name  LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name); 

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId); 

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> finPrdouctsBySupplier(@PathParam("supplier") Supplier supplier);
}

