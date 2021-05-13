<%--
  Created by IntelliJ IDEA.
  User: rwynn
  Date: 3/15/2021
  Time: 8:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A layout example with a side menu that hides on mobile, just like the Pure website.">
    <style>
        /* Add a black background color to the top navigation */
        .topnav {
            background-color: #333;
            overflow: hidden;
        }

        /* Style the links inside the navigation bar */
        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        /* Change the color of links on hover */
        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        /* Add a color to the active/current link */
        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }

        .nav-home {
            background-color: orange;
        }
    </style>
</head>
<body>
<div class="topnav" id="topnav">
    <a class="btn active" href="http://localhost:8080/">DLMS</a>
    <a class="btn" href="http://localhost:8080/company/">Companies</a>
    <a class="btn" href="http://localhost:8080/assetType/">Asset Types</a>
    <a class="btn" href="http://localhost:8080/asset/">Assets</a>
    <a class="btn" href="http://localhost:8080/user/">Users</a>
    <a class="btn" href="/logout.jsp">Log out</a>
</div>
<script>
    // Get the container element
    var btnContainer = document.getElementById("topnav");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("btn");

    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0; i < btns.length; i++) {
        btns[i].addEventListener("click", function() {
            var current = document.getElementsByClassName("active");
            current[0].className = current[0].className.replace(" active", "");
            this.className += " active";
        });
    }
</script>
</body>
</html>
