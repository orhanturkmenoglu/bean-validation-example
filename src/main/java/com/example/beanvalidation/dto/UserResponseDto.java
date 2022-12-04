package com.example.beanvalidation.dto;

import com.example.beanvalidation.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto implements Serializable {
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private int age;
    private String city;
    private String nationality;
}
