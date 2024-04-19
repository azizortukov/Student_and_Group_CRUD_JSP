<%@ page import="uz.oasis.jsp_user_crud_git.entity.Student" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.StudentRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 19/04/24
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student's Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    StudentRepo studentRepo = new StudentRepo();
    GroupRepo groupRepo = new GroupRepo();
    List<Student> students = studentRepo.findAll();

%>
<nav class="navbar bg-body-tertiary bg-light">
    <div class="container justify-content-end">
        <form class="">
            <a class="btn btn-outline-success me-4" type="button" href="/">Back</a>
            <a class="btn btn-outline-success mx-4" type="button" href="/admin/addStudent.jsp">Add Student</a>
        </form>
    </div>
</nav>

<div class="col-10 offset-1">

    <div class="p-4">
        <table class="table table-striped">
            <thead>
            <tr style="vertical-align: middle">
                <th>First Name</th>
                <th>Last Name</th>
                <th>Group Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (Student student : students) {%>
            <tr style="vertical-align: auto">
                <td><%= student.getFirstName()%>
                </td>
                <td><%= student.getLastName()%>
                </td>
                <td><%= groupRepo.getStudentGroup(student)%>
                </td>
                <td>
                    <a href="/admin/editStudent.jsp?id=<%=student.getId()%>" class="btn btn-success text-white">Edit</a>
                    <a href="/student/delete?id=<%= student.getId()%>"
                       class="btn btn-dark text-white">Delete</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
