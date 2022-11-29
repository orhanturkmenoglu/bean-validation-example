package com.example.beanvalidation.entity;

import com.example.beanvalidation.enums.Gender;
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
    @SequenceGenerator(name = "user_sequence_generator", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_generator")
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;
    private String nationality;
}
