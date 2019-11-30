<%-- 
    Document   : lessonDetails
    Created on : Nov 6, 2019, 4:34:59 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="custag" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Modern Business - Start Bootstrap Template</title>

        <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet"
              type="text/css">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet">
        <script>
            function disable() {
                var x = document.getElementById("snackbar");
                x.className = "disable";
            }
        </script>

    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <custag:url value="CourseController" var="homeUserPage">
                    <custag:param name="key" value="COURSES_USER" ></custag:param>
                </custag:url>
                <a class="navbar-brand" href="${homeUserPage}">Online Learning System</a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item ">
                            <custag:url value="CourseController" var="homeUserPage">
                                <custag:param name="key" value="COURSES_USER" ></custag:param>
                            </custag:url>
                            <a class="nav-link" href="${homeUserPage}">Home</a>
                        </li>
                        <li class="nav-item">
                            <custag:url value="CourseController" var="viewYourCourses">
                                <custag:param name="key" value="VIEW_YOUR_COURSES" ></custag:param>
                            </custag:url>
                            <a class="nav-link" href="${viewYourCourses}">Your courses</a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#">
                                <u>
                                    <custag:if test="${requestScope.Dto !=null}">
                                        ${requestScope.Dto.name}
                                    </custag:if>
                                </u>
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <custag:url value="CourseController" var="logout">
                                <custag:param name="key" value="LOG_OUT" ></custag:param>
                            </custag:url>
                            <a class="nav-link" href="${logout}">Log Out</a>
                        </li>
                    </ul>
                </div>
            </div>

        </nav>
        <!-- Page Content -->
        <div class=" mt-5">
            <div class="col-lg-11 mx-auto ">
                <div id="carouselExampleIndicators" class="carousel slide my-5 pt-4 d-flex align-items-center"
                     data-ride="false" data-interval="5000000">

                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <custag:if test="${requestScope.Dto !=null}">
                                <iframe class="d-block img-fluid" width="100%" height="auto"
                                        src="https://www.youtube.com/embed/${requestScope.Dto.videoLink}" frameborder="0"
                                        allow="accelerometer; encrypted-media; gyroscope; picture-in-picture"
                                        allowfullscreen></iframe>
                                </custag:if>


                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="snackbar" class="float-right">
            <button onclick="disable()" type="button" class="close" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <custag:if test="${requestScope.Dto !=null}">
                <p>${requestScope.Dto.description}</p>
            </custag:if>

        </div>
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
