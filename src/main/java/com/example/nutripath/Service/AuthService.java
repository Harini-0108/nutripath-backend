package com.example.nutripath.Service;

import com.example.nutripath.Dto.AuthRequestDto;
import com.example.nutripath.Dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto register(AuthRequestDto dto);
    AuthResponseDto authenticate(AuthRequestDto dto);

}


