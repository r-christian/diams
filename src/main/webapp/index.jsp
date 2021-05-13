<%@ page import="com.christian.diams.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>DIAMS - Home</title>
</head>
<body>
    <h2>Hello</h2>
    <%
        User user = (User)session.getAttribute("user");
        if(user == null){
            response.sendRedirect("http://localhost:8080/login/");
        }else{
            response.sendRedirect("http://localhost:8080/company/");
        }
    %>
</body>
</html>