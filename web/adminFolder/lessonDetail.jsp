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
            if (link) {
                document.getElementById("iframe").src = link;
            }
        }

        function showVideo(link) {
            if (link) {
                document.getElementById("iframe").src = link;
            }
        }

        function setDelete() {
            if (confirm("Do you want to delete this lesson?")) {
                document.getElementById("key").value = "deleteLesson";
                document.getElementById("form").submit();
            } else {

            }
        }
    </script>
    <body onload="showVideo('${requestScope.LESSON.videoLink}')">
        <!--https://www.youtube.com/embed/tgbNymZ7vqY-->
        <div>
            <form action="lessonController" >
                <c:set var="lesson" value="${requestScope.LESSON}"/>
                <iframe id="iframe" width="420" height="345" src="">
                </iframe></br>
                Lesson: <input type="text" class="" value="${lesson.name}" name="txtLessonName" placeholder="enter a lesson name" required="true"/><br/>
                Description: <input type="text" value="${lesson.description}" class="" name="txtLessonDescription" placeholder="enter description" required="true"/><br/>
                Course:
                <select name="txtCourseId">
                    <c:set var="listCourse" value="${requestScope.LISTCOURSE}"/>
                    <c:set var="selected" value="${sessionScope.COURSESELECTED}"/>
                    <c:forEach var="dto" items="${listCourse}">
                        <c:if test="${dto.id == selected}">
                            <option value="${dto.id}" selected="">${dto.name}</option>
                        </c:if>
                        <c:if test="${dto.id != selected}">
                            <option value="${dto.id}">${dto.name}</option>
                        </c:if>
                    </c:forEach>

                </select><br/>
                Video: <input type="text" id="link" value="${lesson.videoLink}" class="" onchange="loadVideo()" name="txtLessonLink" placeholder="enter a lesson name" required="true"/><br/>
                <input type="hidden" name="key" value="updateLesson" id="key"/>
                <input type="hidden" name="lessonId" value="${lesson.id}"/>
                <button type="submit" class="btn-info btn_insert">Update</button>
                <button type="submit" onclick="setDelete()" class="btn-danger btn_insert">Delete</button>
            </form>
        </div>
    </body>
</html>
