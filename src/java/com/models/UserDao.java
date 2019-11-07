/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models;

import com.dtos.UserDto;
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
public class UserDao implements Serializable {

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

    public List<UserDto> getAllUsers() throws ClassNotFoundException, SQLException {
        List<UserDto> result = null;
        UserDto dto = null;
        int id;
        String name, role;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select id,username,role from tblUsers Where active = 1";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                    name = rs.getString("username");
                    role = rs.getString("role");
                    dto = new UserDto(id, name, role);
                    result.add(dto);
                }
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean updateUser(UserDto dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "update tblUsers set username = ?, role = ? where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getRole());
                stm.setInt(3, dto.getId());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteUser(int userId) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "update tblUsers set active = ? where id = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setInt(2, userId);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertUser(UserDto dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "insert into tblUsers (username, role,active) values (?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getRole());
                stm.setBoolean(3, true);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public UserDto getUserById(int id) throws ClassNotFoundException, SQLException {
        UserDto dto = null;
        String name, role;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Select username,role from tblUsers Where active = 1 and id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("username");
                    role = rs.getString("role");
                    dto = new UserDto(id, name, role);
                }
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public boolean getUserAuth(String username, String password) throws SQLException, ClassNotFoundException {
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "SELECT id FROM tblUsers WHERE username = ? AND password = ? AND active = 'TRUE'";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            closeConnection();
        }
        return false;
    }
}
