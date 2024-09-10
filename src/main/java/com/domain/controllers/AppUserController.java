package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.AppUserData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.AppUser;
import com.domain.services.AppUserService;

 
@RestController
@RequestMapping("/api/users")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData userData){
        ResponseData<AppUser> responseData = new ResponseData<>();
        AppUser appUser = modelMapper.map(userData, AppUser.class);
        responseData.setPayload(appUserService.registerAppUser(appUser));
        responseData.setStatus(true);
        responseData.getMessage().add("User registered successfully");
        return ResponseEntity.ok(responseData);
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseData<AppUser>> login(@RequestBody AppUserData userData) {
        ResponseData<AppUser> responseData = new ResponseData<>();

        // Map input data to the AppUser entity
        AppUser appUser = modelMapper.map(userData, AppUser.class);

        try {
            // Call the service to authenticate the user
            AppUser loggedInUser = appUserService.loginUser(appUser.getEmail(), appUser.getPassword());

            // Set the response
            responseData.setStatus(true);
            responseData.getMessage().add("User logged in successfully");
            responseData.setPayload(loggedInUser);

            return ResponseEntity.ok(responseData);

        } catch (IllegalArgumentException ex) {
            // Handle login failure (e.g., user not found or invalid password)
            responseData.setStatus(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.badRequest().body(responseData);
        }
    }

}
