package com.minsait.Subject.services;

import com.minsait.Subject.models.entities.SubjectType;
import com.minsait.Subject.respositories.SubjectTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SubjectTypeImpl implements SubjectTypeService{
    @Autowired
    private SubjectTypeRepository subjectTypeRepository;
    @Override
    @Transactional(readOnly = true)
    public List<SubjectType> findAll() {
        return subjectTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectType findById(Long id) {
        return subjectTypeRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void save(SubjectType subjectType) {
        subjectTypeRepository.save(subjectType);
    }
}
