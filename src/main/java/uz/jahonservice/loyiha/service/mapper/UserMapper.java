package uz.jahonservice.loyiha.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.jahonservice.loyiha.dto.RegistrationDto;
import uz.jahonservice.loyiha.dto.UserDto;
import uz.jahonservice.loyiha.entity.User;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User RegistrationDtoToUserEntity(RegistrationDto dto) {
        User user = new User();
        user.setUsername(dto.getUserName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }

    public UserDto UserEntityToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getUuid());
        userDto.setUserName(user.getUsername());
        return userDto;
    }

}
