package com.minsait.Teacher.repositories;

import com.minsait.Teacher.models.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
