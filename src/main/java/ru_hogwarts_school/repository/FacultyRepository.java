package ru_hogwarts_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findFacultyByColorIgnoreCase(String color);

    Faculty findFacultyByNameIgnoreCase(String name);

   // Collection<Student> findStudentsFromFaculty(long id);

}
