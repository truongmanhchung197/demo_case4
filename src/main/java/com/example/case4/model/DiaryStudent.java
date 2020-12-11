package com.example.case4.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class DiaryStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1000)
    private String content;
    private LocalDate date;
    @ManyToOne
    private Student student;


    public DiaryStudent() {
    }

    public DiaryStudent(Long id, String content, LocalDate date, Student student) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return this.date.getDayOfMonth()+"/"+this.date.getMonthValue()+"/"+this.date.getYear();
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
