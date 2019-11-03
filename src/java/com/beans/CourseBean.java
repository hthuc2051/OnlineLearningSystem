/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.dtos.LessonDto;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author HP
 */
public class CourseBean implements Serializable{
    private int id;
    private String name;
    private List<LessonDto> lessons;
}
