<%-- 
    Document   : insertUser
    Created on : Nov 6, 2019, 12:48:29 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/admin/css/simple-sidebar.css" rel="stylesheet">
        <title>Course Detail</title>
    </head>
    <body style="background-color: #F8F8F8">
        <div class="input_box">
            <div class="center_boxs">
                <form action="${pageContext.request.contextPath}/UserController" >
                    <h2 style="color: darkgreen">CREATE USER</h2>
                    <input type="email" class="insert_box" name="txtUsername" placeholder="User Email" required="true"/><br/>
                    <input type="text" class="insert_box" name="txtRole" placeholder="Role" required="true"/><br/>
                    <input type="hidden" name="key" value="insertUser"/>
                    <c:if test="${requestScope.DUPLICATE != null}">
                        <h5 style="color: red; margin-top: 5px">${requestScope.DUPLICATE}</h5>
                    </c:if>
                    <button type="submit" class="btn-info btn_insert">CREATE</button>
                </form>
            </div>
        </div>
    </body>
</html>
