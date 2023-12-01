<%@ page import="java.util.Date" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: st
  Date: 20.11.2023
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>currencies</title>

</head>
<body>
    <h2>Currencies Date: <%= getCurrentServerDate()%></h2>
    <%!
        Date getCurrentServerDate(){
            return new Date();
        }
    %>

    <table border="1">
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Rate</th>
        </tr>
        <% Map<String, String> map = (Map<String, String>) request.getAttribute("map");
            int number = 0;
            for (Map.Entry<String, String> entry : map.entrySet()){
        %>
        <tr>
            <td><%= ++ number %></td>
            <td><%= entry.getKey() %></td>
            <td><%= entry.getValue() %></td>

        </tr>

        <%
            }
        %>

    </table>

</body>
</html>
