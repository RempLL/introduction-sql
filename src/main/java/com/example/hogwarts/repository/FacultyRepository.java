package com.example.hogwarts.repository;

import com.example.hogwarts.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColor(String color);

    Collection<Faculty> findAllByColorIgnoreCaseOrNameIgnoreCase(String color,String name);
}
