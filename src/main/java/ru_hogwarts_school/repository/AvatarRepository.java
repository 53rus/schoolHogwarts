package ru_hogwarts_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru_hogwarts_school.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);
}
