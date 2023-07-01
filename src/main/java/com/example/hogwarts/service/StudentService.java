package com.example.hogwarts.service;

import com.example.hogwarts.entity.Avatar;
import com.example.hogwarts.entity.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final AvatarService avatarService;

    public StudentService(StudentRepository studentRepository, AvatarService avatarService) {
        this.studentRepository = studentRepository;
        this.avatarService = avatarService;
    }

    public Student add(Student student) {
        return studentRepository.save(student);
    }

    public Student get(long id) {
        return studentRepository.findById(id).get();
    }

    public Student set(Student student) {
        return studentRepository.save(student);
    }

    public Student del(long id) {
        Student student =studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return student;
    }

    public Collection<Student> ageCollect(int age) {
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> ageBetween(int min,int max){
        return studentRepository.findStudentsByAgeBetween(min,max);
    }

    public Student uploadAvatar(long id, MultipartFile multipartFile) {
        Student student = studentRepository.findById(id)
                        .orElseThrow();
        Avatar avatar = avatarService.create(student,multipartFile);
        student.setAvatarUrl("http://localhost:8080/avatars/" + avatar.getId()+"/from-db");
        return student;

    }
}
