package com.minsait.Students.services;

import com.minsait.Students.models.entities.Student;

import java.util.List;

public interface IStudentService {

    void save(Student student);
    Student getById(Long id);
    List<Student> getAll();
    boolean delete(Long id);
    void addStudentToCareer(Long studentId, String careerName);
    String checkStudentProgress(Long studentId, String careerName);

    List<Student> getStudentsInformation(List<Long> requiredStudents);

}
