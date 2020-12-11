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

    public Classroom(String name, Coach coach) {
        this.name = name;
        this.coach = coach;
    }
    public Classroom(Long id, String name, Coach coach) {
        this.id = id;
        this.name = name;
        this.coach = coach;
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}