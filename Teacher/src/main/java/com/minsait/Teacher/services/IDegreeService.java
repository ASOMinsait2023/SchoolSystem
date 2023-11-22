package com.minsait.Teacher.services;

import com.minsait.Teacher.models.entities.Degree;

import java.util.List;

public interface IDegreeService {
    List<Degree> findAll();
    Degree findById(Long id);
    void create(Degree degree);
}
