package com.example.beanvalidation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name = "Admin";
    private String email = "admin@example.com";
    private String phoneNumber = "5417679626";
    private String gender = "Male";
    private int age = 18;
    private String nationality = "Turk";

}
