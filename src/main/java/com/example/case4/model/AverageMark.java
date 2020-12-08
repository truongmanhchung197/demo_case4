package com.example.case4.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avgmarks")
public class AverageMark {
    @Id
    @ManyToOne
    private Class classname;
    @Id
    @ManyToOne
    private Module module;
    private Float avgMark;

    public AverageMark() {
    }

    public AverageMark(Class classname, Module module, Float avgMark) {
        this.classname = classname;
        this.module = module;
        this.avgMark = avgMark;
    }

    public Class getClassname() {
        return classname;
    }

    public void setClassname(Class classname) {
        this.classname = classname;
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
