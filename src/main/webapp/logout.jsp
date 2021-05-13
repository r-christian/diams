<%--
  Created by IntelliJ IDEA.
  User: rwynn
  Date: 4/17/2021
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log out</title>
</head>
<body>
    <%
        session.invalidate();
        response.sendRedirect("http://localhost:8080/login/");
    %>
</body>
</html>
