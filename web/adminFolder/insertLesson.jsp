<%-- 
    Document   : insertLesson
    Created on : Nov 5, 2019, 3:48:07 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="admin/css/simple-sidebar.css" rel="stylesheet">
        <title>Course Detail</title>
    </head>
    <script>
        function loadVideo() {
            var link = document.getElementById("link").value;
            var index = link.indexOf("v=");
            var newIndex = index + 2;
            var temp = link.substring(newIndex);
            var newLink = "https://www.youtube.com/embed/" + temp;
            if (link) {
                document.getElementById("iframe").src = newLink;
            }
        }
    </script>
    <body style="background-color: #F8F8F8">
        <!--https://www.youtube.com/embed/tgbNymZ7vqY-->
        <div class="input_box">
            <div class="center_boxs_lesson">
                <form action="LessonController" >
                    <iframe id="iframe" class="iframe" src="">
                    </iframe>
                    <div class="left_iframe">
                        <h2 style="color: darkgreen">INSERT LESSON</h2>
                        <table style="text-align: left">
                            <tr>
                                <td style="padding-top: 10px">Video:</td>
                                <td><input type="text" id="link" class="insert_box_lesson" onchange="loadVideo()" name="txtLessonLink"  required="true"/></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px">Lesson:</td>
                                <td style="width: 80%"><input type="text" class="insert_box_lesson" name="txtLessonName"  required="true"/></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px">Description:</td>
                                <td><input type="text" class="insert_box_lesson" name="txtLessonDescription" required="true"/></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px">Course:</td>
                                <td> <select class="insert_box_lesson" name="txtCourseId">
                                        <c:set var="listCourse" value="${requestScope.LISTCOURSE}"/>
                                        <c:set var="selected" value="${sessionScope.COURSEID}"/>
                                        <c:forEach var="dto" items="${listCourse}">
                                            <c:if test="${dto.id == selected}">
                                                <option value="${dto.id}" selected="">${dto.name}</option>
                                            </c:if>
                                            <c:if test="${dto.id != selected}">
                                                <option value="${dto.id}">${dto.name}</option>
                                            </c:if>
                                        </c:forEach>

                                    </select></td>
                            </tr>
                        </table>
                        <input type="hidden" name="key" value="insertCourse"/>
                        <button type="submit" class="btn-info btn_insert">Insert</button>
                    </div>

                </form>
            </div>
        </div>
    </body>
</html>
