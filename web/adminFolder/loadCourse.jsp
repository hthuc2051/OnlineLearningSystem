<%-- 
    Document   : loadCourse
    Created on : Nov 4, 2019, 12:50:29 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Custom styles for this template -->
        <link href="admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="admin/css/simple-sidebar.css" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    </head>  
    <script>
        function submitForm(element) {
            var row = "row" + element;
            document.getElementById(row).submit();
        }
        
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
        <div id="header">
            <div class="row">
                <div class="col-8">
                    <form action="adminFolder/insertCourse.jsp">
                        <button type="submit" class="btn-primary active">Add New</button>
                    </form>
                </div>
                <div class="col-4">
                    <input type="text"  id="myInput" placeholder="enter a text" onkeyup="myFunction()"/>
                    <i class="fas fa-search"></i>
                </div>
            </div>

        </div>
        <c:set var="listCourse" value="${requestScope.LISTCOURSE}"/>
        <table align="center" class="table table-hover" style="margin-top: 10px" id="myTable">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Course Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${listCourse}" varStatus="counter">
                <form action="LessonController" id="row${counter.count}">
                    <tr onclick="submitForm(${counter.count})">
                        <td>${counter.count}</td>
                        <td>${dto.name}
                            <input type="hidden" value="${dto.id}" name="txtId"/>
                            <input type="hidden" value="getLessonsById" name="key"/>
                        </td>
                        <td>${dto.description}</td>
                        <td>
                            <a href="CourseController?key=courseDetail&courseId=${dto.id}&courseName=${dto.name}&courseDescription=${dto.description}">Detail</a>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
