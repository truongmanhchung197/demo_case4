package com.example.case4.repo;

import com.example.case4.model.DiaryClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryClassRepository extends JpaRepository<DiaryClass,Long> {
    Iterable<DiaryClass> getAllByClassroom_Id(Long id);
}
