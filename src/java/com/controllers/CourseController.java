/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.beans.CourseBean;
import com.dtos.CourseDto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */


public class CourseController extends HttpServlet {

    private final String HOME_PAGE = "adminFolder/admin.jsp";
    private final String ERROR_PAGE = "error.jsp";
    private final String PAGE = "loadCourse";
    private final String HOME_USER = "students/homepage.jsp";
    private final String COURSE_DETAILS_USER = "students/courseDetails.jsp";

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
            request.removeAttribute("STATUS");
            String key = request.getParameter("key");
            CourseBean bean = new CourseBean();
            switch (key) {
                case "loadCourse":
                    request = loadAllCourses(request, bean);
                    break;
                case "insertCourse":
                    request = insertCourse(request, bean);
                    break;
                case "courseDetail":
                    request.setAttribute("courseName", request.getParameter("courseName"));
                    request.setAttribute("courseId", request.getParameter("courseId"));
                    request.setAttribute("courseDescription", request.getParameter("courseDescription"));
                    url = "adminFolder/courseDetail.jsp";
                    break;
                case "updateCourse":
                    request = updateCourse(request, bean);
                    break;
                case "deleteCourse":
                    request = deleteCourse(request, bean);
                    break;
                case "getUserCourse":
                    request = getUserCourse(request, bean);
                    break;
                case "COURSES_USER":
                    List<CourseDto> listCourses = bean.loadAllCourse();
                    List<CourseDto> top4Courses = new ArrayList<>();
                    if (listCourses != null && listCourses.size() > 4) {
                        for (int i = 0; i < 4; i++) {
                            top4Courses.add(listCourses.get(i));
                        }
                    }
                    request.setAttribute("ListTop4", top4Courses);
                    request.setAttribute("ListAllCourses", listCourses);
                    url = HOME_USER;
                    break;
                case "USER_COURSE_DETAILS":
                    String id = request.getParameter("txtId");
                    CourseDto dto = bean.getCourseDetails(Integer.parseInt(id));
                    request.setAttribute("Dto", dto);
                    url = COURSE_DETAILS_USER;
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            url = ERROR_PAGE;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private HttpServletRequest loadAllCourses(HttpServletRequest request, CourseBean bean) throws ClassNotFoundException, SQLException {
        ArrayList<CourseDto> list = bean.loadAllCourse();
        request.setAttribute("PAGE", PAGE);
        request.setAttribute("LISTCOURSE", list);
        return request;
    }

    private HttpServletRequest insertCourse(HttpServletRequest request, CourseBean bean) throws ClassNotFoundException, SQLException {
        String courseName = request.getParameter("txtCourseName");
        String description = request.getParameter("txtDescription");
        bean.setName(courseName);
        bean.setDescription(description);
        boolean check = bean.insertCourse();
        if (check) {
            request.setAttribute("STATUS", "Insert successfully!");
        } else {
            request.setAttribute("STATUS", "Insert fail!");
        }
        request = loadAllCourses(request, bean);
        return request;
    }

    private HttpServletRequest updateCourse(HttpServletRequest request, CourseBean bean) throws ClassNotFoundException, SQLException {
        String courseName = request.getParameter("courseName");
        String courseId = request.getParameter("courseId");
        String description = request.getParameter("txtDescription");
        bean.setName(courseName);
        bean.setDescription(description);
        bean.setId(Integer.parseInt(courseId));
        boolean check = bean.updateCourse();
        if (check) {
            request.setAttribute("STATUS", "Update successfully!");
        } else {
            request.setAttribute("STATUS", "Update fail!");
        }
        request = loadAllCourses(request, bean);
        return request;
    }

    private HttpServletRequest deleteCourse(HttpServletRequest request, CourseBean bean) throws ClassNotFoundException, SQLException {
        String courseId = request.getParameter("courseId");
        bean.setId(Integer.parseInt(courseId));
        boolean check = bean.deleteCourse();
        if (check) {
            request.setAttribute("STATUS", "Delete successfully!");
        } else {
            request.setAttribute("STATUS", "Delete fail!");
        }
        request = loadAllCourses(request, bean);
        return request;
    }

    private HttpServletRequest getUserCourse(HttpServletRequest request, CourseBean bean) throws ClassNotFoundException, SQLException {
        String userId = request.getParameter("txtId");
        ArrayList<CourseDto> listCourse = bean.getUserCourse(Integer.parseInt(userId));
        request.setAttribute("LISTCOURSE", listCourse);
        request.setAttribute("PAGE", PAGE);
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
