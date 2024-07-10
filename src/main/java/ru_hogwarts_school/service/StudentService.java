package ru_hogwarts_school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru_hogwarts_school.exception.FacultyNotFoundException;
import ru_hogwarts_school.exception.StudentNotFoundException;
import ru_hogwarts_school.model.Faculty;
import ru_hogwarts_school.model.Student;
import ru_hogwarts_school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student addStudent(Student student) {
        logger.info("Was invoked method for add student");

        student.setId(null);
        return studentRepository.save(student);
    }


    public Student getStudent(long id) {
        logger.info("Was invoked method for get student by id");
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return studentRepository.findById(id).get();
        }
        logger.error("Student with id {} not found", id);
        throw new StudentNotFoundException();
    }


    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");

        Optional<Student> stu = studentRepository.findById(student.getId());
        if (stu.isPresent()) {
            return studentRepository.save(student);
        }
        logger.error("Student id {}, name {}, age {} not found", student.getId(), student.getName(), student.getAge());
        throw new StudentNotFoundException();
    }


    public Student deleteStudent(long id) {
        logger.info("Was invoked method for delete student by id");

        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return student.get();
        }
        logger.error("Student to be removed with id {} not found", id);
        throw new StudentNotFoundException();
    }

    public Collection<Student> getAllStudent() {
        logger.info("Was invoked method for get all student");

        return studentRepository.findAll();
    }


    public Collection<Student> getAllStudentByAge(int age) {
        logger.info("Was invoked method for get all student by age");

        Collection<Student> students = studentRepository.getAllStudentByAge(age);

        if (students.isEmpty()) {
            logger.error("Students with age {} not found", age);
            throw new StudentNotFoundException();
        }
        return studentRepository.getAllStudentByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("A method was called to get all students in an age group, minimum value of years {}, maximum value of years {}", min, max);

        Collection<Student> students = studentRepository.findByAgeBetween(min, max);

        if (students.isEmpty()) {
            logger.error("Students in the age group from {} years to {} were not found", min, max);
            throw new StudentNotFoundException();
        }
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findFacultyFromStudent(long id) {
        logger.info("Was invoked method for find faculty from student");

        return getStudent(id).getFaculty();
    }

    public Integer getNumberOfAllStudent() {
        logger.info("Was invoked method for get number of all student");

        return studentRepository.getNumberOfAllStudent();
    }

    public Integer getAverageAgeOfStudent() {
        logger.info("Was invoked method for get average age of student");

        return studentRepository.getAverageAgeOfStudent();
    }

    public Collection<Student> getLastFiveStudent() {
        logger.info("Was invoked method for get last five student");

        return studentRepository.getLastFiveStudent();
    }

    public Collection<String> getAllStudentByNameStartedA() {
        logger.info("Was invoked method for get all student whose name begins A");

        return studentRepository.findAll().stream().filter(student -> student.getName().startsWith("А"))
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Double getAverageAgeOfAllStudents() {
        logger.info("Was invoked method for get average age of student by stream");

        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .getAsDouble();
    }
}
