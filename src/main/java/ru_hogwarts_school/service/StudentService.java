package ru_hogwarts_school.service;

import org.springframework.stereotype.Service;
import ru_hogwarts_school.exception.FacultyNotFoundException;
import ru_hogwarts_school.exception.StudentNotFoundException;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.model.Student;
import ru_hogwarts_school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }


    public Student getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return studentRepository.findById(id).get();
        }
        throw new StudentNotFoundException();
    }


    public Student editStudent(Student student) {
        Optional<Student> stu = studentRepository.findById(student.getId());
        if (stu.isPresent()) {
            return studentRepository.save(student);
        }
        throw new StudentNotFoundException();
    }


    public Student deleteStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return student.get();
        }
        throw new StudentNotFoundException();
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }


    public Collection<Student> getAllStudentByAge(int age) {
        Collection<Student> students = studentRepository.getAllStudentByAge(age);
        if (students.isEmpty()) {
            throw new StudentNotFoundException();
        }
        return studentRepository.getAllStudentByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        Collection<Student> students = studentRepository.findByAgeBetween(min, max);
        if (students.isEmpty()) {
            throw new StudentNotFoundException();
        }
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyFromStudent(long id) {
        return getStudent(id).getFaculty();
    }
}
