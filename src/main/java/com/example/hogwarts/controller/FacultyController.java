package com.example.hogwarts.controller;

import com.example.hogwarts.entity.Faculty;
import com.example.hogwarts.service.FacultyService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("add")
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @GetMapping("get/{id}")
    public Faculty get(@PathVariable long id) {
        return facultyService.get(id);
    }

    @PutMapping("set")
    public Faculty set(@RequestBody Faculty faculty) {
        return facultyService.set(faculty);
    }

    @DeleteMapping("delete/{id}")
    public Faculty del(@PathVariable long id) {
        return facultyService.del(id);
    }

    @GetMapping("collect/{color}")
    public Collection<Faculty> colorCollect(@PathVariable String color) {
        return facultyService.colorCollect(color);
    }

    @GetMapping("collect")
    public Collection<Faculty> colorOrNameCollect(@RequestParam(required = false) String color,@RequestParam(required = false)String name) {
        return facultyService.colorOrNameCollect(color,name);
    }

}
