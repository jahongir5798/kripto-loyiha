package uz.jahonservice.loyiha.service;

import uz.jahonservice.loyiha.dto.LoginDto;
import uz.jahonservice.loyiha.dto.RegistrationDto;
import uz.jahonservice.loyiha.dto.UserDto;
import uz.jahonservice.loyiha.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse<UserDto> registration(RegistrationDto registrationDto);

    JwtResponse<UserDto> login(LoginDto loginDto);
}
