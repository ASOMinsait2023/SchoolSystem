package com.minsait.Students.services;

import com.minsait.Students.Datos;
import com.minsait.Students.models.entities.Career;
import com.minsait.Students.repositories.CareerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CareerServiceImplTest {

    @Mock
    private CareerRepository careerRepository;
    @InjectMocks
    private CareerServiceImpl careerService;

    @Test
    void testGetAll(){
        //given
        when(careerRepository.findAll()).thenReturn(List.of(Datos.getCareer()));
        //when
        List<Career> careers = careerService.getAll();
        assertEquals(1, careers.size());
    }

    @Test
    void testGetById(){
        //given
        when(careerRepository.findById(1L)).thenReturn(Optional.of(Datos.getCareer()));
        //when
        Career career = careerService.getById(1L);
        assertEquals("Programacion", career.getName());
    }

    @Test
    void testGetByName(){
        //given
        when(careerRepository.findByName("Programacion")).thenReturn(Optional.of(Datos.getCareer()));
        //when
        Career career = careerService.getByName("Programacion");
        assertEquals("Programacion", career.getName());
    }

    @Test
    void testSave(){
        //given
        Career career = Datos.getCareer();
        when(careerRepository.save(career)).thenReturn(career);
        //when
        careerService.save(career);
        //then
        verify(careerRepository).save(career);
    }

}