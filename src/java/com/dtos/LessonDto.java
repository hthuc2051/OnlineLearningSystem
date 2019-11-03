/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dtos;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class LessonDto implements Serializable {

    private int id;
    private String name, description, videoLink;
    private boolean active;
    
    public LessonDto(int id, String name, String description, String videoLink) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.videoLink = videoLink;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
