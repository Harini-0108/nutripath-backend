package com.example.nutripath.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.nutripath.Dto.ProfileDto;
import com.example.nutripath.Entity.ClientProfile;
import com.example.nutripath.Service.ProfileManagementService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileManagementService profileService;

    public ProfileController(ProfileManagementService profileService) {
        this.profileService = profileService;
    }

    // CREATE - Admin only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientProfile> createProfile(@RequestBody ProfileDto dto) {
        return ResponseEntity.ok(profileService.createProfile(dto));
    }

    // GET ALL - Admin and User
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<ClientProfile>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    // GET BY ID - Admin and User
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ClientProfile> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    // DELETE - Admin only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok("Profile deleted successfully.");
    }
}