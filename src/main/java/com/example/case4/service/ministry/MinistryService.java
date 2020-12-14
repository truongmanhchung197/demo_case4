package com.example.case4.service.ministry;

import com.example.case4.model.Classroom;
import com.example.case4.model.Ministry;
import com.example.case4.repo.ClassRepository;
import com.example.case4.repo.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MinistryService implements IMinistryService {

    @Autowired
    private MinistryRepository ministryRepository;
    @Autowired
    private ClassRepository classRepository;

    @Override
    public Iterable<Ministry> findAll() {
        return ministryRepository.findAll();
    }

    @Override
    public Optional<Ministry> findById(Long id) {
        return ministryRepository.findById(id);
    }

    @Override
    public Ministry save(Ministry ministry) {
        return ministryRepository.save(ministry);
    }

    @Override
    public void remove(Long id) {
        ministryRepository.deleteById(id);
    }

}
