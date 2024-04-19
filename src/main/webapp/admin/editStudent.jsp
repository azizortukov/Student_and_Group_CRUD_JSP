<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Group" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_user_crud_git.repo.StudentRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 19/04/24
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Add Student</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    StudentRepo studentRepo = new StudentRepo();
    GroupRepo groupRepo = new GroupRepo();
    List<Group> groups = groupRepo.findAll();
    Student student = studentRepo.findById(Integer.parseInt(request.getParameter("id")));
%>
<div class="row mt-4">
    <div class="col-4 offset-4">
        <div class="card p-2">
            <h1>Edit Student</h1>
            <form method="post" action="/student/put">
                <input name="id" type="hidden" value="<%=student.getId()%>">
                <input name="firstName" value="<%=student.getFirstName()%>" class="form-control my-3" type="text" placeholder="First name" required>
                <input name="lastName" value="<%=student.getLastName()%>" class="form-control my-3" type="text" placeholder="Last name" required>
                <select name="groupId" class="form-control my-3">
                    <% for (Group group : groups) { %>
                    <option value="<%=group.getId() %>"
                            <%= student.getGroup().getId().equals(group.getId())? "selected":""%>>
                        <%=group.getName()%></option>
                    <%}%>
                </select>
                <div class="text-center">
                    <button class="btn btn-dark w-100">Update</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
