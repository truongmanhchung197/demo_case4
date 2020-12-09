package com.example.case4.service.classroom;

import com.example.case4.model.Classroom;
import com.example.case4.repo.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClassService implements IClassService {
    @Autowired
    private ClassRepository classRepository;
    @Override
    public Iterable<Classroom> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classroom> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Classroom save(Classroom classroom) {
        return classRepository.save(classroom);
    }

    @Override
    public void remove(Long id) {
        classRepository.deleteById(id);
    }
}
