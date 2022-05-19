<%-- 
    Document   : admin
    Created on : May 17, 2022, 10:48:45 AM
    Author     : ROG STRIX
--%>

<%@page import="java.util.List"%>
<%@page import="sample.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            //phan quyen 
            //    chưa đăng nhập          khác role ad
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("Login.html");
                return;
            }
            //-------------------------------------------------------------
            //giu lai gia tri search
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
            //-------------------------------------------------------------                                             
        %>
        <h1>Hello Admin: <%=loginUser.getFullName()%> </h1>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"
        </form>
        <form action="MainController">
            Search <input type="text" name="search" value="<%=search%>"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <%
            //xu ly kp tra ve
            List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>USER ID</th>
                    <th>FULL NAME</th>
                    <th>ROLE ID</th>
                    <th>PASSWORD</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserDTO user : list) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <%= user.getUserID()%>                        
                    </td>
                    <td>                       
                        <input type="text" name="fullName" value="<%= user.getFullName() %>"/>
                    </td>
                    <td>                        
                        <input type="text" name="roleID" value="<%= user.getRoleID()%>"/>
                    </td>
                    <td><%= user.getPassword()%></td>
                    <td>
                        <a href="MainController?action=Delete&userID=<%= user.getUserID()%>&search=<%= search%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="userID" value="<%= user.getUserID() %>"/>
                        <input type="hidden" name="search" value="<%= search %>"/>
                    </td>
                </tr>
            </form>
                              
                <%
                    }
                %>

            </tbody>
        </table>
        <%
            }
            String error_mesage = (String) request.getAttribute("ERROR_MESSAGE");
            if (error_mesage == null) {
                error_mesage = "";
            }
        %>

        <h1><%= error_mesage %></h1>

        <%
            }
        %>
    </body>
</html>
