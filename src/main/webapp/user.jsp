<%--
  Created by IntelliJ IDEA.
  User: rwynn
  Date: 3/15/2021
  Time: 10:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.christian.diams.model.User"%>
<%@ page import="java.util.List" %>
<%@ page import="antlr.collections.Enumerator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.christian.diams.model.AssetType" %>
<%@ page import="com.christian.diams.model.Asset" %>
<html>
<head>
    <title>Users</title>
    <style type="text/css">
        .form-style-1 {
            margin:10px auto;
            max-width: 400px;
            padding: 20px 12px 10px 20px;
            font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
        }
        .form-style-1 li {
            padding: 0;
            display: block;
            list-style: none;
            margin: 10px 0 0 0;
        }
        .form-style-1 label{
            margin:0 0 3px 0;
            padding:0px;
            display:block;
            font-weight: bold;
        }
        .form-style-1 input[type=text],
        .form-style-1 input[type=date],
        .form-style-1 input[type=datetime],
        .form-style-1 input[type=number],
        .form-style-1 input[type=password],
        .form-style-1 input[type=search],
        .form-style-1 input[type=time],
        .form-style-1 input[type=url],
        .form-style-1 input[type=email],
        textarea,
        select{
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            border:1px solid #BEBEBE;
            padding: 7px;
            margin:0px;
            -webkit-transition: all 0.30s ease-in-out;
            -moz-transition: all 0.30s ease-in-out;
            -ms-transition: all 0.30s ease-in-out;
            -o-transition: all 0.30s ease-in-out;
            outline: none;
        }
        .form-style-1 input[type=text]:focus,
        .form-style-1 input[type=date]:focus,
        .form-style-1 input[type=password]:focus,
        .form-style-1 input[type=datetime]:focus,
        .form-style-1 input[type=number]:focus,
        .form-style-1 input[type=search]:focus,
        .form-style-1 input[type=time]:focus,
        .form-style-1 input[type=url]:focus,
        .form-style-1 input[type=email]:focus,
        .form-style-1 textarea:focus,
        .form-style-1 select:focus{
            -moz-box-shadow: 0 0 8px #88D5E9;
            -webkit-box-shadow: 0 0 8px #88D5E9;
            box-shadow: 0 0 8px #88D5E9;
            border: 1px solid #88D5E9;
        }
        .form-style-1 .field-divided{
            width: 49%;
        }

        .form-style-1 .field-long{
            width: 100%;
        }
        .form-style-1 .field-select{
            width: 100%;
        }
        .form-style-1 .field-textarea{
            height: 100px;
        }
        .form-style-1 input[type=submit], .form-style-1 input[type=button]{
            background: #4B99AD;
            padding: 8px 15px 8px 15px;
            border: none;
            color: #fff;
        }
        .form-style-1 input[type=submit]:hover, .form-style-1 input[type=button]:hover{
            background: #4691A4;
            box-shadow:none;
            -moz-box-shadow:none;
            -webkit-box-shadow:none;
        }
        .form-style-1 .required{
            color:red;
        }

        .btn-action{
            background: lightblue;
            padding: 5px 15px 5px 10px;
            border: none;
            color: #000;
            display: inline-block;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<%
    User selectedUser = (User) request.getAttribute("selectedUser");
    Boolean isEditMode = selectedUser == null ? false : true;

%>
<form align="center" action="<%= isEditMode ? "update" : "save" %>" method="post">
    <ul class="form-style-1">
        <input type="hidden" id="id" name="id" value="<%= isEditMode ? selectedUser.getUserID() : -1%>">
        <li>
            <label align="left">Full Name <span class="required">*</span></label>
            <input required oninvalid="this.setCustomValidity('Firstname cannot be empty.')"
                   onchange="this.setCustomValidity('')" type="text" name="firstName" class="field-divided" placeholder="Firstname" value="<%= isEditMode ? selectedUser.getFirstName() : ""%>" />
            <input required oninvalid="this.setCustomValidity('Last name cannot be empty.')"
                   onchange="this.setCustomValidity('')" type="text" name="lastName" class="field-divided" placeholder="Last name" value="<%= isEditMode ? selectedUser.getLastName() : ""%>" />
        </li>
        <li>
            <label align="left">Email: <span class="required">*</span></label>
            <input required oninvalid="this.setCustomValidity('Email cannot be empty.')"
                   onchange="this.setCustomValidity('')" style="width: 100%; cursor: auto;" type="text" name="email" class="field" placeholder="Email" value="<%= isEditMode ? selectedUser.getEmail() : ""%>" />
        </li>
        <li>
            <label align="left">Password: <span class="required">*</span></label>
            <input required oninvalid="this.setCustomValidity('Password cannot be empty.')"
                   onchange="this.setCustomValidity('')" style="width: 100%; cursor: auto;" type="password" name="password" class="field" placeholder="Password" value="<%= isEditMode ? selectedUser.getPassword() : ""%>" />
        </li>
        <li>
            <input type="submit" value="<%= isEditMode ? "update" : "save" %>" />
        </li>
    </ul>
</form>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Firstname</th>
            <th>Last name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Actions</th>
        </tr>
        <%
            List<User> userList = (List<User>) request.getAttribute("userList");
        %>
        <% for (User user : userList) { %>
        <tr>
            <td><%=user.getUserID()%></td>
            <td><%=user.getFirstName()%></td>
            <td><%=user.getLastName()%></td>
            <td><%=user.getEmail()%></td>
            <td>*******</td>
            <td class="row-actions">
                <a class="btn-action btn-edit" href="edit?id=<%=user.getUserID()%>">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn-action btn-delete" href="delete?id=<%=user.getUserID()%>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
<script>
    var actionsContainer = document.getElementsByClassName("row-actions");
    var btnEdit = actionsContainer[0];
    var btnDelete = actionsContainer[1];
</script>
</body>
</html>