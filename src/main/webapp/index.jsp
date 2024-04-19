<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Student" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.StudentRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.UserRepo" %>
<%@ page import="java.util.Optional" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.User" %>
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
    UserRepo userRepo = new UserRepo();
    List<Student> students = studentRepo.findAll();
    Optional<User> currentUser = userRepo.getUserBySession(request.getSession());
    %>

<nav class="navbar bg-body-tertiary bg-light">
    <div class="container">
        <form class="d-flex" role="search" action="/search" method="post">
            <input name="name" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success ms-4" type="submit">Search</button>
        </form>
        <form class=" justify-content-end">
            <% if (currentUser.isPresent()) { %>
            <a class="nav-link dropdown-toggle btn btn-outline-success" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <%= currentUser.get().getUsername()%>
            </a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="/admin/student.jsp">Edit Student</a></li>
                <li><a class="dropdown-item" href="/admin/group.jsp">Edit Group</a></li>
                <li><a class="dropdown-item" href="/auth?userId=<%=currentUser.get().getId()%>">Log out</a></li>
            </ul>
            <% }else { %>
            <a class="btn btn-outline-success me-2" type="button" href="/login.jsp">Login</a>
            <% } %>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</html>