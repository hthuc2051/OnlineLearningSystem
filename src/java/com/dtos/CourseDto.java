
package com.dtos;

import java.io.Serializable;
import java.util.List;

public class CourseDto implements Serializable {

    private int id;
    private String name, description;
    private List<LessonDto> lessons;
    private boolean active;

    public CourseDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CourseDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
