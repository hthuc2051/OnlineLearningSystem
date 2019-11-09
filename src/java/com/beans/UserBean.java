/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dtos.UserDto;
import com.models.UserDao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class UserBean implements Serializable {

    private int id;
    private String name, password, role;
    UserDao dao = new UserDao();

    public UserBean() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<UserDto> getAllUser() throws ClassNotFoundException, SQLException {
        ArrayList<UserDto> listUser = (ArrayList<UserDto>) dao.getAllUsers();
        return listUser;
    }

    public boolean insetUser() throws ClassNotFoundException, SQLException {
        UserDto dto = new UserDto(id, name, role);
        boolean check = dao.insertUser(dto);
        return check;
    }

    public boolean updateUser() throws ClassNotFoundException, SQLException {
        UserDto dto = new UserDto(id, name, role);
        boolean check = dao.updateUser(dto);
        return check;
    }

    public boolean deleteUser() throws ClassNotFoundException, SQLException {
        boolean check = dao.deleteUser(id);
        return check;
    }

    public UserDto getUserByEmail() throws ClassNotFoundException, SQLException {
        UserDto dto = dao.getUserById(name);
        return dto;
    }

    public boolean isUserExisted() throws SQLException, ClassNotFoundException {
        boolean check = dao.checkExistedUsername(name);
        return check;
    }
}
