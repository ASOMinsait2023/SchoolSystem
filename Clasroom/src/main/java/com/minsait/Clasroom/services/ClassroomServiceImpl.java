package com.minsait.Clasroom.services;

import com.minsait.Clasroom.models.entities.Classroom;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl implements IClassroomService{
    @Override
    public void save(Classroom classroom) {

    }

    @Override
    public Classroom findById(Long id) {
        return null;
    }

    @Override
    public void addStudent(Long classroomId, Long studentId) {

    }

    @Override
    public void addSubject(Long classroomId, Long subjectId) {

    }

    @Override
    public void addTeacher(Long classroomId, Long teacherId) {

    }
}
