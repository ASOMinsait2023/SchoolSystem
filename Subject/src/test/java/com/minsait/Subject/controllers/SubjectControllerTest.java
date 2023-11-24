package com.minsait.Subject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.Subject.models.entities.Subject;
import com.minsait.Subject.services.ISubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubjectController.class)
class SubjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ISubjectService subjectService;
    ObjectMapper mapper;
    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void testGetAllSubjects() throws Exception {

        when(subjectService.findAll()).thenReturn(List.of(Datos.crearSubject().get(), Datos.crearSubject().get()));

        mockMvc.perform(get("/api/v1/subjects").contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body").exists(),
                        jsonPath("$.body[*].id").isNotEmpty(),
                        jsonPath("$.body[*].name").isNotEmpty()
                );
    }

    @Test
    void testGetSubjectById() throws Exception {
        when(subjectService.findById(1L)).thenReturn(Datos.crearSubject().get());

        mockMvc.perform(get("/api/v1/subjects/1").contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body").exists(),
                        jsonPath("$.body.id").isNotEmpty(),
                        jsonPath("$.body.name").isNotEmpty()
                );
    }

    @Test
    void testGetSubjectByIdNotFound() throws Exception {
        when(subjectService.findById(1L)).thenThrow(NoSuchElementException.class);

        mockMvc.perform(get("/api/v1/subjects/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSaveSubject() throws Exception {
        Subject subject = Datos.crearSubject2();

       doNothing().when(subjectService).save(any(Subject.class));

        mockMvc.perform(post("/api/v1/subjects/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(subject)))
                .andExpect(status().isCreated());
    }

    @Test
    void testSaveSubjectBadRequest() throws Exception {
        Subject subject = Datos.crearSubject2();

        doThrow(HttpMessageNotReadableException.class).when(subjectService).save(any(Subject.class));

        mockMvc.perform(post("/api/v1/subjects/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(subject)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateSubject() throws Exception {
        Subject subject = Datos.crearSubject2();

        when(subjectService.findById(1L)).thenReturn(Datos.crearSubject().get());
        doNothing().when(subjectService).update(any(Subject.class));

        mockMvc.perform(put("/api/v1/subjects/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(subject)))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteSubject() throws Exception {
        when(subjectService.findById(1L)).thenReturn(Datos.crearSubject().get());
        doNothing().when(subjectService).delete(any(Long.class));

        mockMvc.perform(delete("/api/v1/subjects/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testAllPretty() throws Exception {
        when(subjectService.findAll()).thenReturn(List.of(Datos.crearSubject().get(), Datos.crearSubject().get()));

        mockMvc.perform(get("/api/v1/subjects/all_pretty").contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.body").exists(),
                        jsonPath("$.body[0].id").isNotEmpty(),
                        jsonPath("$.body[0].name").isNotEmpty()
                );
    }
}