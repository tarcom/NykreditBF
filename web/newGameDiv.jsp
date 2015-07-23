<%@ page import="dk.skov.nykredit.bf.DBHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dk.skov.nykredit.bf.Util" %>
<%



    if (request.getParameter("t1BlueWinner") != null) {
        DBHandler.fightWon(request.getParameter("t1r1"), request.getParameter("t1r2"), request.getParameter("t1b1"), request.getParameter("t1b2"), "blue", 1, 1);
    }

    if (request.getParameter("t1RedWinner") != null) {
        DBHandler.fightWon(request.getParameter("t1r1"), request.getParameter("t1r2"), request.getParameter("t1b1"), request.getParameter("t1b2"), "red", 1, 1);
    }

    if (request.getParameter("t2BlueWinner") != null) {
        DBHandler.fightWon(request.getParameter("t2r1"), request.getParameter("t2r2"), request.getParameter("t2b1"), request.getParameter("t2b2"), "blue", 1, 2);
    }

    if (request.getParameter("t2RedWinner") != null) {
        DBHandler.fightWon(request.getParameter("t2r1"), request.getParameter("t2r2"), request.getParameter("t2b1"), request.getParameter("t2b2"), "red", 1, 2);
    }





%>

<form action="index.jsp" method="POST">
    <input type="hidden" name="newGame" value="true">
    <input type="submit" value="Generate new random game">
</form>

<br>
<br>

<%


    if (request.getParameter("newGame") != null) {
        Util.PR = DBHandler.genericSelect("SELECT name FROM tbl_players where playerReady = 1 ORDER BY RAND() LIMIT 8");
    }
    ArrayList<String> dummy = new ArrayList<String>();
    dummy.add("n/a");

    for (int i = Util.PR.size(); i < 8; i++) {
        Util.PR.add(dummy);
    }
%>

<b>Table 1 (new table)</b><br>

<form action="index.jsp" method="POST">
    Red team:&nbsp;
    <input type="text" size="5" name="t1r1" value="<%=Util.PR.get(2).get(0)%>"> &
    <input type="text" size="5" name="t1r2" value="<%=Util.PR.get(3).get(0)%>">
    <input type="submit" name="t1RedWinner" value="Claim red victory on table 1!"> <br>
    Blue team:
    <input type="text" size="5" name="t1b1" value="<%=Util.PR.get(0).get(0)%>"> &
    <input type="text" size="5" name="t1b2" value="<%=Util.PR.get(1).get(0)%>">
    <input type="submit" name="t1BlueWinner" value="Claim blue victory on table 1!">
    <br>
    <br>

    <b>Table 2 (old table)</b><br>
    Red team:&nbsp;
    <input type="text" size="5" name="t2r1" value="<%=Util.PR.get(6).get(0)%>"> &
    <input type="text" size="5" name="t2r2" value="<%=Util.PR.get(7).get(0)%>">
    <input type="submit" name="t2RedWinner" value="Claim red victory on table 2!"> <br>
    Blue team:
    <input type="text" size="5" name="t2b1" value="<%=Util.PR.get(4).get(0)%>"> &
    <input type="text" size="5" name="t2b2" value="<%=Util.PR.get(5).get(0)%>">
    <input type="submit" name="t2BlueWinner" value="Claim blue victory on table 2!">

</form>
