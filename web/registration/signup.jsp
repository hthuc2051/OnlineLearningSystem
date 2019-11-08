<%-- 
    Document   : signup
    Created on : Nov 7, 2019, 5:43:43 PM
    Author     : T.Z.B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Online Learning System</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
        <link href="${pageContext.request.contextPath}/css/signup.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="signup-form">
            <form action="${pageContext.request.contextPath}/CourseController" method="post" oninput='confirm_password.setCustomValidity(confirm_password.value != password.value ? "Passwords do not match." : "")'>
                <h2>Create an Account</h2>
                <div class="form-group">
                    <input type="email" class="form-control input-lg" name="email" placeholder="Email Address" value="${requestScope.USERNAME}" required="required">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control input-lg" id="password" name="password" placeholder="Password" required="required">
                </div>
                <div class="form-group"> 
                    <input type="password" class="form-control input-lg" id="confirm_password" name="confirm_password" placeholder="Confirm Password" required="required">
                </div>
                <c:if test="${requestScope.ERROR != null}">
                    <h5 class="text-danger mb-2">${requestScope.ERROR}</h5>
                </c:if>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-lg btn-block signup-btn" id="submit" name="key" value="Sign Up">Sign Up</button>
                </div>
                <div class="text-center text-white">Already have an account? <a href="${pageContext.request.contextPath}/registration/login.jsp">Login here</a></div>
            </form>
        </div>

    </body>
</html>                            
