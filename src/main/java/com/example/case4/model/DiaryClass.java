package com.example.case4.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class DiaryClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String content;
    private LocalDate date;
    @ManyToOne
    private Classroom classroom;

    public DiaryClass() {
    }

    public DiaryClass(Long id, String content, LocalDate date, Classroom classroom) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.classroom = classroom;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
