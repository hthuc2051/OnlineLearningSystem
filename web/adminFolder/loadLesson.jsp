<%-- 
    Document   : loadCourse
    Created on : Nov 4, 2019, 12:50:29 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>  
    <script>
        function submitForm(element) {
            var row = "row" + element;
            document.getElementById(row).submit();
        }
    </script>
    <body>
        <c:set var="listLesson" value="${requestScope.LISTLESSON}"/>
        <c:if test="${empty listLesson}">
            <H1 style="text-align: center; margin-top: 100px; color: darkolivegreen"> There are no lessons</H1>
            </c:if>
            <c:if test="${not empty listLesson}">
            <table align="center" class="table table-hover" style="margin-top: 10px">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>Lesson Name</th>
                        <th>Description</th>
                        <th>Video Link</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${listLesson}" varStatus="counter">
                    <form action="#" id="row${counter.count}">
                        <tr onclick="submitForm(${counter.count})">
                            <td>${counter.count}</td>
                            <td>${dto.name}</td>
                            <td>${dto.description}
                                <input type="hidden" value="${dto.id}" name="txtId"/>
                            </td>
                            <td>${dto.videoLink}</td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
