package com.minsait.Clasroom.services;

import com.minsait.Clasroom.models.entities.Classroom;

import java.util.List;

public interface IClassroomService {

    void save(Classroom classroom);
    Classroom findById(Long id);
    void addStudent(Long classroomId, Long studentId);

    List<Long> getStudentsIds(Classroom classroom);

}
