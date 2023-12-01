<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: st
  Date: 20.11.2023
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Static content  -->
    <title>Title</title>
    <!-- Scriptlet -->
    <!-- Dynamic content -->

    <h2><%System.out.println("Hello " +  new Date());
    String param = request.getParameter("test");
        System.out.println(param);
        out.print("Server date: " + new Date());

    %></h2>
</head>
<body>

</body>
</html>