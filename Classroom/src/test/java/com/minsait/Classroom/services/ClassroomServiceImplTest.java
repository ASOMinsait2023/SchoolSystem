package com.minsait.Classroom.services;

import com.minsait.Classroom.Datos;
import com.minsait.Classroom.clients.StudentClient;
import com.minsait.Classroom.clients.SubjectClient;
import com.minsait.Classroom.clients.TeacherClient;
import com.minsait.Classroom.models.entities.Classroom;
import com.minsait.Classroom.models.entities.StudentClassroom;
import com.minsait.Classroom.repositories.ClassroomRepository;
import com.minsait.Classroom.repositories.StudentClassroomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceImplTest {

    @Mock
    private ClassroomRepository classroomRepository;
    @Mock
    private StudentClassroomRepository studentClassroomRepository;
    @Mock
    private SubjectClient subjectClient;
    @Mock
    private StudentClient studentClient;
    @Mock
    private TeacherClient teacherClient;
    @InjectMocks
    private ClassroomServiceImpl classroomService;

    @Test
    void testSaveClassroom() {
        //given
        Classroom classroom = Datos.getClassroom();
        classroom.setSubjectId(1L);
        classroom.setTeacherId(1L);
        when(subjectClient.findById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(teacherClient.findById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        //when
        classroomService.save(classroom);
        //then
        verify(classroomRepository, times(1)).save(classroom);
    }

    @Test
    void testSaveClassroomWithNonExistSubject() {
        //given
        Classroom classroom = Datos.getClassroom();
        classroom.setSubjectId(1L);
        classroom.setTeacherId(1L);
        when(subjectClient.findById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        when(teacherClient.findById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        //when
        classroomService.save(classroom);
        //then
        verify(classroomRepository, times(0)).save(classroom);
    }

    @Test
    void testSaveClassroomWithNonExistTeacher() {
        //given
        Classroom classroom = Datos.getClassroom();
        classroom.setSubjectId(1L);
        classroom.setTeacherId(1L);
        when(subjectClient.findById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(teacherClient.findById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        //when
        classroomService.save(classroom);
        //then
        verify(classroomRepository, times(0)).save(classroom);
    }

    @Test
    void testFinById() {
        //given
        Classroom classroom = Datos.getClassroom();
        classroom.setName("Programacion-101");
        when(classroomRepository.findById(1L)).thenReturn(Optional.of(classroom));
        //when
        Classroom result = classroomService.findById(1L);
        //then
        assertEquals("Programacion-101", result.getName());
    }

    @Test
    void testFindNonExistId() {
        when(classroomRepository.findById(anyLong())).thenThrow(NoSuchElementException.class);
        //when
        assertThrows(NoSuchElementException.class, ()->{
            Classroom result = classroomService.findById(1L);
        });
    }

    @Test
    void testAddStudent() {
        //given
        Long classroomId = 1L;
        Classroom classroom = Datos.getClassroom();
        StudentClassroom student = new StudentClassroom(
                Datos.getStudentClassroom().getId(),
                Datos.getStudentClassroom().getStudentId(),
                Datos.getStudentClassroom().getClassroom()
        );
        when(classroomRepository.findById(1L)).thenReturn(Optional.of(classroom));
        when(studentClient.findById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        //when
        classroomService.addStudent(classroomId, student.getStudentId());
        //then
        verify(studentClassroomRepository, times(1)).save(any(StudentClassroom.class));
        verify(classroomRepository, times(1)).save(classroom);
    }

    @Test
    void testAddNonExistStudent() {
        Long classroomId = 1L;
        Long studentId = 10L;
        Classroom classroom = Datos.getClassroom();
        when(classroomRepository.findById(1L)).thenReturn(Optional.of(classroom));
        when(studentClient.findById(10L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        //when
        classroomService.addStudent(classroomId, studentId);
        //then
        verify(studentClassroomRepository, times(0)).save(any(StudentClassroom.class));
        verify(classroomRepository, times(0)).save(classroom);

    }

    @Test
    void testGetStudents() {
        //given
        Classroom classroom = Datos.getClassroom();
        List<Long> studentsIds = List.of(1L, 2L);
        when(studentClassroomRepository.getAllStudentsByClassroom(classroom)).thenReturn(studentsIds);
        when(studentClient.getRequiredStudents(studentsIds)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        //when
        Map<String, Object> result = classroomService.getStudentsIds(classroom);
        //then
        assertNull(result);
    }

    @Test
    void testGetNonExistStudents() {
        //given
        Classroom classroom = Datos.getClassroom();
        List<Long> studentsIds = List.of(1L, 2L);
        when(studentClassroomRepository.getAllStudentsByClassroom(classroom)).thenReturn(studentsIds);
        when(studentClient.getRequiredStudents(studentsIds)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        //when
        Map<String, Object> result = classroomService.getStudentsIds(classroom);
        //then
        assertNull(result);
    }

    @Test
    void testFindAll() {
        //given
        List<Classroom> classrooms = List.of(Datos.getClassroom(), Datos.getClassroom());
        when(classroomRepository.findAll()).thenReturn(classrooms);
        //when
        List<Classroom> result = classroomService.findAll();
        //then
        assertEquals(2, result.size());
    }
}