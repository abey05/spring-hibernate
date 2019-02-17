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
            <h3>Credit History</h3>
            <table class ="table table-bordered table-stripped table-condensed">
                <thread>
                    <tr>
                        <th>No</th>
                        <th>TxnID</th>
                        <th>Debit Account</th>
                        <th>Credit Amount</th>
                        <th>Date</th>
                        <%--<th>&nbsp;</th>--%>
                    </tr>
                </thread>
                <tbody>
                <c:forEach var="txnCredit" items="${txnListCredit}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${txnCredit.txnId}</td>
                        <td>${txnCredit.debitAccount}</td>
                        <td>${txnCredit.amount}</td>
                        <td>${txnCredit.timestamp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br/>
            <h3>Debit History</h3>
            <table class ="table table-bordered table-stripped table-condensed">
                <thread>
                    <tr>
                        <th>No</th>
                        <th>TxnID</th>
                        <th>Credit Wallet</th>
                        <th>Credit Amount</th>
                        <th>Date</th>
                        <%--<th>&nbsp;</th>--%>
                    </tr>
                </thread>
                <tbody>
                <c:forEach var="txnDebit" items="${txnListDebit}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${txnDebit.txnId}</td>
                        <td>${txnDebit.creditWallet}</td>
                        <td>${txnDebit.amount}</td>
                        <td>${txnDebit.timestamp}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="/"> Home </a>

        </div>
    </div>
</div>
</body>
</html>