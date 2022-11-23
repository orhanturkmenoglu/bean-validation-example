//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.beanvalidation.service;

import com.example.beanvalidation.dto.UserRequestDTO;
import com.example.beanvalidation.dto.UserResponseDTO;
import com.example.beanvalidation.entity.User;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.mapper.UserMapper;
import com.example.beanvalidation.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User mapToUser = this.userMapper.mapToUser(userRequestDTO);
        User save = this.userRepository.save(mapToUser);
        return this.userMapper.mapToUserResponseDTOOptional(save);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
        return this.userMapper.mapToUserResponseDTOList(userList);
    }

    public UserResponseDTO getByUserId(long userId) throws UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findById(userId);
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
        return this.userMapper.mapToUserResponseDTOOptional(optionalUser);
    }

    public UserService(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
}
