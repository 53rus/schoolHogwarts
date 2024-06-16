package ru_hogwarts_school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.model.Student;
import ru_hogwarts_school.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {

        return ResponseEntity.ok(facultyService.getAllFaculty());
    }


    @GetMapping("/color")
    public ResponseEntity<Faculty> getFacultyByColor(@RequestParam String color) {

        return ResponseEntity.ok(facultyService.findFacultyByColor(color));
    }


    @GetMapping("/name")
    public ResponseEntity<Faculty> getFacultyByName(@RequestParam String name) {

        return ResponseEntity.ok(facultyService.findFacultyByName(name));
    }


    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty editFaculty = facultyService.editFaculty(faculty);
        if (editFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(editFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.deleteFaculty(id);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/{id}/students")
    public Collection<Student> findStudentsFromFaculty(@PathVariable long id) {
        return facultyService.findStudentsFromFaculty(id);
    }
}

