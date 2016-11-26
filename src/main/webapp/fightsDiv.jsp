<%@ page import="java.util.List" %>
<%@ page import="dk.skov.nykredit.bf.ScoreBoardGenerator" %>
<%@ page import="dk.skov.nykredit.bf.Model.Game" %>


<h4>All fights</h4>
<table style="border:2px solid black;border-collapse:collapse">
    <tr>
        <th style="border:1px solid black;">#</th>
        <th style="border:1px solid black;">DateTime</th>
        <th style="border:1px solid black;">Red1</th>
        <th style="border:1px solid black;">Red2</th>
        <th style="border:1px solid black;">Blue1</th>
        <th style="border:1px solid black;">Blue2</th>
        <th style="border:1px solid black;">Match winner</th>
    </tr>
    <%
        int count = 0;
        for (Game game : ScoreBoardGenerator.getSingleton().getAllGames()) {
            count++;
            if (count > 30) break;
    %>
    <tr>
        <td style="border:1px solid black;"><%=game.getId()%></td>
        <td style="border:1px solid black;"><%=game.getTimestamp()%></td>

        <% if (game.getPlayer_red_1() != null) { %>
        <td style="border:1px solid black;"><%=game.getPlayer_red_1().getName() %></td>
        <%  } else { %>
        <td style="border:1px solid black;"></td>
        <%  } %>

        <% if (game.getPlayer_red_2() != null) { %>
        <td style="border:1px solid black;"><%=game.getPlayer_red_2().getName() %></td>
        <%  } else { %>
        <td style="border:1px solid black;"></td>
        <%  } %>

        <% if (game.getPlayer_blue_1() != null) { %>
        <td style="border:1px solid black;"><%=game.getPlayer_blue_1().getName() %></td>
        <%  } else { %>
        <td style="border:1px solid black;"></td>
        <%  } %>

        <% if (game.getPlayer_blue_2() != null) { %>
        <td style="border:1px solid black;"><%=game.getPlayer_blue_2().getName() %></td>
        <%  } else { %>
        <td style="border:1px solid black;"></td>
        <%  } %>

        <td style="border:1px solid black;"><%=game.getWinner()%></td>
    </tr>
    <%
        }
    %>
</table>
See (and manipulate) all fights <a href="http://localhost/phpmyadmin/sql.php?server=1&db=nykreditbf&table=tbl_fights" target="_blank">here</a>.
