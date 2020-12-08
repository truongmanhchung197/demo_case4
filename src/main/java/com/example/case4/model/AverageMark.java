package com.example.case4.model;



import javax.persistence.*;

@Entity
@Table
public class AverageMark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Classroom classroom;
    @ManyToOne
    private Module module;
    private Float avgMark;

    public AverageMark() {
    }

    public AverageMark(Long id, Classroom classroom, Module module, Float avgMark) {
        this.id = id;
        this.classroom = classroom;
        this.module = module;
        this.avgMark = avgMark;
    }

    public Long getIdMark() {
        return id;
    }

    public void setIdMark(Long id) {
        this.id = id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(Float avgMark) {
        this.avgMark = avgMark;
    }
}
