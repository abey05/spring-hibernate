<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit User</title>
</head>
<body>
<div align="center">
    <h1>Edit user</h1>
    <%--<form:form action="save" method="post" modelAttribute="command" >--%>
    <form:form action="commit" method="post">
        <%--<script>console.log("${command}")</script>--%>
        <table>
            <form:hidden path="txnId"/>
            <tr>
                <td>Mobile No:</td>
                <td><form:input path="creditWallet" /></td>
            </tr>
            <form:hidden path="userId" value="${id}"/>
            <tr>
                <td>Amount:</td>
                <td><form:input path="amount" id="amount"/></td>
            </tr>
            <form:hidden path="type" value="debit"/>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Pay" id="submitForm"></td>
            </tr>
        </table>
    </form:form>
</div>
<script type="text/javascript">
    $("#submitForm").click(function (e) {
        e.preventDefault();
        $.ajax({
            url: "${pageContext.request.contextPath}/getWalletBalance",
            type: 'GET',
            timeout:600000,
            data: {"id": "${id}"} /*data to be sent to server*/,
            success:function (data) {
                if(parseInt(data) < parseInt($("#amount").val()))
                    alert("Not Enough balance");
                else
                    e.submit();
            }
        });
    });


</script>
</body>
</html>