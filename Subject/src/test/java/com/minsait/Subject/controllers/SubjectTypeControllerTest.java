package com.minsait.Subject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.Subject.models.entities.SubjectType;
import com.minsait.Subject.services.SubjectTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubjectTypeController.class)
class SubjectTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SubjectTypeService subjectTypeService;
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllSubjectTypes() throws Exception {
        when(subjectTypeService.findAll()).thenReturn(List.of(Datos.crearSubjectType().get(), Datos.crearSubjectType().get()));

        mockMvc.perform(get("/api/v1/subject_types").contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body").exists(),
                        jsonPath("$.body[*].id").isNotEmpty(),
                        jsonPath("$.body[*].subjectType").isNotEmpty()
                );
    }

    @Test
    void testAllPretty() throws Exception {
        when(subjectTypeService.findAll()).thenReturn(List.of(Datos.crearSubjectType().get(), Datos.crearSubjectType().get()));

        mockMvc.perform(get("/api/v1/subject_types/all_pretty").contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body").exists(),
                        jsonPath("$.body[*].id").isNotEmpty(),
                        jsonPath("$.body[*].subjectType").isNotEmpty()
                );
    }

    @Test
    void testGetSubjectTypeById() throws Exception {
        when(subjectTypeService.findById(1L)).thenReturn(Datos.crearSubjectType().get());

        mockMvc.perform(get("/api/v1/subject_types/1").contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body").exists(),
                        jsonPath("$.body.id").isNotEmpty(),
                        jsonPath("$.body.subjectType").isNotEmpty()
                );
    }

    @Test
    void testGetSubjectTypeByIdNotFound() throws Exception {
        when(subjectTypeService.findById(1L)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/api/v1/subject_types/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateSubjectType() throws Exception{
        SubjectType subjectType = Datos.crearSubjectType().get();

        mockMvc.perform(post("/api/v1/subject_types/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(subjectType)))
                .andExpect(status().isCreated());
    }
}