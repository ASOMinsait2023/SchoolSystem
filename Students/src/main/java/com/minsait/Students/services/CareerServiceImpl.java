package com.minsait.Students.services;

import com.minsait.Students.models.entities.Career;
import com.minsait.Students.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CareerServiceImpl implements ICareerService{

    @Autowired
    private CareerRepository careerRepository;
    @Transactional
    @Override
    public void save(Career career) {
        careerRepository.save(career);
    }

    @Override
    public List<Career> getAll() {
        return careerRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Career getById(Long id) {
        return careerRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public Career getByName(String name) {
        return careerRepository.findByName(name).orElseThrow();
    }
}
