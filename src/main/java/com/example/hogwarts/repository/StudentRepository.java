package com.example.hogwarts.repository;

import com.example.hogwarts.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findStudentsByAgeBetween(int min,int max);

    Collection<Student> findAllByAge(int age);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getCountAvatar();
    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Integer getAvgAge();
    @Query(value = "SELECT * FROM (SELECT * FROM student ORDER BY id DESC LIMIT 5) t ORDER BY id;",nativeQuery = true)
    List<Student> getLastFive();
}
