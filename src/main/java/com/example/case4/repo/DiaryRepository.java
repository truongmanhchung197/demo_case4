package com.example.case4.repo;

import com.example.case4.model.DiaryStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<DiaryStudent, Long> {
}
