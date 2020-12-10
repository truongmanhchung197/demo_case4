package com.example.case4.service.student;

import com.example.case4.model.Student;
import com.example.case4.service.IService;

public interface IStudentService extends IService<Student> {
    Iterable<Student> getListClass(Long id);
}
