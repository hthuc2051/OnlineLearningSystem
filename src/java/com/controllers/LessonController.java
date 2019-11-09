/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.beans.CourseBean;
import com.beans.LessonBean;
import com.dtos.CourseDto;
import com.dtos.LessonDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LessonController extends HttpServlet {

    private final String HOME_PAGE = "adminFolder/admin.jsp";
    private final String ERROR_PAGE = "error.jsp";
    private final String PAGE = "loadLesson";
    private final String LESSON_DETAILS = "students/lessonDetails.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PAGE;
        try {
            LessonBean bean = new LessonBean();
            String key = request.getParameter("key");
            switch (key) {
                case "getLessonsById":
                    String courseId = request.getParameter("txtId");
                    request = getLessonsById(request, bean, courseId);
                    break;
                case "USER_LESSON_DETAILS":
                    String id = request.getParameter("txtId");
                    LessonDto dto = bean.getLessonDetails(Integer.parseInt(id));
                    request.setAttribute("Dto", dto);
                    url = LESSON_DETAILS;
                    break;
                case "insertCourse":
                    request = insertLesson(request, bean);
                    break;
                case "loadLessons":
                    request = getAllLessons(request, bean);
                    break;
                case "insertPage":
                    request = loadInsertPage(request, bean);
                    url = "adminFolder/insertLesson.jsp";
                    break;
                case "lessonDetail":
                    url = "adminFolder/lessonDetail.jsp";
                    request = loadLessonDetail(request, bean);
                    break;
                case "updateLesson":
                    request = updateLesson(request, bean);
                    break;
                case "deleteLesson":
                    request = deleteLesson(request, bean);
                    break;
            }

        } catch (Exception e) {
            request.setAttribute("ERROR", e.toString());
            url = ERROR_PAGE;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private HttpServletRequest getAllLessons(HttpServletRequest request, LessonBean bean) throws ClassNotFoundException, SQLException {
        ArrayList<LessonDto> listLesson = bean.getAllLessons();
        HttpSession sesson = request.getSession();
        sesson.removeAttribute("COURSEID");
        request.setAttribute("LISTLESSON", listLesson);
        request.setAttribute("PAGE", PAGE);
        return request;
    }

    private HttpServletRequest getLessonsById(HttpServletRequest request, LessonBean bean, String courseId) throws ClassNotFoundException, SQLException {
        HttpSession sesson = request.getSession();
        sesson.setAttribute("COURSEID", courseId);
        bean.setId(Integer.parseInt(courseId));
        ArrayList<LessonDto> listLesson = bean.loadLessonByCourseId();
        request.setAttribute("LISTLESSON", listLesson);
        request.setAttribute("PAGE", PAGE);
        return request;
    }

    private HttpServletRequest insertLesson(HttpServletRequest request, LessonBean bean) throws ClassNotFoundException, SQLException {
        String lessonName = request.getParameter("txtLessonName");
        String description = request.getParameter("txtLessonDescription");
        String link = request.getParameter("txtLessonLink");
        String courseId = request.getParameter("txtCourseId");
        bean.setName(lessonName);
        bean.setDescription(description);
        bean.setVideoLink(link);
        boolean check = bean.insertLesson(Integer.parseInt(courseId));
        if (check) {
            request.setAttribute("STATUS", "Insert successfully!");
        } else {
            request.setAttribute("STATUS", "Insert fail!");
        }
        request = reloadPage(request, bean);
        return request;
    }

    private HttpServletRequest reloadPage(HttpServletRequest request, LessonBean bean) throws ClassNotFoundException, SQLException {
        HttpSession sesson = request.getSession();
        String courseId = (String) sesson.getAttribute("COURSEID");
        if (courseId != null) {
            request = getLessonsById(request, bean, courseId);
        } else {
            request = getAllLessons(request, bean);
        }
        return request;
    }

    private HttpServletRequest loadInsertPage(HttpServletRequest request, LessonBean bean) throws ClassNotFoundException, SQLException {
        CourseBean courseBean = new CourseBean();
        ArrayList<CourseDto> listCourse = courseBean.loadAllCourse();
        request.setAttribute("LISTCOURSE", listCourse);
        return request;
    }

    private HttpServletRequest loadLessonDetail(HttpServletRequest request, LessonBean bean) throws ClassNotFoundException, SQLException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String link = request.getParameter("link");
        LessonDto dto = new LessonDto(Integer.parseInt(id), name, description, link);
        request.setAttribute("LESSON", dto);
        LessonBean lessonBean = new LessonBean();
        lessonBean.setId(Integer.parseInt(id));
        int courseId = lessonBean.getCourseIdByLessonId();
        HttpSession sesson = request.getSession();
        sesson.setAttribute("COURSESELECTED", courseId + "");
        request = loadInsertPage(request, bean);
        return request;
    }

    private HttpServletRequest updateLesson(HttpServletRequest request, LessonBean bean) throws ClassNotFoundException, SQLException {
        String lessonId = request.getParameter("lessonId");
        String lessonName = request.getParameter("txtLessonName");
        String description = request.getParameter("txtLessonDescription");
        String link = request.getParameter("txtLessonLink");
        String courseId = request.getParameter("txtCourseId");
        HttpSession sesson = request.getSession();
        String oldCourseId = (String) sesson.getAttribute("COURSESELECTED");
        bean.setId(Integer.parseInt(lessonId));
        bean.setName(lessonName);
        bean.setDescription(description);
        bean.setVideoLink(link);
        boolean check = bean.uploadLesson(Integer.parseInt(courseId), Integer.parseInt(oldCourseId));
        if (check) {
            request.setAttribute("STATUS", "Update successfully!");
        } else {
            request.setAttribute("STATUS", "Update fail!");
        }
        request = reloadPage(request, bean);
        return request;
    }

    private HttpServletRequest deleteLesson(HttpServletRequest request, LessonBean bean) throws ClassNotFoundException, SQLException {
        String lessonId = request.getParameter("lessonId");
        bean.setId(Integer.parseInt(lessonId));
        boolean check = bean.deleteLesson();
        if (check) {
            request.setAttribute("STATUS", "Delete successfully!");
        } else {
            request.setAttribute("STATUS", "Delete fail!");
        }
        request = reloadPage(request, bean);
        return request;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
