package com.minsait.Students.services;

import com.minsait.Students.models.entities.Career;

public interface ICareerService {

    void save(Career career);
    Career findById(Long id);
    Career findByName(String name);
}
