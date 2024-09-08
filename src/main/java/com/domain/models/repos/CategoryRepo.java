package com.domain.models.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.models.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Page<Category> findByNameContains(String name, Pageable pageable);

}
