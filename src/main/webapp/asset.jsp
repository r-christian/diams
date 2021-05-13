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
    <title>Assets</title>
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
<body onload="loadRelatedData()">
<%@include file="header.jsp" %>
<%
    Asset selectedAsset = (Asset) request.getAttribute("selectedAsset");
    Boolean isEditMode = selectedAsset == null ? false : true;

%>
<form align="center" action="<%= isEditMode ? "update" : "save" %>" method="post">
    <ul class="form-style-1">
        <input type="hidden" id="id" name="id" value="<%= isEditMode ? selectedAsset.getAssetID() : -1%>">
        <input type="hidden" id="companiesHidden" name="companiesHidden" value="<%= isEditMode ? selectedAsset.companyName() : -1%>">
        <input type="hidden" id="assetTypesHidden" name="assetTypesHidden" value="<%= isEditMode ? selectedAsset.assetTypeName() : -1%>">
        <li>
            <label align="left">Company<span class="required">*</span></label>
            <select style="width: 100%; cursor: auto;" name="companies" id="companies" >
                <option selected disabled hidden><%= isEditMode ? selectedAsset.companyName() : "Choose Company" %></option>
            </select>
        </li>
        <li>
            <label align="left">Asset types<span class="required">*</span></label>
            <select style="width: 100%; cursor: auto;" name="assetTypes" id="assetTypes">
                <option selected disabled hidden><%= isEditMode ? selectedAsset.assetTypeName() : "Choose Assettype" %></option>
            </select>
        </li>
        <li>
            <label align="left">Name <span class="required">*</span></label>
            <input required oninvalid="this.setCustomValidity('Name cannot be empty.')"
                   onchange="this.setCustomValidity('')"  style="width: 100%; cursor: auto;" type="text" name="name" class="field" placeholder="Name" value="<%= isEditMode ? selectedAsset.getName() : ""%>" />
        </li>
        <li>
            <label align="left">Manufacturer <span class="required">*</span></label>
            <input required oninvalid="this.setCustomValidity('Manufacturer cannot be empty.')"
                   onchange="this.setCustomValidity('')"  style="width: 100%; cursor: auto;" type="text" name="manufacturer" class="field" placeholder="Manufacturer" value="<%= isEditMode ? selectedAsset.getManufacturer() : ""%>" />
        </li>
        <li>
            <label align="left">Quantity<span class="required">*</span></label>
            <input required style="width: 100%; cursor: auto;" type="number" name="quantity" class="field" placeholder="quantity" value="<%= isEditMode ? selectedAsset.getQuantity() : 1%>" />
        </li>
        <li>
            <input type="submit" value="<%= isEditMode ? "update" : "save" %>" />
        </li>
    </ul>
</form>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Assets</h2></caption>
        <tr>
            <th>ID</th>
            <th>Company</th>
            <th>Asset type</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Manufacturer</th>
            <th>Actions</th>
        </tr>
        <%
            List<Asset> assetList = (List<Asset>) request.getAttribute("assetList");
        %>
        <% for (Asset asset : assetList) { %>
        <tr>
            <td><%=asset.getAssetID()%></td>
            <td><%=asset.companyName()%></td>
            <td><%=asset.assetTypeName()%></td>
            <td><%=asset.getName()%></td>
            <td><%=asset.getQuantity()%></td>
            <td><%=asset.getManufacturer()%></td>
            <td class="row-actions">
                <a class="btn-action btn-edit" href="edit?id=<%=asset.getAssetID()%>">Edit</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a class="btn-action btn-delete" href="delete?id=<%=asset.getAssetID()%>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
<script>
    var actionsContainer = document.getElementsByClassName("row-actions");
    var btnEdit = actionsContainer[0];
    var btnDelete = actionsContainer[1];

    function loadRelatedData() {
        getCompanies();
        getAssetTypes();
    }

    function getCompanies() {
        fetch('http://localhost:8080/GetCompanies/')
            .then(response => response.json())
            .then(data => {
                var select = document.getElementById('companies');
                for (var i = 0; i < data.length; i++) {
                    select.innerHTML = select.innerHTML +
                        '<option value="' + data[i] + '">' + data[i] + '</option>';
                }
            });
    }

    function getAssetTypes() {
        fetch('http://localhost:8080/GetAssetTypes/')
            .then(response => response.json())
            .then(data => {
                var select = document.getElementById('assetTypes');
                for (var i = 0; i < data.length; i++) {
                    select.innerHTML = select.innerHTML +
                        '<option value="' + data[i] + '">' + data[i] + '</option>';
                }
            });
    }
</script>
</body>
</html>