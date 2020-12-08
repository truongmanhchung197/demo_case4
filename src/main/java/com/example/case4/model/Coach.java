package com.example.case4.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coaches")
public class Coach {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    private Integer age;
    private String gender;
    private String tel;
    private String address;

    public Coach() {
    }

    public Coach(Long id, String name, Integer age, String gender, String tel, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
