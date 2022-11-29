package com.example.beanvalidation.repository;

import com.example.beanvalidation.entity.User;
import com.example.beanvalidation.enums.Gender;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findByName(String name, PageRequest pageRequest, Sort sort);
    List<User> findByAge(Integer age, Pageable pageable, Sort sort);

    List<User> findByNationalityAndGender(String nationality, Gender gender);

    List<User> findByAge(Integer age);

    List<User> findByNationality(String nationality, Sort sortBy);

    List<User> findByNationality(String nationality);


}
