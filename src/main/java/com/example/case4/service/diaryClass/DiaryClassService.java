package com.example.case4.service.diaryClass;

import com.example.case4.model.DiaryClass;
import com.example.case4.repo.DiaryClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryClassService implements IDiaryClassService {
    @Autowired
    private DiaryClassRepository diaryClassRepository;

    @Override
    public Iterable<DiaryClass> findAll() {
        return diaryClassRepository.findAll();
    }

    @Override
    public Optional<DiaryClass> findById(Long id) {
        return diaryClassRepository.findById(id);
    }

    @Override
    public DiaryClass save(DiaryClass diaryClass) {
        return diaryClassRepository.save(diaryClass);
    }

    @Override
    public void remove(Long id) {
        diaryClassRepository.deleteById(id);
    }

    @Override
    public Iterable<DiaryClass> getAllByClassId(Long id) {
        return diaryClassRepository.getAllByClassroom_Id(id);
    }
}
