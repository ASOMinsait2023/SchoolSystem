package com.minsait.Teacher.controllers;

import com.minsait.Teacher.models.entities.Degree;
import com.minsait.Teacher.models.entities.Teacher;

import java.util.Optional;

public class Data {

    public static Optional<Teacher> createTeacher1(){
        return Optional.of(new Teacher(1L, "Mario", "Alexis", 23L, "mario@gmail.com",
                createDegree().get()));
    }

    public static Optional<Teacher> createTeacher2(){
        return Optional.of(new Teacher(2L, "Juan", "Perez", 23L, "juan@gmail.com",
                createDegree2().get()));
    }

    public static Optional<Degree> createDegree(){
        return Optional.of(new Degree(1L, "Doctorate", "Mathematics"));
    }

    public static Optional<Degree> createDegree2(){
        return Optional.of(new Degree(2L, "Master", "History"));
    }
}
