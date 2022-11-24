package com.example.beanvalidation.mapper;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.dto.UserUpdateRequestDto;
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
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .age(user.getAge())
                .nationality(user.getNationality())
                .build();
    }

    public User mapToUser(UserRequestDto userRequestDto) {
        return User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .gender(userRequestDto.getGender())
                .age(userRequestDto.getAge())
                .nationality(userRequestDto.getNationality())
                .build();
    }

    public User mapToUser(UserUpdateRequestDto userUpdateRequestDto) {
        return User.builder()
                .id(userUpdateRequestDto.getUserId())
                .name(userUpdateRequestDto.getName())
                .email(userUpdateRequestDto.getEmail())
                .phoneNumber(userUpdateRequestDto.getPhoneNumber())
                .gender(userUpdateRequestDto.getGender())
                .age(userUpdateRequestDto.getAge())
                .nationality(userUpdateRequestDto.getNationality())
                .build();
    }
}
