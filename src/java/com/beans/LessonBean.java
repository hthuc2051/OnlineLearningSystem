/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dtos.LessonDto;
import com.models.CourseDao;
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

    public ArrayList<LessonDto> getAllLessons() throws ClassNotFoundException, SQLException {
        LessonDao dao = new LessonDao();
        ArrayList<LessonDto> listLesson = dao.getAllLessons();
        return listLesson;
    }

    public ArrayList<LessonDto> loadLessonByCourseId() throws ClassNotFoundException, SQLException {
        LessonDao dao = new LessonDao();
        ArrayList<LessonDto> listLesson = dao.getLessonByCourseId(id);
        return listLesson;
    }

    public boolean insertLesson(int courseId) throws ClassNotFoundException, SQLException {
        LessonDao dao = new LessonDao();
        LessonDto dto = new LessonDto(id, name, description, videoLink);
        boolean check = dao.insert(dto);
        if (check) {
            int insertedID = dao.getInsertedId();
            check = dao.insertCourseLesson(courseId, insertedID);
        }
        return check;
    }

    public int getCourseIdByLessonId() throws ClassNotFoundException, SQLException {
        CourseDao dao = new CourseDao();
        int courseId = dao.getCourseByLessonId(id);
        return courseId;
    }

    public boolean uploadLesson(int newCourseId, int oldCourseId) throws ClassNotFoundException, SQLException {
        LessonDao dao = new LessonDao();
        LessonDto dto = new LessonDto(id, name, description, videoLink);
        boolean check = dao.update(dto);
        if (check) {
            check = dao.updateCourseLesson(newCourseId, oldCourseId, id);
        }
        return check;
    }
    
     public boolean deleteLesson() throws ClassNotFoundException, SQLException {
        LessonDao dao = new LessonDao();
        boolean check = dao.delete(id);
        return check;
    }
}
