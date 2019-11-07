<%-- 
    Document   : courseDetails
    Created on : Nov 6, 2019, 4:01:11 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="custag" %>

<!DOCTYPE html>
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
                <a class="navbar-brand" href="#">Online Learning System</a>

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
                            <a class="nav-link" href="${homeUserPage}">Home
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Course : Tìm hiểu về Sơn Tùng MTP
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page Content -->
        <div class="container pt-5">
            <div class="divider-custom text-center">
                <div class="divider-custom-line"></div>
                <div class="divider-custom-icon">
                    <i class="icon-book-open"></i>
                </div>
                <div class="divider-custom-line"></div>
            </div>
            <custag:if test="${requestScope.Dto !=null}">
                <p style="font-style: italic; font-size: 20px;" class="text-center "></p>
            </custag:if>
            <div class="row">


                <div class="col-lg-12">
                    <div class="card mt-4">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Lesson</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Link</th>
                                </tr>
                            </thead>
                            <tbody>
                                <custag:if test="${requestScope.Dto !=null}">
                                    <custag:if test="${not empty requestScope.Dto.lessons}">
                                        <custag:forEach items="${requestScope.Dto.lessons}" var="dto" varStatus="counter">
                                            <tr>
                                                <th scope="row">${counter.count}</th>
                                                <td>${dto.name}</td>
                                                <td>${dto.description}</td>
                                                <td>
                                                    <custag:url value="LessonController" var="viewDetailsLesson">
                                                        <custag:param name="txtId" value="${dto.id}" ></custag:param>
                                                        <custag:param name="key" value="USER_LESSON_DETAILS" ></custag:param>
                                                    </custag:url>
                                                    <a href="${viewDetailsLesson}">Click to learn</a>
                                                </td>
                                            </tr>
                                        </custag:forEach>
                                    </custag:if>
                                </custag:if>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

        </div>
        <!-- /.container -->

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
