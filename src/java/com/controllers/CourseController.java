/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.beans.CourseBean;
import com.dtos.CourseDto;
import com.models.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class CourseController extends HttpServlet {

    private final String HOME_PAGE = "adminFolder/admin.jsp";
    private final String ERROR_PAGE = "error.jsp";
    private final String PAGE = "loadCourse";
    private final String HOME_USER = "students/homepage.jsp";
    private final String USER_COURSES = "students/userCourses.jsp";
    private final String COURSE_DETAILS_USER = "students/courseDetails.jsp";
    private final String LOGIN_PAGE = "/registration/login.jsp";
    private final String SIGNUP_PAGE = "/registration/signup.jsp";
    private final String ADMIN = "ADMIN";
    private final String USER = "USER";

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
            if (key == null) {
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
            } else {
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
                        HttpSession session = request.getSession();
                        if (session.getAttribute("USERNAME") == null) {
                            List<CourseDto> listCourses = bean.loadAllCourse();
                            List<CourseDto> top4Courses = new ArrayList<>();
                            if (listCourses != null && listCourses.size() > 4) {
                                for (int i = 0; i < 4; i++) {
                                    top4Courses.add(listCourses.get(i));
                                }
                            }
                            request.setAttribute("ListTop4", top4Courses);
                            request.setAttribute("ListAllCourses", listCourses);
                        } else {
                            String userId = (String) session.getAttribute("USER_ID");
                            session.setAttribute("USER_ID", userId);
                            List<CourseDto> listCourses = bean.getUnEnrollCoursesByUsername(userId);
                            List<CourseDto> top4Courses = new ArrayList<>();
                            if (listCourses != null && listCourses.size() > 4) {
                                for (int i = 0; i < 4; i++) {
                                    top4Courses.add(listCourses.get(i));
                                }
                            }
                            request.setAttribute("ListTop4", top4Courses);
                            request.setAttribute("ListAllCourses", listCourses);
                        }
                        url = HOME_USER;
                        break;
                    case "VIEW_YOUR_COURSES":
                        HttpSession sessionViewCourse = request.getSession();
                        if (sessionViewCourse.getAttribute("USERNAME") == null) {
                            url = LOGIN_PAGE;
                        } else {
                            String username = (String) sessionViewCourse.getAttribute("USERNAME");
                            List<CourseDto> listUserCourses = bean.loadUserCourses(username);
                            request.setAttribute("ListAllCourses", listUserCourses);
                            url = USER_COURSES;
                        }

                        break;
                    case "USER_COURSE_DETAILS":
                        String id = request.getParameter("txtId");
                        session = request.getSession();
                        if (session.getAttribute("USERNAME") == null) {
                            url = LOGIN_PAGE;
                        } else {
                            CourseDto dto = bean.getCourseDetails(Integer.parseInt(id));
                            request.setAttribute("Dto", dto);
                            url = COURSE_DETAILS_USER;
                        }
                        break;
                    case "Sign In":
                        String username = request.getParameter("txtEmail");
                        String password = request.getParameter("txtPassword");
                        UserDao dao = new UserDao();
                        String checkLogin = dao.getUserAuth(username, password);
                        if (checkLogin.toUpperCase().trim().equals(USER)) {
                            session = request.getSession();
                            session.setAttribute("USERNAME", username);
                            String user_id = dao.findUserByUsername(username);

                            if (user_id == null && user_id.isEmpty()) {
                                request.setAttribute("ERROR", "There are something wrong about your request, please try again later!");
                                url = ERROR_PAGE;
                            } else {
                                session.setAttribute("USER_ID", user_id);
                                // load unenroll course by username
                                List<CourseDto> listCourses = bean.getUnEnrollCoursesByUsername(user_id);
                                List<CourseDto> top4Courses = new ArrayList<>();
                                if (listCourses != null && listCourses.size() > 4) {
                                    for (int i = 0; i < 4; i++) {
                                        top4Courses.add(listCourses.get(i));
                                    }
                                }
                                request.setAttribute("ListTop4", top4Courses);
                                request.setAttribute("ListAllCourses", listCourses);
                                url = HOME_USER;
                            }
                        } else if (checkLogin.toUpperCase().trim().equals(ADMIN)) {
                            session = request.getSession();
                            session.setAttribute("USERNAME", username);
                            request = loadAllCourses(request, bean);
                        } else {
                            String errorText = "Invalid Username or Password!";
                            request.setAttribute("ERRORTEXT", errorText);
                            request.setAttribute("USERNAME", username);
                            url = LOGIN_PAGE;
                        }
                        break;
                    case "LOG_OUT":
                        session = request.getSession();
                        session.removeAttribute("USERNAME");
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
                    case "Sign Up":
                        String signup_username = request.getParameter("email");
                        String signup_password = request.getParameter("password");
                        dao = new UserDao();
                        if (signup_username.trim() != null
                                && signup_password.trim() != null
                                && !signup_username.trim().isEmpty()
                                && !signup_password.trim().isEmpty()) {
                            if (dao.checkExistedUsername(signup_username)) {
                                String error = "Email Address is already existed!";
                                request.setAttribute("ERROR", error);
                                url = SIGNUP_PAGE;
                            } else {
                                if (dao.createNewUser(signup_username, signup_password)) {
                                    request.setAttribute("USERNAME", signup_username);
                                    url = LOGIN_PAGE;
                                } else {
                                    url = ERROR_PAGE;
                                }
                            }
                        } else {
                            String error = "Password is empty!";
                            request.setAttribute("ERROR", error);
                            url = SIGNUP_PAGE;
                        }
                        request.setAttribute("USERNAME", signup_username);
                        break;
                    case "ENROLL_NOW":
                        String courseId = request.getParameter("txtId");
                        session = request.getSession();
                        if (session.getAttribute("USERNAME") == null) {
                            url = LOGIN_PAGE;
                        } else {
                            String userId = (String) session.getAttribute("USER_ID");
                            boolean check = bean.enrollCourse(Integer.parseInt(courseId), Integer.parseInt(userId));
                            if (check) {
                                session.setAttribute("USER_ID", userId);
                                // load unenroll course by username
                                listCourses = bean.getUnEnrollCoursesByUsername(userId);
                                top4Courses = new ArrayList<>();
                                if (listCourses != null && listCourses.size() > 4) {
                                    for (int i = 0; i < 4; i++) {
                                        top4Courses.add(listCourses.get(i));
                                    }
                                }
                                request.setAttribute("ListTop4", top4Courses);
                                request.setAttribute("ListAllCourses", listCourses);
                                url = HOME_USER;
                            } else {
                                url = ERROR_PAGE;
                            }
                        }

                        break;
                }
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", e.toString());
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
