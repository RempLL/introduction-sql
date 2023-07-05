package com.example.hogwarts.service;

import com.example.hogwarts.entity.Faculty;
import com.example.hogwarts.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty set(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty del(long id) {
        Faculty faculty = facultyRepository.findById(id).get();
        facultyRepository.deleteById(id);
        return faculty;
    }

    public Collection<Faculty> colorCollect(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> colorOrNameCollect(String color, String name) {
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color,name);
    }
}
