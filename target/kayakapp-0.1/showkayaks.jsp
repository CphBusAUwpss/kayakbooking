<%-- 
    Document   : showkayaks
    Created on : Nov 11, 2016, 12:00:29 AM
    Author     : Thomas Hartmann - tha@cphbusiness.dk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, model.DataFacade, model.entity.Kayak, model.entity.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>See all kayaks</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>

        <% if (request.getAttribute("user") == null) { %>
        Du skal først logge ind <a href="login.jsp">Gå til login siden</a>
        <% } else {
            User user = (User) request.getAttribute("user");
            %>

        <%@include file="navbar.jsp"%>

        <h1>Available kayaks:</h1>
        <table class="table table-striped">
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Model</th>
                <th>Description</th>
                <th>Color</th>
                <th>Length</th>
                <th>Date</th>
                <th>Book now</th>
            </tr>   
            <%            List<Kayak> kayaks = (List<Kayak>) request.getAttribute("kayaks");

                for (Kayak kayak : kayaks) {
            %>
            <form action="BookKayak" method="POST"> 
                <input type="hidden" name="kayakid" value="<%= kayak.getId()%>">
                <input type="hidden" name="userid" value="<%= user.getId()%>">
                <tr>
                    <td><img src="ImageViewer?id=<%= kayak.getId()%>"></td>
                    <td><%= kayak.getName()%></td>
                    <td><%= kayak.getModel()%></td>
                    <td><%= kayak.getDescription()%></td>
                    <td><%= kayak.getColor()%></td>
                    <td><%= kayak.getLength()%></td>
                    <td><input type="date" name="date"></td>
                    <td><input type="submit" name="submit" value="Book kayak"></td>
                </tr>
            </form>
            <%
                    }
                }
            %>

        </table>
    </body>
</html>
