package com.minsait.Teacher.services;


import com.minsait.Teacher.models.entities.Degree;
import com.minsait.Teacher.repositories.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DegreeServiceImpl implements IDegreeService{
    @Autowired
    private DegreeRepository degreeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Degree> findAll() {
        return degreeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Degree findById(Long id) {
        return degreeRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void create(Degree degree) {
        degreeRepository.save(degree);
    }
}
