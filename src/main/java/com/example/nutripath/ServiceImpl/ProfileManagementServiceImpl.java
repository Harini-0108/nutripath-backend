package com.example.nutripath.ServiceImpl;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.nutripath.Dto.ProfileDto;
import com.example.nutripath.Entity.ClientProfile;
import com.example.nutripath.Entity.SystemAccount;
import com.example.nutripath.Repository.ClientProfileRepo;
import com.example.nutripath.Repository.SystemAccountRepo;
import com.example.nutripath.Service.ProfileManagementService;
import com.example.nutripath.Exception.ResourceNotFoundException;


@Service
public class ProfileManagementServiceImpl implements ProfileManagementService {

    private final ClientProfileRepo profileRepository;
    private final SystemAccountRepo accountRepository;

    public ProfileManagementServiceImpl(ClientProfileRepo profileRepository, SystemAccountRepo accountRepository) {
        this.profileRepository = profileRepository;
        this.accountRepository = accountRepository;
    }

   @Override
    public ClientProfile getMyProfile(Authentication auth) {

        SystemAccount account = accountRepository.findByEmail(auth.getName()).orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        return profileRepository.findByAccountId(account.getId()).orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Override
    public List<ClientProfile> getAllProfiles() {

        return profileRepository.findAll();
    }

    @Override
    public ClientProfile updateProfile(String email, ProfileDto dto) {

        SystemAccount account = accountRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        ClientProfile profile = profileRepository.findByAccountId(account.getId()).orElseGet(() -> {
            ClientProfile newProfile = new ClientProfile();
            newProfile.setAccountId(account.getId());
            return newProfile;
        });
        profile.setAge(dto.getAge());
        profile.setWeightKg(dto.getWeightKg());
        profile.setHeightCm(dto.getHeightCm());
        profile.setCalculatedBmr(dto.getCalculatedBmr());
        return profileRepository.save(profile);
    }

    @Override
    public ClientProfile createProfile(ProfileDto dto) {
        ClientProfile profile = new ClientProfile();
        profile.setAccountId(dto.getAccountId());
        profile.setAge(dto.getAge());
        profile.setWeightKg(dto.getWeightKg());
        profile.setHeightCm(dto.getHeightCm());
        profile.setCalculatedBmr(dto.getCalculatedBmr());
        return profileRepository.save(profile);
    }

    @Override
    public ClientProfile getProfileById(Long id) {
        return profileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found: " + id));
    }

    @Override
    public ClientProfile updateProfileById(Long id, ProfileDto dto) {
        ClientProfile profile = profileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profile not found: " + id));
        profile.setAge(dto.getAge());
        profile.setWeightKg(dto.getWeightKg());
        profile.setHeightCm(dto.getHeightCm());
        profile.setCalculatedBmr(dto.getCalculatedBmr());
        return profileRepository.save(profile);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}