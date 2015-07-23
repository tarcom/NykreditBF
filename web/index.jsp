<%@ page import="dk.skov.nykredit.bf.DBHandler" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: aogj
  Date: 09-07-15
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>

<%
    //DBHandler dbHandler = new DBHandler();
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BF app</title>


</head>
<body>
<h2>BF app</h2>

<table>
    <tr>
        <td>
            <jsp:include page="playersDiv.jsp"/>
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <jsp:include page="newGameDiv.jsp"/>
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <jsp:include page="topPlayerDiv.jsp"/>
        </td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>
            <jsp:include page="fightsDiv.jsp"/>
        </td>
    </tr>
</table>


<jsp:include page="countDownDiv.jsp"/>

<br><br>
Admin page <a href="http://localhost/phpmyadmin/" target="_blank">here</a>


</body>
</html>