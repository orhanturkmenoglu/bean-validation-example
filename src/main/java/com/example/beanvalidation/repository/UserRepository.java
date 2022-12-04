package com.example.beanvalidation.repository;

import com.example.beanvalidation.entity.User;
import com.example.beanvalidation.enums.Gender;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);

    List<User> findByNationality(String nationality, Sort sortBy);

    List<User> findByNationality(String nationality);

    List<User> findByGender(Gender gender);

    List<User> findByGender(Gender gender, Sort sortBy);

    List<User> findByGender(Gender gender, Sort sortBy, PageRequest pageRequest);


}
