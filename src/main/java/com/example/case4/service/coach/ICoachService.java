package com.example.case4.service.coach;

import com.example.case4.model.Classroom;
import com.example.case4.model.Coach;
import com.example.case4.service.IService;

import java.util.List;

public interface ICoachService extends IService<Coach> {
    Iterable<Classroom> showListClass(Long id);
}
