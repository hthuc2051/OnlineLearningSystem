/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author HP
 */
public class CourseDto implements Serializable {

    private int id;
    private String name;
    private List<LessonDto> lessons;
    private boolean active;

    public CourseDto(String name) {
        this.name = name;
    }

    public CourseDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseDto(int id, String name, List<LessonDto> lessons) {
        this.id = id;
        this.name = name;
        this.lessons = lessons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
