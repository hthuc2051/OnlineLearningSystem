/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtos;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class UserDto implements Serializable {

    private int id;
    private String name, password, role;
    private String fullname, birthdate;

    public UserDto(int id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public UserDto(int id, String name, String role, String fullname, String birthdate) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.fullname = fullname;
        this.birthdate = birthdate;
    }

    public UserDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDto(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

}
