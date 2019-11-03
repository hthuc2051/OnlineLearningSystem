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

/**
 *
 * @author HP
 */
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
        String name;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select id,name from tblCourses Where active = 1";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    dto = new CourseDto(id, name);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    

    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Update tblCourses Set active = 0 where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
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
                String sql = "Insert into tblCourses(name, active) values(?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setBoolean(4, true);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
