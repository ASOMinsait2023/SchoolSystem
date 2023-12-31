package com.minsait.Subject.respositories;

import com.minsait.Subject.models.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findAllBySubjectTypeId(Long id);
}
