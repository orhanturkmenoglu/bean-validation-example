package com.example.beanvalidation.dto;

import com.example.beanvalidation.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequestDto {

    @NotNull(message = "User id must not be null")
    private Long userId;

    @NotBlank(message = "Username must not be null or empty")
    private String name;

    @NotBlank(message = "Email must not be null or empty")
    @Email(message = "Email is not valid ", regexp = "^(.+)@(.+)$")
    private String email;

    @NotBlank(message = "Phone number must not be null or empty")
    @Pattern(regexp = "^(\\+)?(90)?(5\\d{2})(\\d{3})(\\d{2})(\\d{2})$", message = "Phone Number is not valid ")
    private String phoneNumber;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @Min(value = 18L, message = "Age must be minimum value 18 ")
    @Max(value = 65L, message = "Age must be maximum value 65")
    private int age;

    @Builder.Default
    private String nationality = "TR";
}
