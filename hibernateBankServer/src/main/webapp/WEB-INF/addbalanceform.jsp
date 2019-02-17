<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>bank</title>
</head>
<body>
<div align="center">
    <h1>Debit money</h1>
    <%--<form action="deduct/id=${id}/amount=${amount}" method="post">--%>
    <form action="transaction/id=${id}/amount=${amount}" method="post">
    <input type="text" name="accountNo" placeholder="Enter Account no to debit"/>
        <input type="submit" value="OK"/>
    </form>
</div>
</body>
</html>