package com.minsait.Classroom.repositories;

import com.minsait.Classroom.models.entities.Classroom;
import com.minsait.Classroom.models.entities.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {

    @Query("SELECT studentId FROM StudentClassroom r WHERE r.classroom = ?1")
    List<Long> getAllStudentsByClassroom(Classroom classroom);

}
