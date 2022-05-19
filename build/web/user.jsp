<%-- 
    Document   : user
    Created on : May 17, 2022, 10:58:00 AM
    Author     : ROG STRIX
--%>

<%@page import="sample.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
            if(loginUser!=null){
            
            %>
            <h1>Hello user: <%= loginUser.getFullName() %></h1>
        
        <%
            }
        %>
        
        
    </body>
</html>
