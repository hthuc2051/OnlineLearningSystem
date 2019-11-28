<%-- 
    Document   : courseDetail
    Created on : Nov 4, 2019, 8:35:02 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="../admin/css/simple-sidebar.css" rel="stylesheet">
        <title>Course Detail</title>
    </head>
    <body style="background-color: #F8F8F8">
        <div class="input_box">
            <div class="center_boxs" style="padding-top: 0;">
                <form action="../CourseController" >
                    <h2 style="color: darkgreen" class="mt-3">INSERT COURSE</h2>
                    <input type="text" class="insert_box" name="txtCourseName" placeholder="Course name" required="true"/><br/>
                    <input type="text" class="insert_box" name="txtDescription" placeholder="Description" required="true"/><br/>
                    <input type="text" class="insert_box" name="txtPrice" placeholder="Price" required="true"/><br/>
                    <input type="hidden" name="key" value="insertCourse"/>
                    <button type="submit" class="btn-info btn_insert">CREATE</button>
                </form>
            </div>
        </div>
    </body>
</html>
