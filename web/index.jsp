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
<!DOCTYPE html>
<html>
<head>
    <title>BF app</title>

</head>
<body>
<h2>BF app</h2>

<table width = 100% border="1" cellpadding="10">
    <tr>
        <td>
            <jsp:include page="newGameDiv.jsp"/>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="playersDiv.jsp"/>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="countDownDiv.jsp"/>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="topPlayerDiv.jsp"/>
        </td>
    </tr>
    <tr>
        <td>
            <jsp:include page="fightsDiv.jsp"/>
        </td>
    </tr>
</table>


<br><br>
Admin page <a href="http://localhost/phpmyadmin/" target="_blank">here</a><br>
Fork project at github <a href="https://github.com/tarcom/NykreditBF.git" target="_blank">here</a>


</body>
</html>