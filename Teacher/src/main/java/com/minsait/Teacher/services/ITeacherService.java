package com.minsait.Teacher.services;

import com.minsait.Teacher.models.entities.Teacher;

import java.util.List;

public interface ITeacherService {
    List<Teacher> findAll();
    Teacher findById(Long id);
    void create(Teacher teacher);
    Teacher update(Teacher teacher, Long id);
    void deleteById(Long id);
    Long countBySpeciality(String speciality);
}
