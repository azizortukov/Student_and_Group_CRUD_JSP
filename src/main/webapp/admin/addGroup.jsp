<%@ page import="uz.oasis.jsp_user_crud_git.repo.GroupRepo" %>
<%@ page import="uz.oasis.jsp_user_crud_git.entity.Group" %>
<%@ page import="java.util.List" %>
<%-- Tahrirlovchi IntelliJ IDEA. Foydalanuvchi: anas. Sanasi: 19/04/24. Vaqt: 17:47 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>Add Group</title>
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
            <h1>Add Group</h1>
            <form method="post" action="/group/post">
                <input name="name" class="form-control my-3" type="text" placeholder="Group name" required>
                <div class="text-center">
                    <button class="btn btn-dark w-100">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
