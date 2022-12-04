package com.example.beanvalidation.controller;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.dto.UserUpdateRequestDto;
import com.example.beanvalidation.enums.Gender;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.handler.ResponseHandler;
import com.example.beanvalidation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return ResponseHandler.responseBuilder("user creation successful", HttpStatus.CREATED, userResponseDto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestParam(value = "name", required = false) String name,
                                                             @RequestParam(value = "sortBy", required = false) String sortBy) {
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers(name, sortBy);
        return ResponseHandler.responseBuilder("fetch user list successful", HttpStatus.OK, userResponseDtoList);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
        UserResponseDto userResponseDto = userService.getUserById(userId);
        return ResponseHandler.responseBuilder("fetch by user id successful", HttpStatus.OK, userResponseDto);
    }

    @GetMapping("/users/filter")
    public ResponseEntity<List<UserResponseDto>> getUsersFilterNationality(@RequestParam(value = "nationality") String nationality,
                                                                           @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                           @RequestParam(value = "direction", required = false) String direction) {
        List<UserResponseDto> userResponseDtoList = userService.getUsersNationality(nationality, sortBy, direction);
        return ResponseHandler.responseBuilder("fetch by user nationality successful", HttpStatus.OK, userResponseDtoList);
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserResponseDto>> getUsersGender(@RequestParam(value = "gender") Gender gender,
                                                                @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
                                                                @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
                                                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        List<UserResponseDto> userResponseDtoList = userService.getUsersGender(gender, sortBy, direction, page, pageSize);
        return ResponseHandler.responseBuilder("fetch by user gender successful", HttpStatus.OK, userResponseDtoList);
    }

    @PutMapping("/users")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userUpdateRequestDto);
        return ResponseHandler.responseBuilder("user update successful", HttpStatus.CREATED, userResponseDto);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return ResponseHandler.responseBuilder("deletion by user id successful", HttpStatus.OK);
    }
}
