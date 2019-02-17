<%@ page contentType = "text/html; charset = UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Bank Database </title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div class ="container">
    <div class="row">
        <div class ="span8 offset2">
            <h1>User Account List</h1>
            <table class ="table table-bordered table-striped">
                <thread>
                    <tr>
                        <th>No</th>
                        <th>Acc Number</th>
                        <th>Name</th>
                        <th>Balance </th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Edit</th>
                        <th>&nbsp;</th>
                    </tr>
                </thread>
                <tbody>
                <c:forEach var="user" items="${userList}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${user.accountNo}</td>
                        <td>${user.name}</td>
                        <td>${user.balance}</td>
                        <td>${user.createdAt}</td>
                        <td>${user.updatedAt}</td>
                        <td><a href="edit/id=${user.accountNo}">Edit</a></td>
                    <%--<td><a href="deleteAccount/id=${user.accountNo}">Delete</a></td>--%>
                        <td>
                            <form action="deleteAccount/id=${user.accountNo}" method="post">
                                <input type = "submit" class ="btn btn-danger btn-sm" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <a href="new"> Add New User account </a>
        </div>
    </div>
</div>
</body>
</html>