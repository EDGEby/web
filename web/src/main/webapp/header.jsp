<%@ page import="static main.org.example.util.ServletUtils.*" %><%--
  Created by IntelliJ IDEA.
  User: sharlan_a
  Date: 27.11.2023
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="css/header_style.css">
<div class="header">
    <div class="menu">
        <svg class="line-top" width="750" height="15" viewbox="0,0 1000,20">
            <line
                    class="line-dash"
                    x1="0"
                    y1="15"
                    x2="1000"
                    y2="15"
                    style="stroke: orange; stroke-width:2; stroke-linecap:round; stroke-dasharray: 180,1200; stroke-dashoffset: -35;"
            />
        </svg>
        <ul>
            <li>Home</li>
            <li>Gallery</li>
            <li>Contact</li>
            <li>About</li>
            <li><%=isUserInSession(request)? "Hello ," + getUserFromSession(request).getName() + " <a href='logout'>Logout</a>"
                    : "Hello Stranger, please <a href='login'>Login</a>"%></li>
        </ul>
        <svg class="line-bottom" width="750" height="30" viewbox="0,0 1000,40">
            <polygon class="lb" points="35,53 115,53 125,43 135,53 215,53" />
            <polygon class="lb" points="285,53 365,53 375,43 385,53 465,53" />

            <polygon class="lb" points="535,53 615,53 625,43 635,53 715,53" />

            <polygon class="lb" points="785,53 865,53 875,43 885,53 965,53" />
        </svg>
    </div>
</div>
<script src="js/header.js"></script>