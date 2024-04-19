<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Student" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.StudentRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.UserRepo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Student CRUD</title>
    <meta charset="utf-8">
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
    <div class="container">
        <form class="d-flex" role="search" action="/search" method="post">
            <input name="name" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success ms-4" type="submit">Search</button>
        </form>
        <form class=" justify-content-end" >
            <button class="btn btn-outline-success me-2" type="button">Login</button>
        </form>
    </div>
</nav>

<div class="col-10 offset-1">

    <div class="p-4">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Group Name</th>
            </tr>
            </thead>
            <tbody>
            <% for (Student student : students) {%>
            <tr style="vertical-align: middle">
                <td><%= student.getFirstName()%>
                </td>
                <td><%= student.getLastName()%>
                </td>
                <td><%= groupRepo.getStudentGroup(student)%>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>