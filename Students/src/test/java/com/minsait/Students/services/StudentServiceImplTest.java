package com.minsait.Students.services;

import com.minsait.Students.Datos;
import com.minsait.Students.models.entities.Career;
import com.minsait.Students.models.entities.Student;
import com.minsait.Students.repositories.CareerRepository;
import com.minsait.Students.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CareerRepository careerRepository;
    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void testGetAll(){
        //given
        int expectedSize = 1;
        when(studentRepository.findAll()).thenReturn(Datos.getStudentList());
        //when
        int realSize = studentService.getAll().size();
        //then
        assertEquals(expectedSize, realSize);
    }

    @Test
    void testGetById(){
        //given
        Long id = 1L;
        when(studentRepository.findById(id)).thenReturn(Optional.of(Datos.getStudent()));
        //when
        Student student = studentService.getById(id);
        //then
        assertEquals(id, student.getId());
    }

    @Test
    void testSave(){
        //given
        Student student = Datos.getStudent();
        when(studentRepository.save(student)).thenReturn(student);
        //when
        studentService.save(student);
        //then
        verify(studentRepository).save(student);
    }

    @Test
    void testDelete() {
        //given
        Long id = 1L;
        when(studentRepository.findById(id)).thenReturn(Optional.of(Datos.getStudent()));
        //when
        studentService.delete(id);
        //then
        verify(studentRepository).deleteById(id);
    }

    @Test
    void testDeleteNonExistStudent() {
        //given
        Long id = 1L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());
        //when
        boolean result = studentService.delete(id);
        //then
        assertFalse(result);
    }

    @Test
    void testAddCareerToStudent() {
        //given
        Student student = Datos.getStudent();
        Long studentId = 1L;
        String careerName = "Programacion";
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(careerRepository.findByName(careerName)).thenReturn(Optional.of(Datos.getCareer()));
        //when
        studentService.addStudentToCareer(studentId, careerName);
        //then
        verify(studentRepository).save(student);
        assertEquals(careerName, student.getCareer().getName());
    }

    @Test
    void testFailsAddStudentToCareer(){
        //given
        Student student = Datos.getStudent();
        Long studentId = 1L;
        String careerName = "non exist";
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(careerRepository.findByName(careerName)).thenReturn(Optional.empty());
        //when - then
        Exception exception = assertThrows(NoSuchElementException.class, ()-> {
            studentService.addStudentToCareer(studentId, careerName);
        });
        assertEquals("one or both elements not found", exception.getMessage());
    }


    @Test
    void testCheckStudentProgress() {
        //given
        Student student = Datos.getStudent();
        student.setActualCredits(250);
        Long studentId = 1L;
        String careerName = "Programacion";
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(careerRepository.findByName(careerName)).thenReturn(Optional.of(Datos.getCareer2()));
        //when
        String message = studentService.checkStudentProgress(studentId, careerName);
        //then
        assertEquals("The progress is: 25.0%", message);
    }

    @Test
    void testFailsCheckStudentProgress(){
        //given
        Student student = Datos.getStudent();
        Long studentId = 1L;
        String careerName = "non exist";
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(careerRepository.findByName(careerName)).thenReturn(Optional.empty());
        //when - then
        Exception exception = assertThrows(NoSuchElementException.class, ()-> {
            studentService.checkStudentProgress(studentId, careerName);
        });
        assertEquals("one or both elements not found", exception.getMessage());
    }

    @Test
    void testGetStudentsInformation() {
        //given
        List<Long> requiredStudents = Arrays.asList(1L);
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(Datos.getStudent()));
        //when
        List<Student> students = studentService.getStudentsInformation(requiredStudents);
        //then
        assertEquals(1, students.size());
    }


}