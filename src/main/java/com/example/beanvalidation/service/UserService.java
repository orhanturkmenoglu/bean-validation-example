package com.example.beanvalidation.service;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.dto.UserUpdateRequestDto;
import com.example.beanvalidation.entity.User;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.mapper.UserMapper;
import com.example.beanvalidation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDTO) {
        log.info("UserService::createUser started");

        User user = userMapper.mapToUser(userRequestDTO);
        User savedUser = userRepository.save(user);

        log.info("UserService::createUser finished");
        return userMapper.mapToUserResponseDto(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers(String name) {
        log.info("UserService::getAllUsers started");

        if (StringUtils.isNotEmpty(name)) {
            List<User> users = userRepository.findUserByName(name);
            return userMapper.mapToUserResponseDtoList(users);
        }

        List<User> userList = userRepository.findAll();

        log.info("UserService::getAllUsers finished");
        return userMapper.mapToUserResponseDtoList(userList);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(long userId) throws UserNotFoundException {
        log.info("UserService::getByUserId started");

        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found: " + userId));

        log.info("UserService::getByUserId finished");
        return userMapper.mapToUserResponseDto(optionalUser.get());
    }

    public UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        log.info("UserService::updateUser started");
        Optional<User> optionalUser = userRepository.findById(userUpdateRequestDto.getUserId());
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with userId: " + userUpdateRequestDto.getUserId()));

        User user = userMapper.mapToUser(userUpdateRequestDto);
        User updatedUser = userRepository.save(user);

        log.info("UserService::updateUser finished");
        return userMapper.mapToUserResponseDto(updatedUser);
    }

    @Transactional
    public void deleteUserById(long userId) throws UserNotFoundException {
        log.info("UserService::deleteUserById started");

        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found with userId: " + userId));

        userRepository.deleteById(userId);

        log.info("UserService::deleteUserById finished");
    }


}
