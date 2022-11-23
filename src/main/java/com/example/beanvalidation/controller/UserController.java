
package com.example.beanvalidation.controller;

import com.example.beanvalidation.dto.UserRequestDTO;
import com.example.beanvalidation.dto.UserResponseDTO;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/api/v1/users"})
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = this.userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = this.userService.getAllUsers();
        return ResponseEntity.ok().body(userResponseDTOList);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<UserResponseDTO> getByUserId(@PathVariable("id") long userId) throws UserNotFoundException {
        UserResponseDTO userResponseDTO = this.userService.getByUserId(userId);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    public UserController(final UserService userService) {
        this.userService = userService;
    }
}
