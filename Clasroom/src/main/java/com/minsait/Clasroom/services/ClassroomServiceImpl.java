package com.minsait.Clasroom.services;

import com.minsait.Clasroom.models.dto.StudentProfileDTO;
import com.minsait.Clasroom.models.entities.Classroom;
import com.minsait.Clasroom.models.entities.StudentClassroom;
import com.minsait.Clasroom.repositories.ClassroomRepository;
import com.minsait.Clasroom.repositories.StudentClassroomRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements IClassroomService{

    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Override
    public void save(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Classroom findById(Long id) {
        return classroomRepository.findById(id).orElseThrow();
    }

    @Override
    public void addStudent(Long classroomId, Long studentId) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow();
        StudentClassroom studentClassroom = new StudentClassroom();
        studentClassroom.setStudentId(studentId);
        studentClassroom.setClassroom(classroom);

        studentClassroomRepository.save(studentClassroom);
        classroom.getStudents().add(studentClassroom);
        classroomRepository.save(classroom);
    }

    public List<Long> getStudentsIds(Classroom classroom){
        return studentClassroomRepository.getAllStudentsByClassroom(classroom);
    }

}
