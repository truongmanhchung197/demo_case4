package com.example.case4.repo;

import com.example.case4.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkStudentRepository extends JpaRepository<Mark, Long> {
}
