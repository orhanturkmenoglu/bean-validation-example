package com.example.beanvalidation.mapper;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<UserResponseDto> mapToUserResponseDtoList(List<User> users) {
        return users.stream().map(this::mapToUserResponseDto).collect(Collectors.toList());
    }

    public UserResponseDto mapToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getMobile())
                .gender(user.getGender())
                .age(user.getAge())
                .nationality(user.getNationality())
                .build();
    }

    public User mapToUser(UserRequestDto userRequestDTO) {
        return User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .mobile(userRequestDTO.getPhoneNumber())
                .gender(userRequestDTO.getGender())
                .age(userRequestDTO.getAge())
                .nationality(userRequestDTO.getNationality())
                .build();
    }
}
