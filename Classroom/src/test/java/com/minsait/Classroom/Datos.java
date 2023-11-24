package com.minsait.Classroom;

import com.minsait.Classroom.models.entities.Classroom;
import com.minsait.Classroom.models.entities.StudentClassroom;

public class Datos {

    public static Classroom getClassroom(){
        return new Classroom();
    }

    public static StudentClassroom getStudentClassroom(){
        StudentClassroom student = new StudentClassroom(1L, 1L, getClassroom());
        return student;
    }

}
