package com.minsait.Subject.services;

import com.minsait.Subject.models.entities.Subject;
import com.minsait.Subject.respositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService{
    @Autowired
    private SubjectRepository subjectRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow();
    }
    @Override
    @Transactional
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }
    @Override
    public void update(Subject subject) {
        subjectRepository.save(subject);
    }
    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}
