package com.example.case4.repo;

import com.example.case4.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Iterable<Student> getAllByStatusIsTrueAndClassroom_Id(Long idClass);
}
