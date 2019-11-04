/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.dtos.CourseDto;
import com.dtos.LessonDto;
import com.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LessonDao implements Serializable {

    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public ArrayList<LessonDto> getLessonByCourseId(String courseId) throws ClassNotFoundException, SQLException {
        ArrayList<LessonDto> result = null;
        LessonDto dto = null;
        int id;
        String name, description, video_link;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select l.id,l.name,l.description,l.video_link from tblLesson l join tblCourses_Lesson c on l.id = c.lesson_id Where l.active = 1 and c.course_id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, courseId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    description = rs.getString("description");
                    video_link = rs.getString("video_link");
                    dto = new LessonDto(id, name, description, video_link);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }

}
