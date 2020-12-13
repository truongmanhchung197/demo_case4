package com.example.case4.service.diaryStudent;

import com.example.case4.model.DiaryStudent;
import com.example.case4.repo.DiaryStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
        return diaryRepository.save(diary);
    }

    @Override
    public void remove(Long id) {
        diaryRepository.deleteById(id);
    }

    @Override
    public Iterable<DiaryStudent> getAllByStudentId(Long id) {
        return diaryRepository.getAllByStudent_Id(id);
    }
}
