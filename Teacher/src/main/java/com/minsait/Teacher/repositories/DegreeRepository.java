package com.minsait.Teacher.repositories;

import com.minsait.Teacher.models.entities.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
}
