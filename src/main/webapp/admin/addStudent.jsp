<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Group" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 19/04/24
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
  GroupRepo groupRepo = new GroupRepo();
  List<Group> groups = groupRepo.findAll();

%>
<div class="row mt-4">
  <div class="col-4 offset-4">
    <div class="card p-2">
      <h1>Add Student</h1>
      <form method="post" action="/student/post">
        <input name="firstName" class="form-control my-3" type="text" placeholder="First name" required>
        <input name="lastName" class="form-control my-3" type="text" placeholder="Last name" required>
        <select name="groupId" class="form-control my-3">
          <option value="" selected disabled>Select Group</option>
          <% for (Group group : groups) { %>
          <option value="<%=group.getId() %>"><%=group.getName()%>
          </option>
          <%}%>
        </select>
        <div class="text-center">
          <button class="btn btn-dark w-100">Save</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
