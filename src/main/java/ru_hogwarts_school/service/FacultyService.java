package ru_hogwarts_school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru_hogwarts_school.exception.FacultyNotFoundException;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.model.Student;
import ru_hogwarts_school.repository.FacultyRepository;
import ru_hogwarts_school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for add faculty");

        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        logger.info("Was invoked method for get faculty");

        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            return facultyRepository.findById(id).get();
        }
        logger.error("There is not faculty with id {}" , id);
        throw new FacultyNotFoundException();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");

        Optional<Faculty> facul = facultyRepository.findById(faculty.getId());
        if (facul.isPresent()) {
            return facultyRepository.save(faculty);
        }
        logger.error("Faculty id {}, name {}, color {} not found", faculty.getId(), faculty.getName(), faculty.getColor());
        throw new FacultyNotFoundException();
    }

    public Faculty deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty by id");

        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.deleteById(id);
            return faculty.get();
        }
        logger.error("Faculty with id {}, not found", id);
        throw new FacultyNotFoundException();
    }


    public Collection<Faculty> getAllFaculty() {
        logger.info("Was invoked method for get all faculty");

        return facultyRepository.findAll();
    }

    public Faculty findFacultyByColor(String color) {
        logger.info("Was invoked method for find faculty by color");

        Faculty faculty = facultyRepository.findFacultyByColorIgnoreCase(color);
        if (faculty != null) {
            return facultyRepository.findFacultyByColorIgnoreCase(color);
        }
        logger.error("Faculty with color {}, not found", color);
        throw new FacultyNotFoundException();
    }

    public Faculty findFacultyByName(String name) {
        logger.info("Was invoked method for find faculty by name");

        Faculty faculty = facultyRepository.findFacultyByNameIgnoreCase(name);
        if (faculty != null) {
            return facultyRepository.findFacultyByNameIgnoreCase(name);
        }
        logger.error("Faculty with name {}, not found", name);
        throw new FacultyNotFoundException();
    }

    public Collection<Student> findStudentsFromFaculty(long id) {
        logger.info("Was invoked method for find student from faculty");

        return studentRepository.findByFaculty_Id(id);
    }
}
