package com.example.nutripath.ServiceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.nutripath.Dto.AuthRequestDto;
import com.example.nutripath.Dto.AuthResponseDto;
import com.example.nutripath.Entity.SystemAccount;
import com.example.nutripath.Repository.SystemAccountRepo;
import com.example.nutripath.Security.JwtUtil;
import com.example.nutripath.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final SystemAccountRepo repository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(SystemAccountRepo repository,PasswordEncoder encoder,JwtUtil jwtUtil,AuthenticationManager authenticationManager) {

        this.repository = repository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDto register(AuthRequestDto dto) {
        SystemAccount account = new SystemAccount();
        account.setEmail(dto.getEmail());
        account.setPasswordHash(encoder.encode(dto.getPassword()));
        String requestedRole = dto.getRole();
        if ("ADMIN".equalsIgnoreCase(requestedRole)) {
            account.setRole("ADMIN");
        } else {
            account.setRole("USER");
        }
        account.setStatus("ACTIVE");
        repository.save(account);
        return new AuthResponseDto(null, "Registration Successful");
    }

    @Override
    public AuthResponseDto authenticate(AuthRequestDto dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));
        SystemAccount account = repository.findByEmail(dto.getEmail()).get();
        String token = jwtUtil.generateToken(account.getEmail(), account.getRole());
        return new AuthResponseDto(token, "Login Successful");
    }

}