<%-- 
    Document   : index
    Created on : Nov 1, 2019, 7:48:49 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script>
        function submitForm(){
            document.getElementById("form").submit();
        }
    </script>
    <body onload="submitForm()">
        <form action="CourseController" id="form">
            <input type="submit" value="LOAD"/>
            <input type="hidden" value="COURSES_USER" name="key"/>
        </form>
    </body>
</html>
