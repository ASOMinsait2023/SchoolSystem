package com.minsait.Subject.services;

import com.minsait.Subject.models.entities.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> findAll();
    Subject findById(Long id);
    void save(Subject subject);
    void update(Subject subject);
    void delete(Long id);
}