package com.example.case4.service.diary;

import com.example.case4.model.DiaryStudent;
import com.example.case4.repo.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DiaryService implements IDiaryService{
    @Autowired
    private DiaryRepository diaryRepository;
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
