package com.minsait.Students.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.Students.models.entities.Student;
import com.minsait.Students.services.ICareerService;
import com.minsait.Students.services.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.minsait.Students.Datos;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IStudentService studentService;
    @MockBean
    private ICareerService careerService;
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveStudent() throws Exception {
        String studentMapper = objectMapper.writeValueAsString(Datos.getStudent());
        mvc.perform(post("/api/v1/students/save")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(studentMapper))
              .andExpect(jsonPath("$.Status").value("Created"))
              .andExpect(jsonPath("$.Message").value("Student created"))
              .andExpect(status().isCreated());
    }

    @Test
    void testSaveFailsWithIncorrectEmail() throws Exception {
        Student student = Datos.getStudent();
        student.setEmail("incorrect");
        String studentMapper = objectMapper.writeValueAsString(student);
        mvc.perform(post("/api/v1/students/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentMapper))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFindById() throws Exception {
        when(studentService.getById(1L)).thenReturn(Datos.getStudent());
        mvc.perform(get("/api/v1/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.id").value(1))
             .andExpect(jsonPath("$.firstName").value(Datos.getStudent().getFirstName()))
             .andExpect(jsonPath("$.email").value(Datos.getStudent().getEmail()))
             .andExpect(status().isOk());
    }

    @Test
    void testStudentNotFound() throws Exception {
        when(studentService.getById(anyLong())).thenThrow(NoSuchElementException.class);
        mvc.perform(get("/api/v1/students/1")
                       .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(studentService.getAll()).thenReturn(Datos.getStudentList());
        mvc.perform(get("/api/v1/students")
                      .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].firstName").value("Rodrigo"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteStudent() throws Exception {
        when(studentService.delete(1L)).thenReturn(true);
        mvc.perform(delete("/api/v1/students/1")
                       .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.Status").value("OK"))
            .andExpect(jsonPath("$.Message").value("Student with id: 1 deleted"))
            .andExpect(status().isOk());
    }

    @Test
    void testFailWhenDeleteNonExistStudent() throws Exception {
        when(studentService.delete(1L)).thenReturn(false);
        mvc.perform(delete("/api/v1/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddStudentToCareer() throws Exception {
        doNothing().when(studentService).addStudentToCareer(1L, "Programacion");
        mvc.perform(put("/api/v1/students/add-to-career?studentId=1&careerName=Programacion")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFailWhenAddStudentToCareer() throws Exception {
        doThrow(NoSuchElementException.class).when(studentService).addStudentToCareer(anyLong(), anyString());
        mvc.perform(put("/api/v1/students/add-to-career?studentId=1&careerName=Programacion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCheckStudentToCareer() throws Exception {
        when(studentService.checkStudentProgress(1L, "Programacion")).thenReturn("progress");
        mvc.perform(put("/api/v1/students/check-progress?studentId=1&careerName=Programacion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Status").value("OK"))
                .andExpect(jsonPath("$.Message").value("progress"));
    }
}