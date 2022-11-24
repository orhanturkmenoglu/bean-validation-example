package com.example.beanvalidation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(message = "username must not be null or empty")
    private String name;
    @Email(message = "invalid email address")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "invalid phone number")
    private String phoneNumber;
    private String gender;
    @Min(value = 18L,message = "age must be minimum value 18 ")
    @Max(value = 65L,message = "age must be maximum value 65")
    private int age;
    @NotBlank (message = "nationality must not be null or empty")
    String nationality;
}
