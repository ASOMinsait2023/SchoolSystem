package com.minsait.Classroom.services;

import com.minsait.Classroom.models.entities.Classroom;

import java.util.List;
import java.util.Map;

public interface IClassroomService {

    void save(Classroom classroom);
    Classroom findById(Long id);
    void addStudent(Long classroomId, Long studentId);
    Map<String,Object> getStudentsIds(Classroom classroom);
    void addSubject(Long idClassroom, Long idSubject);
    List<Classroom> findAll();
}
