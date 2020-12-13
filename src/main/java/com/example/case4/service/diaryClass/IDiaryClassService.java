package com.example.case4.service.diaryClass;

import com.example.case4.model.DiaryClass;
import com.example.case4.service.IService;

public interface IDiaryClassService extends IService<DiaryClass> {
    Iterable<DiaryClass> getAllByClassId(Long id);
}
