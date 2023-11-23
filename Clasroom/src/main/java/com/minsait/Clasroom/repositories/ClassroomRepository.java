package com.minsait.Clasroom.repositories;

import com.minsait.Clasroom.models.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
