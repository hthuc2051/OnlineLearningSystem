/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dtos.LessonDto;
import com.models.LessonDao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class LessonBean implements Serializable {

    private int id;
    private String name, description, videoLink;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public ArrayList<LessonDto> loadLessonByCourseId(String courseId) throws ClassNotFoundException, SQLException {
        LessonDao dao = new LessonDao();
        ArrayList<LessonDto> listLesson = dao.getLessonByCourseId(Integer.parseInt(courseId));
        return listLesson;
    }

    public LessonDto getLessonDetails(int lessonId) throws ClassNotFoundException, SQLException {
        LessonDao dao = new LessonDao();
        LessonDto dto = dao.findLessonById(lessonId);
        return dto;
    }
}
