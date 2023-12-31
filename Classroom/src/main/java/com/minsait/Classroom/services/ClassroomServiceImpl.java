package com.minsait.Classroom.services;

import com.minsait.Classroom.clients.StudentClient;
import com.minsait.Classroom.clients.SubjectClient;
import com.minsait.Classroom.clients.TeacherClient;
import com.minsait.Classroom.models.entities.Classroom;
import com.minsait.Classroom.models.entities.StudentClassroom;
import com.minsait.Classroom.repositories.ClassroomRepository;
import com.minsait.Classroom.repositories.StudentClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClassroomServiceImpl implements IClassroomService{
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private StudentClassroomRepository studentClassroomRepository;
    @Autowired
    private SubjectClient subjectClient;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private TeacherClient teacherClient;
    @Override
    @Transactional
    public void save(Classroom classroom) {
        ResponseEntity<?> subjectResponse =  subjectClient.findById(classroom.getSubjectId());
        ResponseEntity<?> teacherResponse = teacherClient.findById(classroom.getTeacherId());
        if (subjectResponse.getStatusCode().is2xxSuccessful() && teacherResponse.getStatusCode().is2xxSuccessful()) {
            classroomRepository.save(classroom);
        }
    }
    @Override
    @Transactional(readOnly = true)
    public Classroom findById(Long id) {
        return classroomRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void addStudent(Long classroomId, Long studentId) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow();
        StudentClassroom studentClassroom = new StudentClassroom();

        ResponseEntity<?> studentResponse = studentClient.findById(studentId);

        if (studentResponse.getStatusCode().is2xxSuccessful()) {
            studentClassroom.setStudentId(studentId);
            studentClassroom.setClassroom(classroom);

            studentClassroomRepository.save(studentClassroom);
            classroom.getStudents().add(studentClassroom);
            classroomRepository.save(classroom);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String,Object> getStudentsIds(Classroom classroom){
        List<Long> studentsIds =  studentClassroomRepository.getAllStudentsByClassroom(classroom);
        ResponseEntity<?> studentsInfoResponse = studentClient.getRequiredStudents(studentsIds);
        if (studentsInfoResponse.getStatusCode().is2xxSuccessful()) {
            return (Map<String, Object>) studentsInfoResponse.getBody();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

}
