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
        String name, description, price;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select id,name,description,price from tblCourses Where active = 1";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    description = rs.getString("description");
                    price = rs.getString("price");
                    dto = new CourseDto(id, name, description, price);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public List<CourseDto> loadUserCourses(String username) throws ClassNotFoundException, SQLException {
        List<CourseDto> result = null;
        CourseDto dto = null;
        int id;
        String name, description;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select C.id, C.name, C.description "
                        + "From tblUsers U, tblUsers_Courses UC , tblCourses C "
                        + "Where U.id = UC.user_id AND UC.course_id = C.id "
                        + "AND C.active = 1 "
                        + "AND U.username =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
                String sql = "Insert into tblCourses(name, active, description, price) values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setBoolean(2, true);
                stm.setString(3, dto.getDescription());
                stm.setString(4, dto.getPrice());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean enroll(Integer courseId, Integer userId) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Insert into tblUsers_Courses(user_id,course_id) values(?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                stm.setInt(2, courseId);
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
                String sql = "Update tblCourses set name = ?, description = ?, price = ? where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getDescription());
                stm.setString(3, dto.getPrice());
                stm.setInt(4, dto.getId());
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

    public List<CourseDto> getUnEnrollCoursesByUsername(String username) throws ClassNotFoundException, SQLException {
        List<CourseDto> result = null;
        CourseDto dto = null;
        int id;
        String name, description;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "SELECT C.id, C.name, C.description FROM tblCourses C "
                        + "EXCEPT "
                        + "SELECT C.id, C.name, C.description "
                        + "FROM tblUsers_Courses UC INNER JOIN tblCourses C ON UC.course_id=C.id "
                        + "WHERE UC.user_id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
}
