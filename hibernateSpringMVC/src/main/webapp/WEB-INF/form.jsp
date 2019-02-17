<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New User</title>
</head>
<body>
<div align="center">
    <h1>New User</h1>
    <%--<form:form action="save" method="post" modelAttribute="command" >--%>
    <form:form action="save" method="post">
        <%--<script>console.log("${command}")</script>--%>
        <table>
            <form:hidden path="id"/>
            <%--<tr>
                <td>ID : </td>
                <td><form:input path="id" /></td>
            </tr>--%>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Username:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" type="password"/></td>
            </tr>
            <tr>
                <td>Mobile No:</td>
                <td><form:input path="mobileNo" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>