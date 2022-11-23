package com.example.beanvalidation.service;

import com.example.beanvalidation.dto.UserRequestDTO;
import com.example.beanvalidation.dto.UserResponseDTO;
import com.example.beanvalidation.entity.User;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.mapper.UserMapper;
import com.example.beanvalidation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("UserService::createUser started");
        User mapToUser = this.userMapper.mapToUser(userRequestDTO);
        User save = this.userRepository.save(mapToUser);
        log.info("UserService::createUser finished");
        return this.userMapper.mapToUserResponseDTOOptional(save);
    }

    public List<UserResponseDTO> getAllUsers() {
        log.info("UserService::getAllUsers started");
        List<User> userList = this.userRepository.findAll();
        log.info("UserService::getAllUsers finished");
        return this.userMapper.mapToUserResponseDTOList(userList);
    }

    public UserResponseDTO getByUserId(long userId) throws UserNotFoundException {
        log.info("UserService::getByUserId started");
        Optional<User> optionalUser = this.userRepository.findById(userId);
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
        log.info("UserService::getByUserId finished");
        return this.userMapper.mapToUserResponseDTOOptional(optionalUser);
    }

}
