package com.minsait.Teacher.services;

import com.minsait.Teacher.controllers.Data;
import com.minsait.Teacher.models.entities.Teacher;
import com.minsait.Teacher.repositories.DegreeRepository;
import com.minsait.Teacher.repositories.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private DegreeRepository degreeRepository;
    @InjectMocks
    private TeacherServiceImpl teacherService;
    @Captor
    ArgumentCaptor<Teacher> teacherArgumentCaptor;

    @Captor
    ArgumentCaptor<Long> idArgumentCaptor;

    @Test
    void testFindAll(){
        when(teacherRepository.findAll()).thenReturn(List.of(Data.createTeacher1().get(), Data.createTeacher2().get()));

        List<Teacher> teachers=teacherService.findAll();

        assertEquals(2, teachers.size());
        assertEquals("Mario", teachers.get(0).getFirstName());
    }

    @Test
    void testFindById(){
        when(teacherRepository.findById(anyLong())).thenReturn(Data.createTeacher1());

        Teacher teacher=teacherService.findById(1L);

        assertEquals(1L, teacher.getId());
        assertEquals("Mario", teacher.getFirstName());

    }

    @Test
    void testCreate(){
        when(degreeRepository.findById(anyLong())).thenReturn(Data.createDegree());
        Teacher teacher= Data.createTeacher1().get();

        teacherService.create(teacher);

        verify(teacherRepository).save(teacherArgumentCaptor.capture());
        assertEquals(1L, teacherArgumentCaptor.getValue().getId());
        assertEquals("Mario", teacherArgumentCaptor.getValue().getFirstName());
        assertEquals(1L, teacherArgumentCaptor.getValue().getDegree().getId());
    }

    @Test
    void testUpdate(){
        when(teacherRepository.findById(anyLong())).thenReturn(Data.createTeacher1());
        when(teacherRepository.save(any())).thenReturn(Data.createTeacher2().get());
        when(degreeRepository.findById(anyLong())).thenReturn(Data.createDegree2());

        Teacher teacher=teacherService.update(Data.createTeacher2().get(), 1L);

        assertEquals("Juan", teacher.getFirstName());
        assertEquals("Perez", teacher.getLastName());
        assertEquals(2L, teacher.getDegree().getId());
    }

    @Test
    void testDelete(){
        Long id=2L;

        teacherService.deleteById(id);

        verify(teacherRepository).deleteById(idArgumentCaptor.capture());
        assertEquals(id, idArgumentCaptor.getValue());
    }

    @Test
    void testCountBySpeciality(){
        when(teacherRepository.findAll()).thenReturn(List.of(Data.createTeacher1().get(), Data.createTeacher2().get()));

        Long count= teacherService.countBySpeciality("Mathematics");

        assertEquals(1, count);
    }
}