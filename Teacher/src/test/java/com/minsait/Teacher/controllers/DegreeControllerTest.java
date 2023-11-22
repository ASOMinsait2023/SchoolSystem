package com.minsait.Teacher.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.Teacher.models.entities.Degree;
import com.minsait.Teacher.services.IDegreeService;
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

@WebMvcTest(DegreeController.class)
class DegreeControllerTest{

    @Autowired
    MockMvc mvc;

    @MockBean
    private IDegreeService service;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper=new ObjectMapper();
    }

    @Test
    void testFindAll() throws Exception{
        when(service.findAll()).thenReturn(List.of(Data.createDegree().get(), Data.createDegree2().get()));
        mvc.perform(get("/api/v1/degree/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Doctorate"))
                .andExpect(jsonPath("$[1].name").value("Master"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception{
        when(service.findById(anyLong())).thenReturn(Data.createDegree().get());
        mvc.perform(get("/api/v1/degree/list/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Doctorate"))
                .andExpect(jsonPath("$.speciality").value("Mathematics"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testSave() throws Exception{
        Degree degree= Data.createDegree().get();
        mvc.perform(post("/api/v1/degree/create").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(degree)))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Doctorate"))
                .andExpect(jsonPath("$.speciality").value("Mathematics"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testFindByIdException() throws Exception{
        when(service.findById(anyLong())).thenThrow(NoSuchElementException.class);
        mvc.perform(get("/api/v1/degree/list/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}