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
    <body onload="showVideo('${requestScope.LESSON.videoLink}')" style="background-color: #F8F8F8">
        <!--https://www.youtube.com/embed/tgbNymZ7vqY-->
        <div class="input_box">
            <div class="center_boxs_lesson">
                <form action="lessonController" >
                    <c:set var="lesson" value="${requestScope.LESSON}"/>
                    <iframe id="iframe" class="iframe" src="">
                    </iframe>
                    <div class="left_iframe">
                        <h2 style="color: darkblue">LESSON DETAIL</h2>
                        <table style="text-align: left">
                            <tr>
                                <td style="padding-top: 10px">Lesson:</td>
                                <td style="width: 80%"><input type="text" value="${lesson.name}" class="insert_box_lesson" name="txtLessonName"  required="true"/></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px">Description:</td>
                                <td><input type="text" class="insert_box_lesson" value="${lesson.description}" name="txtLessonDescription" required="true"/></td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px">Course:</td>
                                <td> <select class="insert_box_lesson" name="txtCourseId">
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

                                    </select></td>
                            <tr>
                                <td style="padding-top: 10px">Video:</td>
                                <td><input type="text" id="link" class="insert_box_lesson" value="${lesson.videoLink}" onchange="loadVideo()" name="txtLessonLink"  required="true"/></td>
                            </tr>
                            </tr>
                        </table>
                        <input type="hidden" name="key" value="insertCourse"/>
                        <button type="submit" class="btn-info btn_insert">Update</button>
                        <button type="submit" onclick="setDelete()" class="btn-danger btn_delete">Delete</button> 
                    </div>

                    <input type="hidden" name="key" value="updateLesson" id="key"/>
                    <input type="hidden" name="lessonId" value="${lesson.id}"/>

                </form>
            </div>
        </div>
    </body>
</html>
