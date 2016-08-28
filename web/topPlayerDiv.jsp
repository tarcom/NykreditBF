<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.DBHandler" %>
<%@ page import="dk.skov.nykredit.bf.Util" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.SortedMap" %>
<%@ page import="java.util.TreeMap" %>


<h3>SCOREBOARDS</h3>
<table cellpadding="10" frame="1">
    <tr>
        <td>
            <h4>Last rolling day</h4>
            <%=Util.generateScoreboard(1)%>
        </td>
        <td>
            <h4>Last rolling week</h4>
            <%=Util.generateScoreboard(7)%>
        </td>
        <td>
            <h4>Last rolling month</h4>
            <%=Util.generateScoreboard(30)%>
        </td>
        <td>
            <h4>All-times</h4>
            <%=Util.generateScoreboard(9999999)%>
        </td>
        <%--<td>--%>
            <%--<h4>Most active players</h4>--%>
            <%--<%=Util.generateScoreboard(9999999)%>--%>
        <%--</td>--%>
        <%--<td>--%>
            <%--<h4>Latest active players</h4>--%>
            <%--<%--%>
                <%--TreeMap<String, String> map = new TreeMap<>();--%>
                <%--List<List<String>> players = DBHandler.genericSelect("select name from tbl_players");--%>
                <%--for (List<String> player : players) {--%>
                    <%--List<List<String>> timestamp = DBHandler.genericSelect("select timestamp from tbl_points where name='" + player.get(0) + "' limit 1");--%>
                    <%--map.put(timestamp.get(0).get(0), player.get(0));--%>
                <%--}--%>
                <%--for (String s : map.keySet()) {--%>
                    <%--System.out.println(s + " - " + map.get(s));--%>
                <%--}--%>
            <%--%>--%>
        <%--</td>--%>
    </tr>
</table>