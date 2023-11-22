package com.minsait.Students.services;

import com.minsait.Students.models.entities.Career;
import com.minsait.Students.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CareerServiceImpl implements ICareerService{

    @Autowired
    private CareerRepository careerRepository;
    @Transactional
    @Override
    public void save(Career career) {
        careerRepository.save(career);
    }

    @Transactional(readOnly = true)
    @Override
    public Career findById(Long id) {
        return careerRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public Career findByName(String name) {
        return careerRepository.findByName(name).orElseThrow();
    }
}
