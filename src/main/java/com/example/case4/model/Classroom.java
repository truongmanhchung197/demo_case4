package com.example.case4.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    private Coach coach;

    @OneToMany
    private List<Diary> diaryList;

    public Classroom(String name, Coach coach, List<Diary> diaryList) {
        this.name = name;
        this.coach = coach;
        this.diaryList = diaryList;
    }

    public Classroom(Long id, String name, Coach coach, List<Diary> diaryList) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.diaryList = diaryList;
    }

    public Classroom() {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}