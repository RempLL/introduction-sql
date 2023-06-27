package com.example.hogwarts.repository;

import com.example.hogwarts.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findStudentsByAgeBetween(int min,int max);

    Collection<Student> findAllByAge(int age);

}
