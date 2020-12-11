package com.example.case4.service.diaryStudent;

import com.example.case4.model.DiaryStudent;
import com.example.case4.repo.DiaryStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DiaryStudentService implements IDiaryStudentService {
    @Autowired
    private DiaryStudentRepository diaryRepository;
    @Override
    public Iterable<DiaryStudent> findAll() {
        return diaryRepository.findAll();
    }

    @Override
    public Optional<DiaryStudent> findById(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public DiaryStudent save(DiaryStudent diary) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
