package com.minsait.Clasroom.services;

import com.minsait.Clasroom.models.entities.Classroom;

public interface IClassroomService {

    void save(Classroom classroom);
    Classroom findById(Long id);
    void addStudent(Long classroomId, Long studentId);
    void addSubject(Long classroomId, Long subjectId);
    void addTeacher(Long classroomId, Long teacherId);

}
