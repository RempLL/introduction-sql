package com.example.hogwarts.service;

import com.example.hogwarts.entity.Faculty;
import com.example.hogwarts.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        LOGGER.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        LOGGER.info("Was invoked method for get faculty with id = {}", id);
        return facultyRepository.findById(id).get();
    }

    public Faculty set(Faculty faculty) {
        LOGGER.info("Was invoked method for set faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty del(long id) {
        LOGGER.info("Was invoked method for delete faculty with id {}",id);
        Faculty faculty = facultyRepository.findById(id).get();
        facultyRepository.deleteById(id);
        return faculty;
    }

    public Collection<Faculty> colorCollect(String color) {
        LOGGER.info("Was invoked method for colorCollect faculty");
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> colorOrNameCollect(String color, String name) {
        LOGGER.info("Was invoked method for colorOrNameCollect faculty");
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color,name);
    }
}
