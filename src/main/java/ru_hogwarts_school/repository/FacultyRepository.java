package ru_hogwarts_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru_hogwarts_school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> getAllFacultyByColor(String color);
}
