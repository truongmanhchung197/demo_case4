package com.example.case4.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private boolean status;
    private String tel;

    @ManyToOne
    private Class className;

    @OneToMany(mappedBy = "diaries")
    private List<Diary> diaryList;

    public Student() {
    }

    public Student(Long id, String name, Integer age, String address, boolean status, String tel, Class className, List<Diary> diaryList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.status = status;
        this.tel = tel;
        this.className = className;
        this.diaryList = diaryList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public List<Diary> getDiaryList() {
        return diaryList;
    }

    public void setDiaryList(List<Diary> diaryList) {
        this.diaryList = diaryList;
    }
}
