<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.DBHandler" %>


<h4>Latest 30 fights</h4>
<table style="border:2px solid black;border-collapse:collapse">
    <tr>
        <th style="border:1px solid black;">#</th>
        <th style="border:1px solid black;">DateTime</th>
        <th style="border:1px solid black;">Red1</th>
        <th style="border:1px solid black;">Red2</th>
        <th style="border:1px solid black;">Blue1</th>
        <th style="border:1px solid black;">Blue2</th>
        <th style="border:1px solid black;">Match winner</th>
        <th style="border:1px solid black;">Points at stake</th>
        <th style="border:1px solid black;">Table</th>
    </tr>
    <%
        String sql = "select * from tbl_fights order by id desc limit 30";
        for (List<String> playerList : DBHandler.genericSelect(sql)) {
    %>
    <tr>
        <td style="border:1px solid black;"><%=playerList.get(0)%></td>
        <td style="border:1px solid black;"><%=playerList.get(5).substring(0, playerList.get(5).length()-2)%></td>
        <td style="border:1px solid black;"><%=playerList.get(1)%></td>
        <td style="border:1px solid black;"><%=playerList.get(2)%></td>
        <td style="border:1px solid black;"><%=playerList.get(3)%></td>
        <td style="border:1px solid black;"><%=playerList.get(4)%></td>
        <td style="border:1px solid black;"><%=playerList.get(6)%></td>
        <td style="border:1px solid black;"><%=playerList.get(7)%></td>
        <td style="border:1px solid black;"><%=playerList.get(8)%></td>
    </tr>
    <%
        }
    %>
</table>
See (and manipulate) all fights <a href="http://localhost/phpmyadmin/sql.php?server=1&db=nykreditbf&table=tbl_fights" target="_blank">here</a>.
