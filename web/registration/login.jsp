<%-- 
    Document   : login
    Created on : Nov 7, 2019, 3:58:56 PM
    Author     : T.Z.B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Learning System</title>
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>

    </head>
    <body>        
        <div class="container-fluid">
            <div class="row no-gutter">
                <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
                <div class="col-md-8 col-lg-6">
                    <div class="login d-flex align-items-center py-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-9 col-lg-8 mx-auto">
                                    <h3 class="login-heading mb-4">Welcome to Online Learning System!</h3>
                                    <form action="${pageContext.request.contextPath}/CourseController" method="POST">
                                        <div class="form-label-group">
                                            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name="txtEmail" value="${requestScope.USERNAME}" required autofocus/>
                                            <label for="inputEmail">Email address</label>
                                        </div>

                                        <div class="form-label-group">
                                            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="txtPassword" required/>
                                            <label for="inputPassword">Password</label>
                                        </div>
                                        <c:if test="${requestScope.ERRORTEXT != null}">
                                            <h5 class="text-danger mb-2">${requestScope.ERRORTEXT}</h5>
                                        </c:if>
                                        <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit" name="key" value="Sign In">Sign In</button>
                                        <div class="text-center">
                                            <a class="small" href="${pageContext.request.contextPath}/registration/signup.jsp">Sign Up Here</a></div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
