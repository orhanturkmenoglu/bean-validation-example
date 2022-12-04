package com.example.beanvalidation.service;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.dto.UserUpdateRequestDto;
import com.example.beanvalidation.entity.User;
import com.example.beanvalidation.enums.Gender;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.mapper.UserMapper;
import com.example.beanvalidation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    public List<UserResponseDto> getAllUsers(String name, String sortBy) {
        log.info("UserService::getAllUsers started");

        List<User> userList;
        if (StringUtils.isNotEmpty(name)) {
            userList = userRepository.findByName(name);
            userMapper.mapToUserResponseDtoList(userList);
        }
        if (StringUtils.isNotEmpty(sortBy)) {
            userList = userRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
            userMapper.mapToUserResponseDtoList(userList);
        }

        userList = userRepository.findAll();

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

    @Transactional
    public List<UserResponseDto> getUsersNationality(String nationality, String sortBy, String direction) {
        log.info("UserService::getUsersNationality started::");

        List<User> userList;

        if (StringUtils.isNotEmpty(sortBy) && StringUtils.isNotEmpty(direction)) {
            userList = userRepository.findByNationality(nationality, Sort.by(Sort.Direction.fromString(direction), sortBy));
        } else {
            userList = userRepository.findByNationality(nationality);
        }

        log.info("UserService::getUsersNationality finished");
        return userMapper.mapToUserResponseDtoList(userList);
    }

    @Transactional
    public List<UserResponseDto> getUsersGender(Gender gender, String sortBy, String direction, Integer page, Integer pageSize) {
        List<User> userList = null;
        PageRequest pageRequest = PageRequest.of(page, pageSize);

        if (Objects.nonNull(gender)) {
            userList = userRepository.findByGender(gender);
            userMapper.mapToUserResponseDtoList(userList);
        }
        if (StringUtils.isNotEmpty(sortBy)) {
            userList = userRepository.findByGender(gender, Sort.by(sortBy));
            userMapper.mapToUserResponseDtoList(userList);
        }
        if (StringUtils.isNotEmpty(direction)) {
            userList = userRepository.findByGender(gender, Sort.by(Sort.Direction.fromString(direction), sortBy));
            userMapper.mapToUserResponseDtoList(userList);
        }
        if (Objects.nonNull(pageRequest)) {
            userList = userRepository.findByGender(gender, Sort.by(Sort.Direction.fromString(direction), sortBy), pageRequest);
            userMapper.mapToUserResponseDtoList(userList);
        }

        return userMapper.mapToUserResponseDtoList(userList);
    }

    @Transactional
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
