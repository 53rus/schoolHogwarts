package ru_hogwarts_school.service;

import ru_hogwarts_school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    Faculty deleteFaculty(long id);

    public Collection<Faculty> getAllFaculty();

    public Collection<Faculty> getAllFacultyByColor(String color);
}
