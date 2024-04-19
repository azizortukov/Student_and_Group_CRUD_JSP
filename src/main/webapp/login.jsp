<%@ page import="uz.oasis.jsp_user_crud_git.entity.Student" %>
<%@ page import="uz.oasis.jsp_user_crud_git.servlet.AuthServlet" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 19/04/24
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>Login</title>
    <style>
        body {
            background-color: #f8f9fa; /* lighter background color */
        }

        .login-container {
            margin-top: 100px;
        }

        .login-form {
            max-width: 400px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        .login-form input[type="text"],
        .login-form input[type="password"] {
            margin-bottom: 20px;
        }

        .login-btn {
            background-color: limegreen;
            border-color: limegreen;
        }

        .login-btn:hover {
            background-color: limegreen;
            border-color: limegreen;
            opacity: 0.8;
        }
    </style>
</head>
<body>
<%
    String manyTimes = request.getParameter("multiple");
    if (manyTimes != null) { %>
<div id="notification" class="notification col-4 offset-4 mt-4 bg-danger text-white text-center">
    Email or password is wrong. Please, try again!
</div>
<% } %>
<div class="container login-container">
    <div class="login-form">
        <h2 class="text-center mb-4">Login</h2>
        <form method="post" action="/auth">
            <div class="form-group">
                <input name="username" type="text" class="form-control" placeholder="Username" required>
            </div>
            <div class="form-group">
                <input name="password" type="password" class="form-control" placeholder="Password" required>
            </div>
            <div class="form-group form-check">
                <input name="remember_me" type="checkbox" class="form-check-input" id="rememberMe">
                <label class="form-check-label" for="rememberMe">Remember me</label>
            </div>
            <button type="submit" class="btn btn-lg btn-block login-btn text-white">Login</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        showNotification();
    });

    function showNotification() {
        var notification = document.getElementById("notification");
        notification.style.display = "block";
        setTimeout(function () {
            notification.style.display = "none";
        }, 3000);
    }

</script>
</body>
</html>
