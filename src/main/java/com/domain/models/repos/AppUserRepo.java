package com.domain.models.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.models.entities.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long>{
    
    Optional<AppUser> findByEmail(String email);
    AppUser findByEmailAndPassword(String email, String password);

}
