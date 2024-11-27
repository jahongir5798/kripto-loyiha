package uz.jahonservice.loyiha.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jahonservice.loyiha.dto.LoginDto;
import uz.jahonservice.loyiha.dto.RegistrationDto;
import uz.jahonservice.loyiha.dto.UserDto;
import uz.jahonservice.loyiha.dto.response.JwtResponse;
import uz.jahonservice.loyiha.service.AuthService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public JwtResponse<UserDto> registration(
            @RequestBody RegistrationDto registrationDto
    ){
        log.info("AuthController registration method invoked");
        JwtResponse<UserDto> registration = authService.registration(registrationDto);
        log.info("AuthController registration method completed");
        return registration;
    }

    @PostMapping("/login")
    public JwtResponse<UserDto> login(
            @RequestBody LoginDto loginDto
    ){
        log.info("AuthController login method invoked");
        JwtResponse<UserDto> login = authService.login(loginDto);
        log.info("AuthController login method completed;");
        return login;
    }

}
