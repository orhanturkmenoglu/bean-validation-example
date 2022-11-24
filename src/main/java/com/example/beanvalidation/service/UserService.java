package com.example.beanvalidation.service;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.entity.User;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.mapper.UserMapper;
import com.example.beanvalidation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        User mapToUser = this.userMapper.mapToUser(userRequestDTO);
        User save = this.userRepository.save(mapToUser);
        log.info("UserService::createUser finished");
        return this.userMapper.mapToUserResponseDto(save);
    }

    public List<UserResponseDto> getAllUsers() {
        log.info("UserService::getAllUsers started");
        List<User> userList = this.userRepository.findAll();
        log.info("UserService::getAllUsers finished");
        return this.userMapper.mapToUserResponseDtoList(userList);
    }

    public UserResponseDto getUserById(long userId) throws UserNotFoundException {
        log.info("UserService::getByUserId started");
        Optional<User> optionalUser = this.userRepository.findById(userId);
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
        log.info("UserService::getByUserId finished");
        return this.userMapper.mapToUserResponseDto(optionalUser.get());
    }

    @Transactional
    public void deleteUserById(long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.orElseThrow(() -> new UserNotFoundException("User not found: " + userId));
        userRepository.deleteById(userId);
    }

}
