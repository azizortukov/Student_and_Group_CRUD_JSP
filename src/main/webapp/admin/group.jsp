<%@ page import="uz.oasis.jsp_user_crud_git.entity.Group" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Group" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 19/04/24
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Group's Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    GroupRepo groupRepo = new GroupRepo();
    List<Group> groups = groupRepo.findAll();

%>

<nav class="navbar bg-body-tertiary bg-light">
    <div class="container justify-content-end">
        <form >
            <a class="btn btn-outline-success me-4" type="button" href="/index.jsp">Back</a>
            <a class="btn btn-outline-success mx-4" type="button" href="/admin/addGroup.jsp">Add Group</a>
            <a class="btn btn-outline-secondary mx-4" type="button" href="/admin/student.jsp">Students' CRUD</a>

        </form>
    </div>
</nav>

<div class="col-10 offset-1">

    <div class="p-4">
        <table class="table table-striped">
            <thead>
            <tr style="vertical-align: middle">
                <th>Group Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (Group group : groups) {%>
            <tr style="vertical-align: auto">

                <td><%= group.getName()%>
                </td>
                <td>
                    <a href="/admin/editGroup.jsp?id=<%=group.getId()%>" class="btn btn-success text-white">Edit</a>
                    <a href="/group/delete?id=<%= group.getId()%>"
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
