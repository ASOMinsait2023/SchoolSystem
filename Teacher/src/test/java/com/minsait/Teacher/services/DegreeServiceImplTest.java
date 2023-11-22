package com.minsait.Teacher.services;

import static org.junit.jupiter.api.Assertions.*;

import com.minsait.Teacher.controllers.Data;
import com.minsait.Teacher.models.entities.Degree;
import com.minsait.Teacher.repositories.DegreeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DegreeServiceImplTest {

    @Mock
    private DegreeRepository degreeRepository;
    @InjectMocks
    private DegreeServiceImpl degreeService;
    @Captor
    ArgumentCaptor<Degree> degreeArgumentCaptor;

    @Test
    void testFindAll(){
        when(degreeRepository.findAll()).thenReturn(List.of(Data.createDegree().get(), Data.createDegree2().get()));

        List<Degree> degrees=degreeService.findAll();

        assertEquals(2, degrees.size());
        assertEquals("Doctorate", degrees.get(0).getName());
    }

    @Test
    void testFindById(){
        when(degreeRepository.findById(anyLong())).thenReturn(Data.createDegree());

        Degree degree=degreeService.findById(1L);

        assertEquals(1L, degree.getId());
        assertEquals("Doctorate", degree.getName());

    }

    @Test
    void testCreate(){
        Degree degree= Data.createDegree().get();

        degreeService.create(degree);

        verify(degreeRepository).save(degreeArgumentCaptor.capture());
        assertEquals(1L, degreeArgumentCaptor.getValue().getId());
        assertEquals("Doctorate", degreeArgumentCaptor.getValue().getName());
    }
}