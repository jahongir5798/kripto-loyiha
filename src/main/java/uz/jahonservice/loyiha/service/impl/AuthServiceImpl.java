package uz.jahonservice.loyiha.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.jahonservice.loyiha.dto.LoginDto;
import uz.jahonservice.loyiha.dto.RegistrationDto;
import uz.jahonservice.loyiha.dto.UserDto;
import uz.jahonservice.loyiha.dto.response.JwtResponse;
import uz.jahonservice.loyiha.entity.User;
import uz.jahonservice.loyiha.exceptions.DatabaseException;
import uz.jahonservice.loyiha.jwtService.JwtService;
import uz.jahonservice.loyiha.repository.UserRepository;
import uz.jahonservice.loyiha.service.AuthService;
import uz.jahonservice.loyiha.exceptions.MyException;
import uz.jahonservice.loyiha.service.mapper.UserMapper;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public JwtResponse<UserDto> registration(RegistrationDto dto) {
        if (!dto.getPassword().equals(dto.getPrePassword())){
            throw new MyException("password and prePassword is not equals");
        }

        if (userRepository.existsByUsername(dto.getUserName())){
            throw new MyException("Username already exists");
        }
        try {

            User user = userMapper.RegistrationDtoToUserEntity(dto);
            user.setCreated_at(LocalDateTime.now());
            userRepository.save(user);

            return JwtResponse.<UserDto>builder()
                    .code(0)
                    .message("User registered successfully")
                    .success(true)
                    .user(userMapper.UserEntityToUserDto(user))
                    .build();

        }catch (Exception e){
            throw new DatabaseException("Database error while creating user");
        }
    }

    @Override
    public JwtResponse<UserDto> login(LoginDto loginDto) {

        User user = userRepository.findByUsername(loginDto.getUserName()).orElseThrow(() -> new MyException("username not found"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new MyException("wrong password");
        }

        return JwtResponse.<UserDto>builder()
                .code(0)
                .message("Login successful")
                .success(true)
                .token(jwtService.buildToken(user))
                .user(userMapper.UserEntityToUserDto(user))
                .build();
    }
}
