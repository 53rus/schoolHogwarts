package ru_hogwarts_school.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru_hogwarts_school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> getAllStudentByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findByFaculty_Id(long facultyId);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getNumberOfAllStudent();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    Integer getAverageAgeOfStudent();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getLastFiveStudent();
}
