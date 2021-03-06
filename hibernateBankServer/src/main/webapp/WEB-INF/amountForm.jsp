<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Money</title>
    <script  type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script  type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div align="center">
    <h1>Add Money in Wallet</h1>
    <%--<form action="saveMoneyWallet/id=${id}" method="post" >
        <div class="form-group">
            <label for="amount">Amount: </label>
            <input type="text" id="amount" name="amount" placeholder="Amount">
        </div>
        <div class="form-group">
            <label for="accountNo">Account No: </label>
            <input type="text" id="accountNo" name="amount" placeholder="Account No">
        </div>
        <div >
            <button type="submit" class ="btn btn-primary">Submit</button>
        </div>
    </form>--%>
    <form action= "commitTransaction" method="post" id ="amountForm">
        <div>
            <input type="hidden" id="orderId" name="orderId" value="${orderId}">
        </div>
        <div>
            <input type="hidden" id="userId" name="userId" value="${userId}">
        </div>
        <div>
            <label for="amount">Amount:</label>
            <input type="text" id="amount" name="amount" placeholder="Amount">
        </div>
        <div>
            <label for="accountNo">Account No</label>
            <input type="text" id="accountNo" name="accountNo">
        </div>
        <div>
            <label >Enter OTP </label>
            <input type="text" id="otp" name="otp">
           <%-- <button class ="btn btn-warning btn-sm" id="generateOtp">Generate OTP</button>--%>
        </div>
        <div >
            <input type="submit" value="Submit" id="submitForm">
        </div>
    </form>
</div>
<script >
    /*$("#generateOtp").click(function (e) {
        e.preventDefault();
        var amountInfo = {
            "amount": $("#amount").val(),
            accountNo: $("#accountNo").val(),

        }
        console.log(amountInfo);
        $.ajax({
            url: "${pageContext.request.contextPath}/saveOtp",
            type: 'POST',
            contentType:'application/json',
            dataType:'text',
            //timeout:600000,
            //data: JSON.stringify(amountInfo),
            data: "data" + amountInfo,
            success:function (dataFromServer) {
                alert(dataFromServer);
            },
            error:function(dataFromServer){
                console.log('Error', dataFromServer);
            }
        });
    });*/
    $("#submitForm").click(function submitForm() {
        $('form[id="amountForm"]').validate({
            /*focusCleanup:true,*/
            errorClass: "error",
            rules:{
                amount:"required",
                accountNo:"required"
            },
            messages:{
                amount: "Enter amount",
                accountNo:"Enter account no"
            },
            submitHandler:function (form) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/verifyOtp",
                    type: 'GET',
                    timeout:600000,
                    data: {"orderId": "${orderId}"} /*data to be sent to server*/,
                    success:function (data) {
                        if(data === $("#otp").val()) {
                            form.submit();
                            alert(data);
                        }
                    }
                });
            }
        });
    });

</script>
</body>

</html>