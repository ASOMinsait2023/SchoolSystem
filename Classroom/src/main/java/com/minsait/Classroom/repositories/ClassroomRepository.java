package com.minsait.Classroom.repositories;

import com.minsait.Classroom.models.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
