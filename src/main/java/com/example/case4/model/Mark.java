package com.example.case4.model;


import javax.persistence.*;

@Entity
@Table
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Module module;
    private Float theory_point;
    private Float practice_point;

    public Mark() {
    }

    public Mark(Student student, Module module, Float theory_point, Float practice_point) {
        this.student = student;
        this.module = module;
        this.theory_point = theory_point;
        this.practice_point = practice_point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Float getTheory_point() {
        return theory_point;
    }

    public void setTheory_point(Float theory_point) {
        this.theory_point = theory_point;
    }

    public Float getPractice_point() {
        return practice_point;
    }

    public void setPractice_point(Float practice_point) {
        this.practice_point = practice_point;
    }
}
