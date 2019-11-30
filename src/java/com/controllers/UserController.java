/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.beans.UserBean;
import com.dtos.UserDto;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
public class UserController extends HttpServlet {

    private final String HOME_PAGE = "adminFolder/admin.jsp";
    private final String ERROR_PAGE = "error.jsp";
    private final String PAGE = "loadUser";

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
            String key = request.getParameter("key");
            UserBean bean = new UserBean();
            switch (key) {
                case "loadUser":
                    request = loadAllUser(request, bean);
                    break;
                case "insertUser":
                    String userName = request.getParameter("txtUsername");
                    bean.setName(userName);
                    boolean check = bean.isUserExisted();
                    if (!check) {
                        request = insertUser(request, bean);
                    } else {
                        request.setAttribute("DUPLICATE", "This email is existed!");
                        url = "adminFolder/insertUser.jsp";
                    }
                    break;
                case "userDetail":
                    request.setAttribute("name", request.getParameter("name"));
                    request.setAttribute("id", request.getParameter("id"));
                    request.setAttribute("role", request.getParameter("role"));
                    url = "adminFolder/userDetail.jsp";
                    break;
                case "updateUser":
                    request = updateUser(request, response, bean);
                    break;
                case "deleteUser":
                    request = deleteUser(request, bean);
                    break;
                case "editProfile":
                    request = getUserById(request, bean);
                    url = "adminFolder/userProfile.jsp";
                    break;
                case "VIEW_PROFILE":
                    HttpSession session = request.getSession();
                    if (session != null) {
                        String username = (String) session.getAttribute("USERNAME");
                        bean.setName(username);
                        request = loadUserProfileByUsername(request, bean);
                        url = "students/profile.jsp";
                    } else {
                        url = ERROR_PAGE;
                    }
                    break;
                case "UPDATE_PROFILE":
                    String id = request.getParameter("txtId");
                    String fullname,
                     password,
                     birthdate,
                     image;
                    password = request.getParameter("txtPassword");
                    fullname = request.getParameter("txtFullName");
                    byte[] bytes = fullname.getBytes(StandardCharsets.ISO_8859_1);
                    fullname = new String(bytes, StandardCharsets.UTF_8);
                    birthdate = request.getParameter("txtBirthDate");
                    image = request.getParameter("txtImage");
                    bean.setFullname(fullname);
                    bean.setBirthdate(birthdate);
                    bean.setImage(image);
                    bean.setId(Integer.parseInt(id));
                    bean.setPassword(password);
                    boolean updated = bean.updateProfile();
                    if (updated) {
                        session = request.getSession();
                        if (session != null) {
                            String username = (String) session.getAttribute("USERNAME");
                            bean.setName(username);
                            request = loadUserProfileByUsername(request, bean);
                            url = "students/profile.jsp";
                        } else {
                            url = ERROR_PAGE;
                        }
                    } else {
                        url = ERROR_PAGE;
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", e.toString());
            url = ERROR_PAGE;
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private HttpServletRequest loadUserProfileByUsername(HttpServletRequest request, UserBean bean) throws ClassNotFoundException, SQLException {
        UserDto user = bean.getUserByEmail();
        request.setAttribute("USER_PROFILE", user);
        return request;
    }

    private HttpServletRequest loadAllUser(HttpServletRequest request, UserBean bean) throws ClassNotFoundException, SQLException {
        ArrayList<UserDto> listUser = bean.getAllUser();
        request.setAttribute("PAGE", PAGE);
        request.setAttribute("LISTUSER", listUser);
        return request;
    }

    private HttpServletRequest insertUser(HttpServletRequest request, UserBean bean) throws ClassNotFoundException, SQLException {
        String userName = request.getParameter("txtUsername");
        String role = request.getParameter("txtRole");
        bean.setName(userName);
        bean.setRole(role);
        boolean check = bean.insetUser();
        if (check) {
            request.setAttribute("STATUS", "Insert successfully!");
        } else {
            request.setAttribute("STATUS", "Insert fail!");
        }
        request = loadAllUser(request, bean);
        return request;
    }

    private HttpServletRequest updateUser(HttpServletRequest request, HttpServletResponse response, UserBean bean) throws ClassNotFoundException, SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String role = request.getParameter("role");
        bean.setName(name);
//        HttpSession session = request.getSession();
//        String email = (String) session.getAttribute("USERNAME");
//        if(!email.equals(name)){
//            
//        }
//        boolean check = bean.isUserExisted();
//        if (check) {
//            String page_begin = request.getParameter("PAGE_BEGIN");
//            String url = "adminFolder/userDetail.jsp";
//            if (page_begin.equals("detail")) {
//                request.setAttribute("name", request.getParameter("name"));
//                request.setAttribute("id", request.getParameter("id"));
//                request.setAttribute("role", request.getParameter("role"));
//            } else {
//                request.setAttribute("key", "editProfile");
//                request = getUserById(request, bean);
//                url = "adminFolder/userProfile.jsp";
//            }
//            request.setAttribute("DUPLICATED", "This USERNAME IS ALREADY EXISTED");
//            request.getRequestDispatcher(url).forward(request, response);
//        } else {
//            bean.setId(Integer.parseInt(id));
//            bean.setRole(role);
//            boolean checkUpdate = bean.updateUser();
//            if (checkUpdate) {
//                request.setAttribute("STATUS", "Update successfully!");
//            } else {
//                request.setAttribute("STATUS", "Update fail!");
//            }
//            request = loadAllUser(request, bean);
//        }
        bean.setId(Integer.parseInt(id));
        bean.setRole(role);
        boolean checkUpdate = bean.updateUser();
        if (checkUpdate) {
            request.setAttribute("STATUS", "Update successfully!");
        } else {
            request.setAttribute("STATUS", "Update fail!");
        }
        request = loadAllUser(request, bean);
        return request;
    }

    private HttpServletRequest deleteUser(HttpServletRequest request, UserBean bean) throws ClassNotFoundException, SQLException {
        String id = request.getParameter("id");
        bean.setId(Integer.parseInt(id));
        boolean check = bean.deleteUser();
        if (check) {
            request.setAttribute("STATUS", "Delete successfully!");
        } else {
            request.setAttribute("STATUS", "Delete fail!");
        }
        request = loadAllUser(request, bean);
        return request;
    }

    private HttpServletRequest getUserById(HttpServletRequest request, UserBean bean) throws ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("USERNAME");
        bean.setName(email);
        UserDto dto = bean.getUserByEmail();
        request.setAttribute("name", dto.getName());
        request.setAttribute("id", dto.getId());
        request.setAttribute("role", dto.getRole());
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
