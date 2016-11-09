<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.Util" %>
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
    <title>BF app 2.0</title>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>

    <%--<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />--%>
    <%--<script type="text/javascript" src="tablecloth/tablecloth.js"></script>--%>

    <link href="alsk.css" rel="stylesheet" type="text/css" media="screen"/>

    <style>


    </style>

</head>
<body>
<h2>BF app 2.0</h2>

<%
    Util.updateModel(request);
%>
<div align="center">

    <table cellpadding="10">
        <tr align="center">
            <td valign="top">

                <table border="1" cellpadding="10">
                    <tr align="center">
                        <td align="center" width="400px" rowspan="2">
                            <jsp:include page="playersDiv.jsp"/>
                        </td>
                        <td align="center">
                            <jsp:include page="newGameDiv.jsp"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">
                            <jsp:include page="countDownDiv.jsp"/>
                        </td>
                    </tr>
                    <tr>
                    <td colspan="2" align="center">
                    <jsp:include page="topPlayerDiv.jsp"/>
                    </td>
                    </tr>
                    <%--<tr>--%>
                    <%--<td colspan="2" align="center">--%>
                    <%--<jsp:include page="fightsDiv.jsp"/>--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                </table>
            </td>


            <td valign="top">
                <jsp:include page="fightsDiv.jsp"/>
            </td>
        </tr>
    </table>

</div>

<br>
Admin page <a href="http://localhost/phpmyadmin/" target="_blank">here</a><br>
Fork project at github <a href="https://github.com/tarcom/NykreditBF.git" target="_blank">here</a>
<br>

</body>
</html>