package com.example.hogwarts.controller;

import com.example.hogwarts.entity.Student;
import com.example.hogwarts.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("add")
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @GetMapping("get/{id}")
    public Student get(@PathVariable long id) {
        return studentService.get(id);
    }

    @PutMapping("set")
    public Student set(@RequestBody Student student) {
        return studentService.set(student);
    }


    @DeleteMapping("delete/{id}")
    public Student del(@PathVariable long id) {
        return studentService.del(id);
    }

    @GetMapping("collect/{age}")
    public Collection<Student> ageCollect(@PathVariable Integer age) {
            return studentService.ageCollect(age);

    }

    @GetMapping("collect")
    public Collection<Student> ageCollect(@RequestParam int min,@RequestParam int max) {
        return studentService.ageBetween(min,max);
    }

}
