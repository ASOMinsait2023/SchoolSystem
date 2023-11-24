package com.minsait.Subject.services;

import com.minsait.Subject.models.entities.SubjectType;

import java.util.List;

public interface SubjectTypeService {
    List<SubjectType> findAll();
    SubjectType findById(Long id);
    void save(SubjectType subjectType);
}
