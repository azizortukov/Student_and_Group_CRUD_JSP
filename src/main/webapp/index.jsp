<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Student" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.StudentRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.UserRepo" %>
<%@ page import="java.util.Optional" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.User" %>
<%@ page import="jakarta.persistence.TypedQuery" %>
<%@ page import="static uz.oasis.jsp_user_crud_git.repo.BaseRepo.em" %>
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
    String parameter = request.getParameter("search-name");
    if (parameter != null) {
        TypedQuery<Student> query = em.createQuery("select s from Student s where firstName like :search or lastName like :search", Student.class);
        query.setParameter("search", "%" + parameter + "%");
        students = query.getResultList();
    }
    Optional<User> currentUser = userRepo.getUserBySession(request.getSession());
    %>

<nav class="navbar bg-body-tertiary bg-light">
    <div class="container">
        <form class="d-flex" role="search" action="/" method="get">
            <input name="search-name" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success ml-4" type="submit">Search</button>
        </form>
        <form class="justify-content-end">
            <% if (currentUser.isPresent()) { %>
            <div class="dropdown">
                <a class="nav-link dropdown-toggle btn btn-outline-success" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%= currentUser.get().getUsername()%>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                    <a class="dropdown-item" href="/admin/student.jsp">Edit Student</a>
                    <a class="dropdown-item" href="/admin/group.jsp">Edit Group</a>
                    <a class="dropdown-item" href="/auth?userId=<%=currentUser.get().getId()%>">Log out</a>
                </div>
            </div>
            <% } else { %>
            <a class="btn btn-outline-success mr-2" type="button" href="/login.jsp">Login</a>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>