<%@ page import="main.org.example.model.Office" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: sharlan_a
  Date: 22.11.2023
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Employee</title>
  <!--  <link rel="stylesheet" href="css/Style.css">-->
</head>

<body>

<form action="employees" method="post" > <!-- Submit will call doPost() method. All parameters will be in request body!  -->
    <div class="group">
        <input type="text" name="name"><span class="highlight"></span><span class="bar"></span>
        <label>Name</label>
    </div>
    <div class="group">
        <input type="text" name="last_name"><span class="highlight"></span><span class="bar"></span>
        <label>Last Name</label>
    </div>

    <div class="group">
        <input type="number" name="age"><span class="highlight"></span><span class="bar"></span>
        <label>Age</label>
    </div>

    <div class="group">
        <select name="office_id">
            <% for(Office office: (Set<Office>)request.getAttribute("offices")) { %>
            <option value='<%=office.getId()%>'><%=office.getId() + " - " + office.getTitle()%></option>
            <%}%>
        </select>
        <label>Office</label>
    </div>
    <br>
    <!-- Passport form -->
    <div class="group">
        <input type="text" name="personal_id"><span class="highlight"></span><span class="bar"></span>
        <label>Personal ID</label>
    </div>
    <div class="group">
        <input type="text" name="ind_id"><span class="highlight"></span><span class="bar"></span>
        <label>Individual ID</label>
    </div>

    <div class="group">
        <input type="date" name="exp_date"><span class="highlight"></span><span class="bar"></span>
        <label>Exp Date</label>
    </div>


    <button type="submit" class="button buttonBlue">Create
        <div class="ripples buttonRipples"><span class="ripplesCircle"></span></div>
    </button>
</form>
<footer><a href="http://www.polymer-project.org/" target="_blank"><img
        src="https://www.polymer-project.org/images/logos/p-logo.svg"></a>
    <p>You Gotta Love <a href="http://www.polymer-project.org/" target="_blank">Google</a></p>
</footer>
<script src="js/login.js"></script>

</body>
</html>
