/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dtos.CourseDto;
import com.dtos.LessonDto;
import com.models.CourseDao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CourseBean implements Serializable{
    private int id;
    private String name;
    private List<LessonDto> lessons;

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
    
    public ArrayList<CourseDto> loadAllCourse() throws ClassNotFoundException, SQLException{
        ArrayList<CourseDto> listCourse;
        CourseDao dao = new CourseDao();
           listCourse = (ArrayList<CourseDto>) dao.getAllCourses();
        return listCourse;
    }
}
