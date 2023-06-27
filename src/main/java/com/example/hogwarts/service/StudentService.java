package com.example.hogwarts.service;

import com.example.hogwarts.entity.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
}
