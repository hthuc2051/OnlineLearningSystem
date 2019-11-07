/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.dtos.CourseDto;
import com.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements Serializable {

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

    public List<CourseDto> getAllCourses() throws ClassNotFoundException, SQLException {
        List<CourseDto> result = null;
        CourseDto dto = null;
        int id;
        String name, description;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select id,name,description from tblCourses Where active = 1";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    description = rs.getString("description");
                    dto = new CourseDto(id, name, description);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public CourseDto findCourseById(int id) throws ClassNotFoundException, SQLException {
        CourseDto dto = null;
        String name, description;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Select id, name,description from tblCourses Where id=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    name = rs.getString("name");
                    description = rs.getString("description");
                    dto = new CourseDto(id, name, description);
                }
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public List<CourseDto> getUserCourse(int userId) throws ClassNotFoundException, SQLException {
        List<CourseDto> result = null;
        CourseDto dto = null;
        int id;
        String name, description;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select c.id,c.name,c.description from tblUsers_Courses u join tblCourses c on c.id = u.course_id Where c.active = 1 and u.user_id = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    description = rs.getString("description");
                    dto = new CourseDto(id, name, description);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean delete(int id) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Update tblCourses Set active = 0 where id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteCourseContainLesson(int id) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insert(CourseDto dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Insert into tblCourses(name, active,description) values(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setBoolean(2, true);
                stm.setString(3, dto.getDescription());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(CourseDto dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Update tblCourses set name = ?, description =? where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getDescription());
                stm.setInt(3, dto.getId());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getCourseByLessonId(int lessonId) throws ClassNotFoundException, SQLException {
        int id = 0;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Select l.id from tblCourses l join tblCourses_Lessons c on l.id = c.course_id Where l.active = 1 and c.lesson_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, lessonId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } finally {
            closeConnection();
        }
        return id;
    }
}
