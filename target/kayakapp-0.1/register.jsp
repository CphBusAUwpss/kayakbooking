<%-- 
    Document   : register
    Created on : Nov 24, 2016, 10:29:58 PM
    Author     : Thomas Hartmann - tha@cphbusiness.dk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>register Page</title>
    </head>
    <body>
        <h1>Register here</h1>
        <form action="UserControl" method="POST">
            <input type="text" name="username" placeholder="Enter your username" />
            <input type="text" name="email" placeholder="Enter your email" />
            <input type="password" name="password1" placeholder="Enter a password > 8 chars" />
            <input type="password" name="password2" placeholder="Reenter your password" />
            <input type="hidden" name="origin" value="register" />
            <input type="submit" value="Register now" />
        </form>
    </body>
</html>
