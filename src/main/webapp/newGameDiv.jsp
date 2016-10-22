<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dk.skov.nykredit.bf.Util" %>
<%@ page import="dk.skov.nykredit.bf.ScoreBoardGenerator" %>

<form action="index.jsp" method="POST">
    <input type="hidden" name="newGame" value="true">
    <input type="submit" size="5" value="Generate new random game" class="btn green">
</form>

<form action="index.jsp" name="claimVictoryForm" method="POST">
    <table>
        <% if (Util.tableOne != null) { %>
            <tr <% if (ScoreBoardGenerator.getSingleton().getRedDog(Util.tableTwo) == ScoreBoardGenerator.RedDog.TOPDOG) %> bgcolor="red" <%;%> >
                <td width="60px">
                    Red team:
                </td>
                <td width="160px">
                    <input type="text" size="5" name="t1r1" value="<%=Util.tableOne.getPlayer_red_1_name()%>"> &
                    <input type="text" size="5" name="t1r2" value="<%=Util.tableOne.getPlayer_red_2_name()%>">
                </td>
                <td width="100px">
                    (<%=Util.getRedSumTableOne()%> points)
                </td>
                <td width="250px">
                    <input type="submit" name="t1RedWinner" value="Claim red victory on table 1!" class="btn small red alskSize">
                </td>
            </tr>

            <tr <% if (ScoreBoardGenerator.getSingleton().getRedDog(Util.tableTwo) == ScoreBoardGenerator.RedDog.UNDERDOG) %> bgcolor="blue" <%;%> >
                <td>
                    Blue team:
                </td>
                <td>
                    <input type="text" size="5" name="t1b1" value="<%=Util.tableOne.getPlayer_blue_1_name()%>"> &
                    <input type="text" size="5" name="t1b2" value="<%=Util.tableOne.getPlayer_blue_2_name()%>">

                </td>
                <td>
                    (<%=Util.getBlueSumTableOne()%> points)
                </td>
                <td>
                    <input type="submit" name="t1BlueWinner" value="Claim blue victory on table 1!" class="btn small blue alskSize">
                </td>
            </tr>
        <% } %>

        <tr>
            <td colspan="4">
                &nbsp;
            </td>
        </tr>
        <tr>
            <td colspan="4">
                &nbsp;
            </td>
        </tr>

        <% if (Util.tableTwo != null) { %>
            <tr <% if (ScoreBoardGenerator.getSingleton().getRedDog(Util.tableTwo) == ScoreBoardGenerator.RedDog.TOPDOG) %> bgcolor="red" <%;%> >
                <td>
                    Red team:
                </td>
                <td>
                    <input type="text" size="5" name="t2r1" value="<%=Util.tableTwo.getPlayer_red_1_name() %>"> &
                    <input type="text" size="5" name="t2r2" value="<%=Util.tableTwo.getPlayer_red_2_name() %>">
                </td>
                    <td>
                    (<%=Util.getRedSumTableTwo()%> points)
                    </td>
                <td>
                    <input type="submit" name="t2RedWinner" value="Claim red victory on table 2!" class="btn small red alskSize"> <br>
                </td>
            </tr>

            <tr <% if (ScoreBoardGenerator.getSingleton().getRedDog(Util.tableTwo) == ScoreBoardGenerator.RedDog.UNDERDOG) %> bgcolor="blue" <%;%> >
                <td>
                    Blue team:
                </td>
                <td>
                    <input type="text" size="5" name="t2b1" value="<%=Util.tableTwo.getPlayer_blue_1_name()%>"> &
                    <input type="text" size="5" name="t2b2" value="<%=Util.tableTwo.getPlayer_blue_2_name()%>">
                </td>
                    <td>
                    (<%=Util.getBlueSumTableTwo()%> points)
                    </td>
                <td>
                    <input type="submit" name="t2BlueWinner" value="Claim blue victory on table 2!" class="btn small blue alskSize">
                </td>
            </tr>
        <% } %>
    </table>






</form>
