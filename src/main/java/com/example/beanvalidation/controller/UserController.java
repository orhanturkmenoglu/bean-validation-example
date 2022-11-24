package com.example.beanvalidation.controller;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDTO) {
        UserResponseDto userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestParam(value = "username",required = false) String userName) {
        List<UserResponseDto> userResponseDTOList = userService.getAllUsers(userName);
        return ResponseEntity.ok().body(userResponseDTOList);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
        UserResponseDto userResponseDTO = userService.getUserById(userId);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
