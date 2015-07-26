<%@ page import="dk.skov.nykredit.bf.DBHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dk.skov.nykredit.bf.Util" %>

<%




%>

<form action="index.jsp" method="POST">
    <input type="hidden" name="newGame" value="true">
    <input type="submit" value="Generate new random game">
</form>

<br>
<br>


<form action="index.jsp" method="POST">
    <b>Table 1 (new table) - teamDiff = <%=Util.t1TeamDiff%></b><br>

    Red team:&nbsp;
    <input type="text" size="5" name="t1r1" value="<%=Util.playersReadyListList.get(0).get(0)%>"> &
    <input type="text" size="5" name="t1r2" value="<%=Util.playersReadyListList.get(2).get(0)%>">
    <input type="text" hidden name="t1Intense" value="<%=Util.t1Intense%>"/>
    (<%=Util.t1r1%> & <%=Util.t1r2%> = <%=(Util.t1r1 + Util.t1r2)%> points)
    <input type="submit" name="t1RedWinner" value="Claim red victory on table 1!"> <br>

    Blue team:
    <input type="text" size="5" name="t1b1" value="<%=Util.playersReadyListList.get(1).get(0)%>"> &
    <input type="text" size="5" name="t1b2" value="<%=Util.playersReadyListList.get(3).get(0)%>">
    <input type="text" hidden name="t1Intense" value="<%=Util.t1Intense%>"/>
    (<%=Util.t1b1%> & <%=Util.t1b2%> = <%=(Util.t1b1 + Util.t1b2)%> points)
    <input type="submit" name="t1BlueWinner" value="Claim blue victory on table 1!">

    <br>
    <br>

    <b>Table 2 (old table) - teamDiff = <%=Util.t2TeamDiff%></b></b><br>

    Red team:&nbsp;
    <input type="text" size="5" name="t2r1" value="<%=Util.playersReadyListList.get(4).get(0)%>"> &
    <input type="text" size="5" name="t2r2" value="<%=Util.playersReadyListList.get(6).get(0)%>">
    <input type="text" hidden name="t2Intense" value="<%=Util.t2Intense%>"/>
    (<%=Util.t2r1%> & <%=Util.t2r2%> = <%=(Util.t2r1 + Util.t2r2)%> points)
    <input type="submit" name="t2RedWinner" value="Claim red victory on table 2!"> <br>

    Blue team:
    <input type="text" size="5" name="t2b1" value="<%=Util.playersReadyListList.get(5).get(0)%>"> &
    <input type="text" size="5" name="t2b2" value="<%=Util.playersReadyListList.get(7).get(0)%>">
    <input type="text" hidden name="t2Intense" value="<%=Util.t2Intense%>"/>
    (<%=Util.t2b1%> & <%=Util.t2b2%> = <%=(Util.t2b1 + Util.t2b2)%> points)
    <input type="submit" name="t2BlueWinner" value="Claim blue victory on table 2!">

</form>
