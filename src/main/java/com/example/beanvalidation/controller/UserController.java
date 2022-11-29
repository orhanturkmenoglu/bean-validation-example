package com.example.beanvalidation.controller;

import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.dto.UserUpdateRequestDto;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "sortBy", required = false) String sortBy) {
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers(name, sortBy);
        return ResponseEntity.ok().body(userResponseDtoList);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
        UserResponseDto userResponseDto = userService.getUserById(userId);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @GetMapping("/users/filter")
    public ResponseEntity<List<UserResponseDto>> getUsersFilter(@RequestParam(value = "age", required = false) Integer age,
                                                                @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                                                @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                @RequestParam(value = "page", defaultValue = "1") int page,
                                                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        List<UserResponseDto> userResponseDtoList = userService.getUsers(age, sortBy, direction, page, pageSize);
        return ResponseEntity.ok().body(userResponseDtoList);
    }

    @GetMapping("/users/")
    public ResponseEntity<List<UserResponseDto>> getUsersNationalityFilter(@RequestParam(value = "nationality") String nationality,
                                                                           @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                           @RequestParam(value = "direction", required = false) String direction) {
        List<UserResponseDto> userResponseDtoList = userService.getUsersNationality(nationality, sortBy, direction);
        return ResponseEntity.ok().body(userResponseDtoList);
    }

    @PutMapping("/users")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
