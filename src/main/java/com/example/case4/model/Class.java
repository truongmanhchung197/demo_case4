package com.example.case4.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classes")
public class Class {
    @Id
    private String name;

    @ManyToOne
    private Coach coach;

    @OneToMany(mappedBy = "Diary")
    private List<Diary> diaryList;

    public Class() {
    }

    public Class(String name, Coach coach, List<Diary> diaryList) {
        this.name = name;
        this.coach = coach;
        this.diaryList = diaryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public List<Diary> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<Diary> diaryList) {
        this.diaryList = diaryList;
    }
}
