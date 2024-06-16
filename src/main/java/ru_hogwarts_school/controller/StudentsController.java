package ru_hogwarts_school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.model.Student;
import ru_hogwarts_school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentsController {

    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentId(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/age")
    public Collection<Student> getAllStudentByAge(@RequestParam Integer age) {
        return studentService.getAllStudentByAge(age);
    }

    @GetMapping
    public Collection<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/ageBetween")
    public Collection<Student> findByAgeBetween(@RequestParam Integer min,
                                                @RequestParam Integer max) {
        return studentService.findByAgeBetween(min, max);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(editStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student student = studentService.deleteStudent(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/faculty")
    public Faculty findFacultyFromStudent(@PathVariable long id) {
        return studentService.findFacultyFromStudent(id);
    }

}
