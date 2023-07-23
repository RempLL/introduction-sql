package com.example.hogwarts.service;

import com.example.hogwarts.entity.Student;
import com.example.hogwarts.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        LOGGER.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student get(long id) {
        LOGGER.info("Was invoked method for get student with id {}",id);
        return studentRepository.findById(id).get();
    }

    public Student set(Student student) {
        LOGGER.info("Was invoked method for update student");
        return studentRepository.save(student);
    }

    public Student del(long id) {
        LOGGER.info("Was invoked method for delete student with id {}",id);
        Student student =studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return student;
    }

    public Collection<Student> ageCollect(int age) {
        LOGGER.info("Was invoked method for get student with age {}",age);
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> ageBetween(int min,int max){
        LOGGER.info("Was invoked method for get student between min {}, max {}",min,max);
        return studentRepository.findStudentsByAgeBetween(min,max);
    }

    public int getCountStudent() {
        LOGGER.info("Was invoked method for getCountStudent student");
        return studentRepository.getCountAvatar();
    }

    public int getAvgAge() {
        LOGGER.info("Was invoked method for getAvgAge student");
        return studentRepository.getAvgAge();
    }

    public List<Student> getLastFive() {
        LOGGER.info("Was invoked method for getLastFive student");
        return studentRepository.getLastFive();
    }

    public List<String> nameStartWithA() {
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name -> name.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double avgAgeStream() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow();
    }
}
