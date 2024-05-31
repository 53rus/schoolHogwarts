package ru_hogwarts_school.service;

import org.springframework.stereotype.Service;
import ru_hogwarts_school.model.Student;
import ru_hogwarts_school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student getStudent(long id) {
        return studentRepository.findById(id).get();
    }


    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }


    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }


    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }


    public Collection<Student> getAllStudentByAge(int age) {
        return studentRepository.getAllStudentByAge(age);
    }


}
