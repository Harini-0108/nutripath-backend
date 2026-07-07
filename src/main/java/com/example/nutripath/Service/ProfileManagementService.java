package com.example.nutripath.Service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.example.nutripath.Dto.ProfileDto;
import com.example.nutripath.Entity.ClientProfile;

public interface ProfileManagementService {

    List<ClientProfile> getAllProfiles();

    ClientProfile createProfile(ProfileDto dto);

    ClientProfile getProfileById(Long id);

    ClientProfile updateProfileById(Long id, ProfileDto dto);

    void deleteProfile(Long id);

    ClientProfile updateProfile(String email, ProfileDto dto);

    ClientProfile getMyProfile(Authentication auth);

}