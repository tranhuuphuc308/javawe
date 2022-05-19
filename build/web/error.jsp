<%-- 
    Document   : error
    Created on : May 17, 2022, 10:28:22 AM
    Author     : ROG STRIX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>OMG!!</h1>
        <h1>Error: <%= session.getAttribute("ERROR_MESSAGE")  %></h1>
    </body>
</html>
