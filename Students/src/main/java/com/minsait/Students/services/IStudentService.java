package com.minsait.Students.services;

import com.minsait.Students.models.entities.Student;

import java.util.List;

public interface IStudentService {

    void save(Student student);
    Student findById(Long id);
    List<Student> getAll();
    boolean delete(Long id);
    void addCareerToStudent(Long studentId, String careerName);
}
