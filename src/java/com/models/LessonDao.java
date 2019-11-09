package com.models;

import com.dtos.LessonDto;
import com.utils.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

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

    public ArrayList<LessonDto> getLessonByCourseId(int courseId) throws ClassNotFoundException, SQLException {
        ArrayList<LessonDto> result = null;
        LessonDto dto = null;
        int id;
        String name, description, video_link;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select l.id,l.name,l.description,l.video_link from tblLessons l join tblCourses_Lessons c on l.id = c.lesson_id Where l.active = 1 and c.course_id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, courseId);
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

    public LessonDto findLessonById(int id) throws ClassNotFoundException, SQLException {
        LessonDto dto = null;
        String name, description, videoLink;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Select id, name,description,video_link From tblLessons Where id=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    name = rs.getString("name");
                    description = rs.getString("description");
                    videoLink = rs.getString("video_link");
                    dto = new LessonDto(id, name, description, videoLink);
                }
            }
        } finally {
            closeConnection();
        }

        return dto;
    }

    public ArrayList<LessonDto> getAllLessons() throws ClassNotFoundException, SQLException {
        ArrayList<LessonDto> result = null;
        LessonDto dto = null;
        int id;
        String name, description, video_link;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                result = new ArrayList<>();
                String sql = "Select l.id,l.name,l.description,l.video_link from tblLessons l Where l.active = 'True'";
                stm = con.prepareStatement(sql);
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

    public boolean insert(LessonDto dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        String originalLink = dto.getVideoLink();
        String newLink = "";
        if (originalLink != null && !originalLink.equals("")) {
            int index = originalLink.indexOf("v=");
            newLink = originalLink.substring(index += 2);
        }
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "Insert into tblLessons(name,description,video_link,active) values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getDescription());
                stm.setString(3, newLink);
                stm.setBoolean(4, true);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getInsertedId() throws ClassNotFoundException, SQLException {
        int id = 0;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "select MAX(id) as 'id' from tblLessons";
                stm = con.prepareStatement(sql);
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

    public boolean insertCourseLesson(int courseId, int lessonId) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "insert into tblCourses_Lessons (course_id, lesson_id) values (?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, courseId);
                stm.setInt(2, lessonId);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(LessonDto dto) throws ClassNotFoundException, SQLException {
        boolean check = false;
        String originalLink = dto.getVideoLink();
        String newLink = "";
        if (originalLink != null && !originalLink.equals("")) {
            int index = originalLink.indexOf("v=");
            newLink = originalLink.substring(index += 2);
        }
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "update tblLessons set name=? ,description=?, video_link=? ,active=? where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getDescription());
                stm.setString(3, newLink);
                stm.setBoolean(4, true);
                stm.setInt(5, dto.getId());
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateCourseLesson(int newcourseId, int oldCourseId, int lessonId) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "update tblCourses_Lessons set course_id = ? where course_id =? and lesson_id=?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, newcourseId);
                stm.setInt(2, oldCourseId);
                stm.setInt(3, lessonId);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(int id) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = MyConnection.getConnection();
            if (con != null) {
                String sql = "update tblLessons set active=? where id = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setInt(2, id);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
