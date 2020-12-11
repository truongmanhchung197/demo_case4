package com.example.case4.service.markstudent;

import com.example.case4.model.Mark;
import com.example.case4.repo.MarkStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MarkStudentService implements IMarkStudentService {

    @Autowired
    private MarkStudentRepository markStudentRepository;

    @Override
    public Iterable<Mark> findAll() {
        return markStudentRepository.findAll();
    }

    @Override
    public Optional<Mark> findById(Long id) {
        return markStudentRepository.findById(id);
    }

    @Override
    public Mark save(Mark mark) {
        return markStudentRepository.save(mark);
    }

    @Override
    public void remove(Long id) {
        markStudentRepository.deleteById(id);
    }
}
