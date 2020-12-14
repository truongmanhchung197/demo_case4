package com.example.case4.repo;

import com.example.case4.model.Classroom;
import com.example.case4.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Long> {
}
