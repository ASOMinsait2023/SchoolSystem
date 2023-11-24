package com.minsait.Classroom.repositories;

import com.minsait.Classroom.Datos;
import com.minsait.Classroom.models.entities.Classroom;
import com.minsait.Classroom.models.entities.StudentClassroom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentClassroomRepositoryTest {

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Test
    void testFindStudentsByClassroom(){
        //given
        Classroom classroom = Datos.getClassroom();
        classroom.setId(1L);
        //when
        List<Long> students = studentClassroomRepository.getAllStudentsByClassroom(classroom);
        //then
        assertEquals(1, students.size());

    }

}