<%@ page contentType = "text/html; charset = UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div class = "container">
    <div class = "row m-3">
        <div class= "col">
            <table class = "table table-bordered table-striped">
                <tbody>
                     <tr>
                         <td>Name</td>
                         <td>${user.name}</td>
                     </tr>
                     <tr>
                         <td>Username</td>
                         <td>${user.username}</td>
                     </tr>
                    <tr>
                        <td>Mobile Number </td>
                        <td>${user.mobileNo}</td>
                    </tr>
                    <tr>
                        <td>Wallet</td>
                        <td>${user.walletBalance}</td>
                    </tr>
                </tbody>
            </table
            <%--<a href="edit/id=${user.id}">Edit</a>
            <a href="showPassbook/id=${user.id}">Txn Details</a>--%>
            <%--<a href="addMoney/id=${user.id}">Add Money</a>--%>
        </div>
        <div class="row">
            <div class="col m-1">
                <form action="edit/id=${user.id}" >
                    <input type = "submit" class ="btn btn-primary btn-sm" value="Edit">
                </form>
            </div>
            <div class="col m-1">
                <form action="showPassbook/id=${user.id}" >
                    <input type = "submit" class ="btn btn-primary btn-sm" value="Txn Details">
                </form>
            </div>
            <div class="col m-1">
                <form action="addMoney/id=${user.id}" >
                    <input type = "submit" class ="btn btn-primary btn-sm" value="Add Money">
                </form>
            </div>
            <div class="col m-1">
                <form action="pay/${user.id}" >
                    <input type = "submit" class ="btn btn-primary btn-sm" value="Pay">
                </form>
            </div>
            <div class="col m-1">
                <form action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value = "${_csrf.token}">
                    <input type="submit" value="Logout" class="btn btn-primary btn-sm">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>