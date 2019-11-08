<%-- 
    Document   : userDetial
    Created on : Nov 6, 2019, 1:00:58 PM
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
    <body style="background-color: #F8F8F8">
        <div class="input_box">
            <div class="center_boxs_detail">
                <form action="UserController" id="form" >
                    <h2 style="color: darkblue">PROFILE</h2>
                    Name:  <input type="text" class="insert_box" value="${requestScope.name}" name="name" required="true" readonly="true"/><br/>
                    Role:  <input type="text" class="insert_box" value="${requestScope.role}" name="role" required="true"/><br/>
                    <input type="hidden" name="key" value="updateUser" id="key"/>
                    <input type="hidden" name="id" value="${requestScope.id}"/>
                    <button type="submit" class="btn-info btn_insert">Update</button>
                    <h4 style="color: red">${requestScope.DUPLICATED}</h4>
                </form>
            </div>
        </div>
    </body>
</html>
