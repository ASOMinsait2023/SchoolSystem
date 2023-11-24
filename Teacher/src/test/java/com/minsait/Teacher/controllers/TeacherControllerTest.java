package com.minsait.Teacher.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.Teacher.models.entities.Teacher;
import com.minsait.Teacher.services.ITeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ITeacherService service;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper=new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception{
        when(service.findAll()).thenReturn(List.of(Data.createTeacher1().get(), Data.createTeacher2().get()));
        mvc.perform(get("/api/v1/teacher/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.body.[0].firstName").value("Mario"))
                .andExpect(jsonPath("$.body.[1].firstName").value("Juan"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception{
        when(service.findById(anyLong())).thenReturn(Data.createTeacher1().get());
        mvc.perform(get("/api/v1/teacher/list/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.body.firstName").value("Mario"))
                .andExpect(jsonPath("$.body.lastName").value("Alexis"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testSave() throws Exception{
        Teacher teacher= Data.createTeacher1().get();
        mvc.perform(post("/api/v1/teacher/create").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(teacher)))
                .andExpect(jsonPath("$.body.id").value(1L))
                .andExpect(jsonPath("$.body.firstName").value("Mario"))
                .andExpect(jsonPath("$.body.lastName").value("Alexis"))
                .andExpect(jsonPath("$.body.degree.name").value("Doctorate"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdate() throws Exception{
        Teacher teacher= Data.createTeacher1().get();
        when(service.update(any(), anyLong())).thenReturn(teacher);
        mvc.perform(put("/api/v1/teacher/update/1").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(teacher)))
                .andExpect(jsonPath("$.body.id").value(1L))
                .andExpect(jsonPath("$.body.firstName").value("Mario"))
                .andExpect(jsonPath("$.body.lastName").value("Alexis"))
                .andExpect(jsonPath("$.body.degree.name").value("Doctorate"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteById() throws Exception{
        mvc.perform(delete("/api/v1/teacher/delete/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testCountBySpeciality() throws Exception{
        when(service.countBySpeciality(anyString())).thenReturn(2L);
        mvc.perform(get("/api/v1/teacher/countBySpeciality/Mathematics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("The number of teachers in the Mathematics speciality is: 2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdException() throws Exception{
        when(service.findById(anyLong())).thenThrow(NoSuchElementException.class);
        mvc.perform(get("/api/v1/teacher/list/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}