<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.ScoreBoardGenerator" %>
<%@ page import="dk.skov.nykredit.bf.SimpleDBHandler" %>
<%@ page import="dk.skov.nykredit.bf.Model.Player" %>

<%
    if (request.getParameter("addPlayer") != null && request.getParameter("addPlayer").length() > 1) {
        SimpleDBHandler.addPlayer(request.getParameter("addPlayer"));
        ScoreBoardGenerator.getSingleton().addPlayer(request.getParameter("addPlayer"));
    }

    if (request.getParameter("PlayerReady") != null && request.getParameter("PlayerReady").length() > 1) {
        SimpleDBHandler.switchPlayerReady(request.getParameter("PlayerReady"));
        ScoreBoardGenerator.getSingleton().switchReadyPlayer(request.getParameter("PlayerReady"));
    }
%>

<table style="border:2px solid black;border-collapse:collapse">

    <tr>
        <th style="border:1px solid black;">Name</th>
        <th style="border:1px solid black;">Last Played</th>
        <th style="border:1px solid black;"></th>
    </tr>

    <%
        for (Player player : ScoreBoardGenerator.getSingleton().getAllPlayers().values()) {
            String playerReadyGreen = player.isReady() ? "bgcolor=\"#7cfc00\"" : "";
    %>
        <tr <%=playerReadyGreen%> >
            <td style="border:1px solid black;"><%=player.getName()%></td>
            <td style="border:1px solid black;"><%=player.getLastGamePlayed()%></td>

            <form action="index.jsp" method="POST">
                <td style="border:1px solid black;">
                    <input type="hidden" name="PlayerReady" value="<%=player.getName()%>">
                    <input type="submit" value="Ready?" class="btnPlayerReady">
                </td>
            </form>
        </tr>
    <%
        }
    %>

    <form action="index.jsp" method="POST">
        <tr>
            <td><input type="text" size="5" name="addPlayer"></td>
            <td/>
            <td colspan="4"><input type="submit" value="add player"/></td>
        </tr>
    </form>

</table>

<br>