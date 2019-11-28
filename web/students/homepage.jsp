<%-- 
    Document   : homepage
    Created on : Nov 5, 2019, 7:53:44 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="custag" %>

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
                <a class="navbar-brand" href="#">Online Learning System</a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <custag:if test="${sessionScope.USERNAME == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="registration/login.jsp">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="registration/signup.jsp">Sign up</a>
                            </li>
                        </custag:if>
                        <custag:if test="${sessionScope.USERNAME != null}">
                            <li class="nav-item">
                                <custag:url value="CourseController" var="viewYourCourses">
                                    <custag:param name="key" value="VIEW_YOUR_COURSES" ></custag:param>
                                </custag:url>
                                <a class="nav-link" href="${viewYourCourses}">Your courses</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white">Welcome, ${sessionScope.USERNAME}</a>
                            </li>                            
                            <li class="nav-item">
                                <custag:url value="CourseController" var="logout">
                                    <custag:param name="key" value="LOG_OUT" ></custag:param>
                                </custag:url>
                                <a class="nav-link" href="${logout}">Log Out</a>
                            </li>
                        </custag:if>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Header - set the background image for the header in the line below -->
        <header class="py-5 bg-image-full" style="background-image: url('https://wallpaperaccess.com/full/1426870.png');">
            <img class="logo mt-2 d-block mx-auto" src="img/logo.png" alt="">
            <p class="logo-title">Online Learning System</p>
            <!-- Icon Divider -->
            <div class="divider-custom divider-light">
                <div class="divider-custom-line"></div>
                <div class="divider-custom-icon">
                    <i class="icon-star"></i>
                </div>
                <div class="divider-custom-line"></div>
            </div>
        </header>

        <!-- Content section -->
        <section class="py-5">
            <div class="container">
                <h1>Hi, Welcome to our Learning system !</h1>
                <p class="lead">We are the educational framework of the future. Let's checkout our latest courses</p>
            </div>
            <!-- Page Features -->
            <div class="row text-center m-5">
                <custag:if test="${requestScope.ListTop4 !=null}">
                    <custag:if test="${not empty requestScope.ListTop4}">
                        <custag:forEach items="${requestScope.ListTop4}" var="dto" varStatus="counter">
                            <div class="col-lg-3 col-md-6 mb-4">
                                <div class="card h-100">

                                    <div class="card-body">
                                        <h4 class="card-title">${dto.name}</h4>
                                        <p class="card-text">${dto.description}</p>
                                    </div>
                                    <div class="card-footer">
                                        <custag:url value="CourseController" var="enrollLink">
                                            <custag:param name="key" value="ENROLL_NOW" ></custag:param>
                                            <custag:param name="txtId" value="${dto.id}" ></custag:param>
                                        </custag:url>
                                        <a href="${enrollLink}" class="btn btn-primary">Enroll Now!</a>
                                    </div>
                                </div>
                            </div>
                        </custag:forEach>
                    </custag:if>
                </custag:if>

            </div>
            <!-- /.row -->
        </section>

        <!-- Image Section - set the background image for the header in the line below -->
        <section class="py-5 bg-image-full" style="background-image: url('img/beach.jpg');">
            <img class="logo mt-2 d-block mx-auto" src="img/smart-icon.jpg" alt="">
            <p class="text-center quote">" Research is creating new knowledge "</p>
            <p class="text-center quote">Neil Armstrong</p>
        </section>
        <!-- Content section -->
        <section class="py-5">
            <div class="container">
                <div class="divider-custom text-center">
                    <div class="divider-custom-line"></div>
                    <div class="divider-custom-icon">
                        <i class="icon-book-open"></i>
                    </div>
                    <div class="divider-custom-line"></div>
                </div>
                <h3 class="text-center">ALL COURSES</h3>


            </div>
            <!-- Page Features -->
            <div class="row text-center m-5">

                <custag:if test="${requestScope.ListAllCourses !=null}">
                    <custag:if test="${not empty requestScope.ListAllCourses}">
                        <custag:forEach items="${requestScope.ListAllCourses}" var="dto" varStatus="counter">
                            <div class="col-lg-3 col-md-6 mb-4">
                                <div class="card h-100">

                                    <div class="card-body">
                                        <h4 class="card-title">${dto.name}</h4>
                                        <p class="card-text">${dto.description}</p>
                                    </div>
                                    <div class="card-footer">
                                        <custag:url value="CourseController" var="enrollLink">
                                            <custag:param name="key" value="ENROLL_NOW" ></custag:param>
                                            <custag:param name="txtId" value="${dto.id}" ></custag:param>
                                        </custag:url>
                                        <a href="${enrollLink}" class="btn btn-primary">Enroll Now!</a>
                                    </div>
                                </div>
                            </div>
                        </custag:forEach>
                    </custag:if>
                </custag:if>
            </div>
            <!-- /.row -->
        </section>

        <section class="features-icons bg-light text-center">
            <hr />
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                            <div class="features-icons-icon d-flex">
                                <i class="icon-notebook m-auto text-primary"></i>
                            </div>
                            <h3>Easy to take note, remember lesson</h3>
                            <p class="lead mb-0">Contains easy-to-remember knowledge, take note will become easier !</p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-5 mb-lg-0 mb-lg-3">
                            <div class="features-icons-icon d-flex">
                                <i class="icon-location-pin m-auto text-primary"></i>
                            </div>
                            <h3>With Internet, Learn everywhere</h3>
                            <p class="lead mb-0">Learn everywhere you like, convenient for busy people !</p>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto mb-0 mb-lg-3">
                            <div class="features-icons-icon d-flex">
                                <i class="icon-check m-auto text-primary"></i>
                            </div>
                            <h3>Easy To Learn, Easy to understand</h3>
                            <p class="lead mb-0">Our system supports users to gain best experience !</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
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
