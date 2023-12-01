<%@ page import="main.org.example.model.Employee" %>
<%@ page import="java.util.Set" %>
<%@ page import="main.org.example.util.ServletUtils" %>
<%@ page import="main.org.example.model.User" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: sharlan_a
  Date: 20.11.2023
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="css/table_style.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<%! public boolean hasRole(HttpServletRequest request , String... roleNames){
    User user = ServletUtils.getUserFromSession(request);
    return Arrays.asList(roleNames).contains(user.getRole().getName());
} %>

<% Set<Employee> empls = (Set<Employee>) request.getAttribute("empls");

    if(empls.isEmpty()) {%>
<h1>No Employees found!</h1>
<%} else {%>
<table class="table_dark">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>LAST NAME</th>
        <th>AGE</th>
        <th>OFFICE</th>
        <th>PASSPORT</th>
        <th>UPDATED</th>
        <th>CREATED</th>
        <%=hasRole(request, "Admin", "Manager")? "<th>Update</th>" : "" %>
        <%=hasRole(request, "Admin")? "<th>Delete</th>" : "" %>
    </tr>

    <% for(Employee empl : empls) { %>
    <tr>
        <td><%=empl.getId()%></td>
        <td><%=empl.getName()%></td>
        <td><%=empl.getLastName()%></td>
        <td><%=empl.getAge()%></td>
        <td><%=empl.getOffice().getTitle()%></td>
        <td><%=empl.getPassport().getIndID()%></td>
        <td><%=empl.getUpdatedTs()%></td>
        <td><%=empl.getCreatedTs()%></td>
        <% if(hasRole(request, "Admin", "Manager")){ %>
        <td><a href=<%="employees?action=U&id="+empl.getId()%>> UPDATE </a> </td>
        <%}%>
        <% if(hasRole(request, "Admin")){ %>
        <td><a href=<%="employees?action=D&id="+empl.getId()%>> DELETE </a> </td>
        <%}%>
    </tr>
    <% } %>
</table>
<br><a href=<%="employees?action=C"%>> CREATE </a>
<% } %>

</body>
</html>
