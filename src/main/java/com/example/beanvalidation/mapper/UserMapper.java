
package com.example.beanvalidation.mapper;

import com.example.beanvalidation.dto.UserRequestDTO;
import com.example.beanvalidation.dto.UserResponseDTO;
import com.example.beanvalidation.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public List<UserResponseDTO> mapToUserResponseDTOList(List<User> users) {
        return users.stream().map(this::mapToUserResponseDTOOptional).collect(Collectors.toList());
    }

    public UserResponseDTO mapToUserResponseDTOOptional(Optional<User> optionalUser) {
        return UserResponseDTO.builder()
                .userId(optionalUser.get().getId())
                .name(optionalUser.get().getName())
                .email(optionalUser.get().getEmail())
                .mobile(optionalUser.get().getMobile())
                .gender(optionalUser.get().getGender())
                .age(optionalUser.get().getAge())
                .nationality(optionalUser.get().getNationality())
                .build();
    }

    public UserResponseDTO mapToUserResponseDTOOptional(User user) {
        return UserResponseDTO.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .gender(user.getGender())
                .age(user.getAge())
                .nationality(user.getNationality())
                .build();
    }

    public User mapToUser(UserRequestDTO userRequestDTO) {
        return User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .mobile(userRequestDTO.getMobile())
                .gender(userRequestDTO.getGender())
                .age(userRequestDTO.getAge())
                .nationality(userRequestDTO.getNationality())
                .build();
    }
}
