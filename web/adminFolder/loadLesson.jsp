<%-- 
    Document   : loadCourse
    Created on : Nov 4, 2019, 12:50:29 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<link href="admin/css/simple-sidebar.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>  
    <script>
        function myFunction() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");
            for (i = 1; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td");
                if (td) {
                    flag = false;
                    for (j = 0; j < 2; j++) {
                        if (td[j]) {
                            txtValue = td[j].textContent || td[j].innerText;
                            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                flag = true
                            }
                        }
                    }
                    if (flag) {
                        tr[i].style.display = "";
                    }
                    else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
        function checkStatus(status) {
            if (status) {
                alert(status);
            }
        }
    </script>
    <body onload="checkStatus('${requestScope.STATUS}')">
        <c:set var="listLesson" value="${requestScope.LISTLESSON}"/>
        <c:if test="${empty listLesson}">
            <H1 style="text-align: center; margin-top: 100px; color: darkolivegreen"> There are no lessons</H1>
            <form action="LessonController" style="text-align: center">
                <input type="hidden" value="insertPage" name="key"/>
                <button type="submit" class="btn-primary active">Add New</button>
            </form>
        </c:if>
        <c:if test="${not empty listLesson}">
            <div id="header">
                <div class="row">
                    <div class="col-8">
                        <form action="LessonController">
                            <input type="hidden" value="insertPage" name="key"/>
                            <button type="submit" class="btn-primary active">Add New</button>
                        </form>
                    </div>
                    <div class="col-4">
                        <input type="text"  id="myInput" placeholder="enter a text" onkeyup="myFunction()"/>
                        <i class="fas fa-search"></i>
                    </div>
                </div>
            </div>
            <table align="center" class="table table-hover" style="margin-top: 10px" id="myTable">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>Lesson Name</th>
                        <th>Description</th>
                        <th>Video Link</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listLesson}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.name}</td>
                            <td>${dto.description}
                                <input type="hidden" value="${dto.id}" name="txtId"/>
                            </td>
                            <td>https://www.youtube.com/watch?v=${dto.videoLink}</td>
                            <td><a href="LessonController?key=lessonDetail&id=${dto.id}&name=${dto.name}&description=${dto.description}&link=${dto.videoLink}">
                                    Detail</a></td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
