package com.example.beanvalidation.controller;

import com.example.beanvalidation.constants.UserMessage;
import com.example.beanvalidation.dto.UserRequestDto;
import com.example.beanvalidation.dto.UserResponseDto;
import com.example.beanvalidation.dto.UserUpdateRequestDto;
import com.example.beanvalidation.enums.Gender;
import com.example.beanvalidation.exception.SuccessResponse;
import com.example.beanvalidation.exception.UserNotFoundException;
import com.example.beanvalidation.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Users ", description = "Users related resources")
@OpenAPIDefinition(info = @Info(
        description = "Users related resources",
        contact = @Contact(
                name = "mail service",
                email = "orhantrkmn749@gmail.com"
        ),
        title = "User api documentation",
        version = "1.0"
))
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create a new user",
            description = " Operation to Create a new user",
            responses = {
                    @ApiResponse(
                            description = "Created new user",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad request for new  user", responseCode = "400")
            }
    )
    @PostMapping("/users")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userRequestDto);
        return SuccessResponse.responseBuilder(UserMessage.CREATE_USER, HttpStatus.CREATED, userResponseDto);
    }

    @Operation(
            summary = "Fetch all users",
            description = "Fetch all users",
            responses = {
                    @ApiResponse(
                            description = "Successful getting all users",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad request for users", responseCode = "400")
            }
    )
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(@RequestParam(value = "name", required = false)
                                                             @Parameter(description = "User name ", example = "Orhan") String name,
                                                             @RequestParam(value = "sortBy", required = false)
                                                             @Parameter(description = "User sortBy ", example = "name") String sortBy) {
        List<UserResponseDto> userResponseDtoList = userService.getAllUsers(name, sortBy);
        return SuccessResponse.responseBuilder(UserMessage.GET_ALL_USER, HttpStatus.OK, userResponseDtoList);

    }

    @Operation(
            summary = "Fetch  user by id ",
            description = "Fetch user by id ",
            responses = {
                    @ApiResponse(
                            description = "Fetch user by id successful",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad request for users", responseCode = "400")
            }
    )
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId")
                                                       @Parameter(description = "User id ", example = "1") long userId) throws UserNotFoundException {
        UserResponseDto userResponseDto = userService.getUserById(userId);
        return SuccessResponse.responseBuilder(UserMessage.GET_USER_BY_ID, HttpStatus.OK, userResponseDto);
    }


    @Operation(
            summary = "Fetch  user  nationality  ",
            description = "Fetch user nationality or filter ",
            responses = {
                    @ApiResponse(
                            description = "User nationality successfully fetched",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad request for users", responseCode = "400")
            }
    )
    @GetMapping("/users/filter")
    public ResponseEntity<List<UserResponseDto>> getUserNationality(@RequestParam(value = "nationality") String nationality,
                                                                    @RequestParam(value = "sortBy", required = false) String sortBy,
                                                                    @RequestParam(value = "direction", required = false) String direction) {
        List<UserResponseDto> userResponseDtoList = userService.getUsersNationality(nationality, sortBy, direction);
        return SuccessResponse.responseBuilder(UserMessage.GET_USER_NATIONALITY, HttpStatus.OK, userResponseDtoList);
    }


    @Operation(
            summary = "Fetch  user  gender  ",
            description = "Fetch user gender or search ",
            responses = {
                    @ApiResponse(
                            description = "User gender successfully fetched",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad request for users", responseCode = "400")
            }
    )
    @GetMapping("/users/search")
    public ResponseEntity<List<UserResponseDto>> getUsersGender(@RequestParam(value = "gender") Gender gender,
                                                                @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
                                                                @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction,
                                                                @RequestParam(value = "page", required = false) Integer page,
                                                                @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        List<UserResponseDto> userResponseDtoList = userService.getUsersGender(gender, sortBy, direction, page, pageSize);
        return SuccessResponse.responseBuilder(UserMessage.GET_USER_GENDER, HttpStatus.OK, userResponseDtoList);
    }


    @Operation(
            summary = "Update new user  ",
            description = "Update user ",
            responses = {
                    @ApiResponse(
                            description = "Successfully updated the new user",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad request for users", responseCode = "400")
            }
    )
    @PutMapping("/users")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userUpdateRequestDto);
        return SuccessResponse.responseBuilder(UserMessage.UPDATE_USER, HttpStatus.CREATED, userResponseDto);
    }

    @Operation(
            summary = "Delete user by id  ",
            description = "Delete user by id ",
            responses = {
                    @ApiResponse(
                            description = "Successfully user deleted",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad request for users", responseCode = "400")
            }
    )
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
        return SuccessResponse.responseBuilder(UserMessage.DELETE_USER_BY_ID, HttpStatus.OK);
    }
}
