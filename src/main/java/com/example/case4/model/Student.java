package com.example.case4.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table
public class Student {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private boolean status;
    private String gender;
    private String tel;
    private String image;

    @Transient
    private MultipartFile avatar;

    @ManyToOne
    private Classroom classroom;

    public Student() {
    }

    public Student(Long id, String name, Integer age, String address, boolean status, String gender, String tel, String image, MultipartFile avatar, Classroom classroom) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.status = status;
        this.gender = gender;
        this.tel = tel;
        this.image = image;
        this.avatar = avatar;
        this.classroom = classroom;
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

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
