package com.minsait.Students.services;

import com.minsait.Students.models.entities.Career;

import java.util.List;

public interface ICareerService {

    void save(Career career);
    List<Career> getAll();
    Career getById(Long id);
    Career getByName(String name);
}
