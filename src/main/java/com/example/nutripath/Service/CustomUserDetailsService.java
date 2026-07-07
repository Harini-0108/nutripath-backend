package com.example.nutripath.Service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.nutripath.Entity.SystemAccount;
import com.example.nutripath.Repository.SystemAccountRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SystemAccountRepo accountRepo;
    public CustomUserDetailsService(SystemAccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SystemAccount account = accountRepo.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("User Not Found"));
        return User.builder()
            .username(account.getEmail())
            .password(account.getPasswordHash())
            .roles(account.getRole())
            .build();
    }

}

