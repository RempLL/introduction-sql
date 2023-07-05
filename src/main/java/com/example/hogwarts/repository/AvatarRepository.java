package com.example.hogwarts.repository;

import com.example.hogwarts.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudent_id(long studentId);

    @Query(value = "SELECT * FROM avatar",nativeQuery = true)
    List<Avatar> getAll();
}
