package com.minsait.Students.repositories;

import com.minsait.Students.models.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<Career> findByName(String name);

}
