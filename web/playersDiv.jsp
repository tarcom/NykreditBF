<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.DBHandler" %>

<%
    if (request.getParameter("addPlayer") != null && request.getParameter("addPlayer").length() > 1) {
        DBHandler.executeUpdateDb("INSERT INTO `tbl_players` (`name`) VALUES ('" + request.getParameter("addPlayer") + "')");
    }

    if (request.getParameter("PlayerReady") != null && request.getParameter("PlayerReady").length() > 1) {
        DBHandler.executeUpdateDb("UPDATE  `tbl_players` SET  `playerReady` = !`playerReady` WHERE name =  '" + request.getParameter("PlayerReady") + "'");
    }




%>

<table align="left" style="border:2px solid black;border-collapse:collapse">

    <tr>
        <th style="border:1px solid black;">#</th>
        <th style="border:1px solid black;"></th>
        <th style="border:1px solid black;">Name</th>
        <th style="border:1px solid black;"></th>
        <th style="border:1px solid black;">points</th>
        <th style="border:1px solid black;">games played</th>
        <th style="border:1px solid black;">games won</th>
    </tr>
    <%
        for (List<String> playerList : DBHandler.genericSelect("SELECT `name`, `playerReady` FROM `tbl_players` order by name")) {
            String player = playerList.get(0);
            String playerReady = (playerList.get(1).equals("1") ? "checked" : "");
            String playerReadyGreen = (playerList.get(1).equals("1") ? "bgcolor=\"#7cfc00\"" : "");
    %>
    <tr <%=playerReadyGreen%>>
        <td style="border:1px solid black;">x</td>

        <td style="border:1px solid black;"><input type="checkbox" <%=playerReady%> disabled></td>

        <td style="border:1px solid black;"><%=player%></td>

        <form action="index.jsp" method="POST">
            <td style="border:1px solid black;">
                <input type="hidden" name="PlayerReady" value="<%=player%>">
                <input type="submit" value="Ready?">
            </td>
        </form>

        <td style="border:1px solid black;">x</td>

        <td style="border:1px solid black;">x</td>

        <td style="border:1px solid black;">x</td>
    </tr>
    <%
        }
    %>

    <form action="index.jsp" method="POST">
        <tr>
            <td></td>
            <td></td>
            <td><input type="text" size="5" name="addPlayer"></td>
            <td><input type="submit" value="add player"/></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </form>

</table>

<br>