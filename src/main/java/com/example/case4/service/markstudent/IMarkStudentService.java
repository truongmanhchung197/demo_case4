package com.example.case4.service.markstudent;

import com.example.case4.model.Mark;
import com.example.case4.service.IService;

public interface IMarkStudentService extends IService<Mark> {
    Iterable<Mark> showListMark(Long id);
    Mark getByStudentIdAndModuleId(Long idStudent,Long idModule);
}
