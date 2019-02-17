<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Contact</title>
</head>
<body>
<div align="center">
    <h1>Edit Account</h1>
    <%--<form:form action="save" method="post" modelAttribute="command" >--%>
    <form:form action="updateAccount" method="post">
        <%--<script>console.log("${command}")</script>--%>
        <table>
            <form:hidden path="accountNo"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Balance:</td>
                <td><form:input path="balance" /></td>
            </tr>
            <form:hidden path="createdAt"/>
            <form:hidden path="updatedAt"/>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>