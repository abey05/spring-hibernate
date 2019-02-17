<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div align="center">
    <h2>Login</h2>
    <form name='f' action="login" method='POST'>
        <table>
            <tr>
                <td>User:</td>
                <td><input class = "form-control" type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input class="form-control" type='password' name='password' /></td>
            </tr>
            <tr >
                <td><input name="submit" type="submit" value="submit" class ="btn btn-primary btn-sm"/></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
</div>
</body>
</html>