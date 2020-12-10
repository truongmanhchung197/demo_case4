package com.example.case4.service.coach;

import com.example.case4.model.Classroom;
import com.example.case4.model.Coach;
import com.example.case4.repo.ClassRepository;
import com.example.case4.repo.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoachService implements ICoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private ClassRepository classRepository;

    @Override
    public Iterable<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public Optional<Coach> findById(Long id) {
        return coachRepository.findById(id);
    }

    @Override
    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public void remove(Long id) {
        coachRepository.deleteById(id);
    }

    @Override
    public Iterable<Classroom> showListClass(Long idCoach) {
        return classRepository.findAllByCoach_Id(idCoach);
    }
}
