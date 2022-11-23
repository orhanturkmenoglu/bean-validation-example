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
    private @NotBlank(message = "username shouldn't be null or empty")
    String name;
    private @Email(message = "invalid email address")
    String email;
    private @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered")
    String mobile;
    private String gender;
    @Min(18L) @Max(65L)
    private int age;
    @NotBlank
    String nationality;
}
