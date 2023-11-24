package com.minsait.Classroom.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.Classroom.Datos;
import com.minsait.Classroom.models.entities.Classroom;
import com.minsait.Classroom.services.IClassroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassroomController.class)
class ClassroomControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private IClassroomService classroomService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateClassroom() throws Exception {
        //given
        Classroom classroom = Datos.getClassroom();
        //when - then
        mvc.perform(post("/api/v1/classrooms/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(classroom)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Classroom created"));
    }

    @Test
    void testAddStudentToClassroom() throws Exception {
        //given
        Classroom classroom = Datos.getClassroom();
        //when - then
        mvc.perform(put("/api/v1/classrooms/add-student?classroomId=1&studentId=1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("Student with id: 1 added successfully"));
    }

    @Test
    void testGetAllStudentsByClassroom() throws Exception {
        //given
        Long classroomId = 1L;
        when(classroomService.findById(classroomId)).thenReturn(Datos.getClassroom());
        when(classroomService.getStudentsIds(Datos.getClassroom())).thenReturn(new HashMap<>());
        //when - then
        mvc.perform(get("/api/v1/classrooms/get-students/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Students information"));
    }

    @Test
    void testGetAllClassrooms() throws Exception {
        mvc.perform(get("/api/v1/classrooms"))
                .andExpect(status().isOk());
    }
}