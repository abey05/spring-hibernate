<%@ page contentType = "text/html; charset = UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <head>
      <meta charset="utf-8">
      <title>Hello </title>
      <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
   </head>
   <body>
   <div class ="container">
      <div class="row">
         <div class ="span8 offset2">
            <div class="float-sm-right">
               <div class ="pb-2 mt-2 mr-2 row">
                  <div class="pb-2 mt-2 mb-2 mr-2">
                     <form action="/new">
                        <input type="submit" value="Add New User" class="btn btn-primary btn-sm">
                     </form>
                     <%--<a href="new"> Add New User </a>--%>
                  </div>
                  <div class="pb-2 mt-2 mb-2 mr-2">
                     <form action="/logout" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value = "${_csrf.token}">
                        <input type="submit" value="Logout" class="btn btn-primary btn-sm">
                     </form>
                  </div>
               </div>
            </div>
            <div class ="pb-2 mt-4 mb-2 mr-2 ">
               <h3>User List</h3>
            </div>
            <table class ="table table-bordered table-striped">
              <thread>
                 <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Wallet</th>
                    <th>Mobile No</th>
                    <th>Created At</th>
                    <th>Updated At</th>
                    <th>Edit</th>
                    <th>Add Money</th>
                    <th>Passbook</th>
                    <th>&nbsp;</th>
                 </tr>
              </thread>
              <tbody>
              <c:forEach var="user" items="${userList}" varStatus="status">
                 <tr>
                    <td>${status.index + 1}</td>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.username}</td>
                    <td>${user.walletBalance}</></td>
                    <td>${user.mobileNo}</td>
                    <td>${user.createdAt}</td>
                    <td>${user.updatedAt}</td>
                    <td><a href="edit/id=${user.id}">Edit</a></td>
                    <td><a href="addMoney/id=${user.id}">Add</a></td>
                    <td><a href="showPassbook/id=${user.id}">Txn Details</a></td>
                    <td>
                          <%--<a href="delete?id=${user.id}">Delete</a>--%>
                       <form action="/delete/id=${user.id}" >
                          <input type = "submit" class ="btn btn-danger btn-sm" value="Delete">
                       </form>
                    </td>
                 </tr>
              </c:forEach>
              </tbody>
            </table>
            <%--<a href="<c:url value="j_spring_security_logout"/>">Logout</a>--%>
         </div>
      </div>
   </div>
   </body>
</html>