package ru_hogwarts_school.service;

import org.springframework.stereotype.Service;
import ru_hogwarts_school.exception.FacultyNotFoundException;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.model.Student;
import ru_hogwarts_school.repository.FacultyRepository;
import ru_hogwarts_school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            return facultyRepository.findById(id).get();
        }
        throw new FacultyNotFoundException();
    }

    public Faculty editFaculty(Faculty faculty) {
        Optional<Faculty> facul = facultyRepository.findById(faculty.getId());
        if (facul.isPresent()) {
            return facultyRepository.save(faculty);
        }
        throw new FacultyNotFoundException();
    }

    public Faculty deleteFaculty(long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.deleteById(id);
            return faculty.get();
        }
        throw new FacultyNotFoundException();
    }


    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty findFacultyByColor(String color) {
        Faculty faculty = facultyRepository.findFacultyByColorIgnoreCase(color);
        if (faculty != null) {
            return facultyRepository.findFacultyByColorIgnoreCase(color);
        }
        throw new FacultyNotFoundException();
    }

    public Faculty findFacultyByName(String name) {
        Faculty faculty = facultyRepository.findFacultyByNameIgnoreCase(name);
        if (faculty != null) {
            return facultyRepository.findFacultyByNameIgnoreCase(name);
        }
        throw new FacultyNotFoundException();
    }

    public Collection<Student> findStudentsFromFaculty(long id) {
        return studentRepository.findByFaculty_Id(id);
    }
}
