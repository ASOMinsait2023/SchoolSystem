package com.minsait.Students;

import com.minsait.Students.models.entities.Career;
import com.minsait.Students.models.entities.Student;

import java.util.List;

public class Datos {

    public static Student getStudent(){
        return new Student(1L, "Rodrigo", "Garcia", 21, "roy@email.com", getCareer(), 0);
    }

    public static Career getCareer(){
        return new Career(1L, "Programacion", 345);
    }

    public static Career getCareer2(){
        return new Career(2L, "Contaduria", 1000);
    }

    public static List<Student> getStudentList(){
        return List.of(getStudent());
    }

}
