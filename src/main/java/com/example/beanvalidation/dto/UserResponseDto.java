package com.example.beanvalidation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private int age;
    private String nationality;
}
