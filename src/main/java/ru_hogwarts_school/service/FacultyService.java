package ru_hogwarts_school.service;

import org.springframework.stereotype.Service;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService{

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {

        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getAllFacultyByColor(String color) {
        return facultyRepository.getAllFacultyByColor(color);
    }

}
