package com.minsait.Clasroom.repositories;

import com.minsait.Clasroom.models.entities.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {
}
