<%-- 
    Document   : courseDetiail
    Created on : Nov 5, 2019, 2:19:40 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="admin/css/simple-sidebar.css" rel="stylesheet">
        <title>Course Detail</title>
    </head>
    <script>
        function setDelete() {
            if (confirm("Do you want to delete this course?")) {
                document.getElementById("key").value = "deleteCourse";
                document.getElementById("form").submit();
            } else {

            }
        }
    </script>
    <body>
        <div class="input_box">
            <form action="CourseController" id="form" >
                Course Name:  <input type="text" class="insert_box" value="${requestScope.courseName}" name="courseName" placeholder="enter a course name" required="true"/><br/>
                <input type="hidden" name="key" value="updateCourse" id="key"/>
                <input type="hidden" name="courseId" value="${requestScope.courseId}"/>
                <button type="submit" class="btn-info btn_insert">Update</button>
                <button type="button" onclick="setDelete()" class="btn-danger btn_insert">Delete</button>
            </form>
        </div>
    </body>
</html>