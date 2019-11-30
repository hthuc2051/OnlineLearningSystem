<%-- 
    Document   : profile
    Created on : Nov 29, 2019, 8:58:56 PM
    Author     : T.Z.B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Online Learning System</title>
        <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet"
              type="text/css">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <c:url value="CourseController" var="homeUserPage">
                    <c:param name="key" value="COURSES_USER" ></c:param>
                </c:url>
                <a class="navbar-brand" href="${homeUserPage}">Online Learning System</a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <c:url value="CourseController" var="homeUserPage">
                                <c:param name="key" value="COURSES_USER" ></c:param>
                            </c:url>
                            <a class="nav-link" href="${homeUserPage}">Home
                            </a>
                        </li>
                        <c:if test="${sessionScope.USERNAME == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="registration/login.jsp">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="registration/signup.jsp">Sign up</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.USERNAME != null}">
                            <li class="nav-item">
                                <c:url value="CourseController" var="viewYourCourses">
                                    <c:param name="key" value="VIEW_YOUR_COURSES" ></c:param>
                                </c:url>
                                <a class="nav-link" href="${viewYourCourses}">Your courses</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white active" href="#"><u>Welcome, ${sessionScope.USERNAME}</u></a>
                            </li>                            
                            <li class="nav-item">
                                <c:url value="CourseController" var="logout">
                                    <c:param name="key" value="LOG_OUT" ></c:param>
                                </c:url>
                                <a class="nav-link" href="${logout}">Log Out</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Header - set the background image for the header in the line below -->
        <header class="pt-5 pb-3 bg-image-full" style="background-image: url('https://wallpaperaccess.com/full/1426870.png');">
            <div class="container d-flex align-items-center flex-column">
                <img class="masthead-avatar mb-3 mt-5 rounded-circle bg-light" src="${USER_PROFILE.image}" style="width: 250px; height: 250px;" alt="">
                <h1 class="masthead-heading text-white">${USER_PROFILE.fullname}</h1>
            </div>
        </header>

        <!-- Masthead -->
        <header class="text-dark text-center bg-light" style="height: 400px;">
            <div class="container d-flex flex-column">
                <div class="col-md-8 offset-md-2">
                    <div class="card-body">
                        <div class="row mt-3">
                            <div class="col-lg-6">
                                <ul class="list-unstyled mb-0 text-left">
                                    <li>
                                        <h2 class="mt-1">Email:</h2>
                                    </li>
                                    <li>
                                        <h2 class="mt-1">Date of Birth:</h2>
                                    </li>
                                    <li>
                                        <h2 class="mt-1">Balance:</h2>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-lg-6">
                                <ul class="list-unstyled mb-0 text-right">
                                    <li>
                                        <h2 class="mt-1 text-black-50">${USER_PROFILE.name}</h2>
                                    </li>
                                    <li>
                                        <h2 class="mt-1 text-black-50">${USER_PROFILE.birthdate}</h2>
                                    </li>
                                    <li>
                                        <h2 class="mt-1 text-black-50" style="color: #ed6663 !important">${USER_PROFILE.balance}$</h2>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Button trigger modal -->
            <button type="button" class="btn btn-lg btn-primary mt-3 mb-5" data-toggle="modal" data-target="#profile">Edit My Profile</button>

            <script>
                function isValidDate(dateString) {
                    var regEx = /^\d{4}-\d{2}-\d{2}$/;
                    return dateString.match(regEx) != null;
                }
            </script>

            <!-- Modal -->
            <div class="modal fade " id="profile" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/UserController" method="post" accept-charset="UTF-8" 
                              oninput='confirm_password.setCustomValidity(confirm_password.value != password.value ? "Passwords do not match." : ""); 
                              dateofbirth.setCustomValidity(isValidDate(dateofbirth.value) != true ? "Invalid Date." : "")'>
                            <div class="modal-header">
                                <h4 class="modal-title" id="exampleModalLabel">Edit Profile</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group text-left">
                                    <label for="fullname">Full Name</label>
                                    <input type="text" class="form-control" id="fullname" placeholder="John Doe" name="txtFullName" value="${USER_PROFILE.fullname}" required/>
                                </div>
                                <div class="form-group text-left">
                                    <label for="dateofbirth">Date of Birth</label>
                                    <input type="text" class="form-control" id="dateofbirth" placeholder="2019-12-1" name="txtBirthDate" value="${USER_PROFILE.birthdate}"/>
                                </div>
                                <div class="form-group text-left">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" placeholder="Your password" name="txtPassword" value=""/>
                                </div>
                                <div class="form-group text-left">
                                    <label for="confirm_password">Confirm-Password</label>
                                    <input type="password" class="form-control" id="confirm_password" placeholder="Confirm password" name="confirm_password"/>
                                </div>
                                <div class="form-group text-left">
                                    <label for="image">Image</label>
                                    <input type="text" class="form-control" id="image" placeholder="Image url" name="txtImage" value="${USER_PROFILE.image}"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="hidden" name="txtId" value="${USER_PROFILE.id}"/>
                                <button type="submit" class="btn btn-primary" name="key" value="UPDATE_PROFILE">Save changes</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </header>

        <!-- Footer -->
        <footer class="py-4 bg-dark">
            <div class="container">
                <p class="m-0 lead text-white">Online learning system</p>
                <p class="m-0  text-white">Phone no : 0909111111</p>
                <p class="m-0  text-white">Email: onlinelearningsystem@gmail.com</p>
                <p class="m-0  text-white">Location: 123A Quang Trung, Ho Chi Minh city</p>

            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

    </body>

</html>
