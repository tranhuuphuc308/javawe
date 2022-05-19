<%-- 
    Document   : createUser
    Created on : May 19, 2022, 9:00:55 AM
    Author     : ROG STRIX
--%>

<%@page import="sample.daos.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Create user</h1>
        <%
            UserError error = (UserError) request.getAttribute("USER_ERROR");
            if(error ==null){
                error = new UserError();
            }
        %>
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" required=""/><%= error.getUserIDError() %></br>
            Full Name <input type="text" name="fullName" required=""/><%= error.getFullNameError()%></br>
            Role ID <input type="text" name="roleID" required=""/><%= error.getRoleIDError()%></br>
            Pass <input type="password" name="password" required=""/><%= error.getPasswordError()%></br>
            Confirm<input type="password" name="confirm" required=""/><%= error.getConfirmError()%></br>
            <input tytype="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/>
            <%= error.getMessageError()%>
        </form>
    </body>
</html>
