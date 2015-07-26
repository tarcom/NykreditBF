<%@ page import="dk.skov.nykredit.bf.DBHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dk.skov.nykredit.bf.Util" %>

<form action="index.jsp" method="POST">
    <input type="hidden" name="newGame" value="true">
    <input type="submit" size="5" value="Generate new random game" class="btn green">
</form>

<%
    List<List<String>> lists = DBHandler.genericSelect("select distinct name from tbl_players");
    String playersRegex = "";
    for(List<String> player : lists) {
        playersRegex += player.get(0) + "|";
    }

    if (playersRegex.endsWith("|")) {
        playersRegex = playersRegex.substring(0, playersRegex.length() - 1);
    }

    String formRequiredFieldPatttern = "pattern='" + playersRegex + "' title='valid names are: " + playersRegex.replaceAll("\\|", ", ") + "'";


%>

<form action="index.jsp" name="claimVictoryForm" method="POST">
    <table>
        <tr>
            <td colspan="4">
                <b>Table 1 (new table) - teamDiff = <%=Util.t1TeamDiff%></b>
            </td>
        </tr>

        <tr>
            <td width="60px">
                Red team:
            </td>
            <td width="160px">
                <input type="text" size="5" name="t1r1" <%=formRequiredFieldPatttern%> required value="<%=Util.playersReadyListList.get(0).get(0)%>"> &
                <input type="text" size="5" name="t1r2" <%=formRequiredFieldPatttern%> value="<%=Util.playersReadyListList.get(2).get(0)%>">
                <input type="text" hidden name="t1Intense" value="<%=Util.t1Intense%>"/>
            </td>
            <td width="100px">
                (<%=Util.t1r1%> & <%=Util.t1r2%> = <%=(Util.t1r1 + Util.t1r2)%> points)
            </td>
            <td width="250px">
                <input type="submit" name="t1RedWinner" value="Claim red victory on table 1!" class="btn small red alskSize">
            </td>
        </tr>

        <tr>
            <td>
                Blue team:
            </td>
            <td>
                <input type="text" size="5" name="t1b1" <%=formRequiredFieldPatttern%> value="<%=Util.playersReadyListList.get(1).get(0)%>"> &
                <input type="text" size="5" name="t1b2" <%=formRequiredFieldPatttern%> value="<%=Util.playersReadyListList.get(3).get(0)%>">
                <input type="text" hidden name="t1Intense" value="<%=Util.t1Intense%>"/>
            </td>
            <td>
                (<%=Util.t1b1%> & <%=Util.t1b2%> = <%=(Util.t1b1 + Util.t1b2)%> points)
            </td>
            <td>
                <input type="submit" name="t1BlueWinner" value="Claim blue victory on table 1!" class="btn small blue alskSize">
            </td>
        </tr>

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
        <tr>
            <td colspan="4">
                <b>Table 2 (old table) - teamDiff = <%=Util.t2TeamDiff%></b>
            </td>
        </tr>
        <tr>
            <td>
                Red team:
            </td>
            <td>
                <input type="text" size="5" name="t2r1" <%=formRequiredFieldPatttern%> value="<%=Util.playersReadyListList.get(4).get(0)%>"> &
                <input type="text" size="5" name="t2r2" <%=formRequiredFieldPatttern%> value="<%=Util.playersReadyListList.get(6).get(0)%>">
                <input type="text" hidden name="t2Intense" value="<%=Util.t2Intense%>"/>
            </td>
            <td>
                (<%=Util.t2r1%> & <%=Util.t2r2%> = <%=(Util.t2r1 + Util.t2r2)%> points)
            </td>
            <td>
                <input type="submit" name="t2RedWinner" value="Claim red victory on table 2!" class="btn small red alskSize"> <br>
            </td>
        </tr>
        <tr>
            <td>
                Blue team:
            </td>
            <td>
                <input type="text" size="5" name="t2b1" <%=formRequiredFieldPatttern%> value="<%=Util.playersReadyListList.get(5).get(0)%>"> &
                <input type="text" size="5" name="t2b2" <%=formRequiredFieldPatttern%> value="<%=Util.playersReadyListList.get(7).get(0)%>">
                <input type="text" hidden name="t2Intense" value="<%=Util.t2Intense%>"/>
            </td>
            <td>
                (<%=Util.t2b1%> & <%=Util.t2b2%> = <%=(Util.t2b1 + Util.t2b2)%> points)
            </td>
            <td>
                <input type="submit" name="t2BlueWinner" value="Claim blue victory on table 2!" class="btn small blue alskSize">
            </td>
        </tr>
    </table>






</form>
