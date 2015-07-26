<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.DBHandler" %>


<h4>Latest fights</h4>
<table style="border:2px solid black;border-collapse:collapse">
    <tr>
        <th style="border:1px solid black;">#</th>
        <th style="border:1px solid black;">DateTime</th>
        <th style="border:1px solid black;">Red1</th>
        <th style="border:1px solid black;">Red2</th>
        <th style="border:1px solid black;">Blue1</th>
        <th style="border:1px solid black;">Blue2</th>
        <th style="border:1px solid black;">MatchWinner</th>
        <th style="border:1px solid black;">Points at steake</th>
        <th style="border:1px solid black;">Table</th>
    </tr>
    <%
        String sql = "select * from tbl_fights order by id desc";
        for (List<String> playerList : DBHandler.genericSelect(sql)) {
    %>
    <tr>
        <td style="border:1px solid black;"><%=playerList.get(0)%></td>
        <td style="border:1px solid black;"><%=playerList.get(5)%></td>
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
