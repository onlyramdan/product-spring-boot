package com.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.models.entities.AppUser;
import com.domain.models.repos.AppUserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder  bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findByEmail(email).orElseThrow(() ->
        new UsernameNotFoundException("User with email " + email + " not found"));
    }

    public AppUser registerAppUser(AppUser user){
        boolean userExist = appUserRepo.findByEmail(user.getEmail()).isPresent();
        if(userExist){
            throw new RuntimeException("User with email " + user.getEmail() + " already exist");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return appUserRepo.save(user);
    }

    public AppUser loginUser(String email, String password) {
        // Fetch user from the database
        AppUser user = appUserRepo.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if the password matches
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // If successful, return the user object
        return user;
    }
}
