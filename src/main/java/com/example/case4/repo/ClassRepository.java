package com.example.case4.repo;

import com.example.case4.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ClassRepository extends JpaRepository<Classroom,Long> {
    Iterable<Classroom> findAllByCoach_Id(Long id);
}
