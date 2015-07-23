<%@ page import="dk.skov.nykredit.bf.DBHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dk.skov.nykredit.bf.Util" %>

<%

    if (request.getParameter("newGame") != null) {
        Util.playersReadyListList = DBHandler.genericSelect("SELECT name FROM tbl_players where playerReady = 1 ORDER BY RAND() LIMIT 8");
    }
    ArrayList<String> dummy = new ArrayList<String>();
    dummy.add("n/a");

    for (int i = Util.playersReadyListList.size(); i < 8; i++) {
        Util.playersReadyListList.add(dummy);
    }


    //----------



    if (request.getParameter("t1BlueWinner") != null) {
        String t1Intense = request.getParameter("t1Intense");
        System.out.println("t1Intense=" + t1Intense);
        DBHandler.fightWon(request.getParameter("t1r1"), request.getParameter("t1r2"), request.getParameter("t1b1"), request.getParameter("t1b2"), "blue", (t1Intense.equals("t1b") ? 2 : 1), 1);
    }

    if (request.getParameter("t1RedWinner") != null) {
        String t1Intense = request.getParameter("t1Intense");
        System.out.println("t1Intense=" + t1Intense);
        DBHandler.fightWon(request.getParameter("t1r1"), request.getParameter("t1r2"), request.getParameter("t1b1"), request.getParameter("t1b2"), "red", (t1Intense.equals("t1r") ? 2 : 1), 1);
    }

    if (request.getParameter("t2BlueWinner") != null) {
        String t2Intense = request.getParameter("t2Intense");
        System.out.println("t2Intense=" + t2Intense);
        DBHandler.fightWon(request.getParameter("t2r1"), request.getParameter("t2r2"), request.getParameter("t2b1"), request.getParameter("t2b2"), "blue", (t2Intense.equals("t2b") ? 2 : 1), 2);
    }

    if (request.getParameter("t2RedWinner") != null) {
        String t2Intense = request.getParameter("t2Intense");
        System.out.println("t2Intense=" + t2Intense);
        DBHandler.fightWon(request.getParameter("t2r1"), request.getParameter("t2r2"), request.getParameter("t2b1"), request.getParameter("t2b2"), "red", (t2Intense.equals("t2r") ? 2 : 1), 2);
    }


    //-----------



    System.out.println(Util.playersReadyListList);

    //table 1
    int t1r1 = DBHandler.getTablePlayerPoints(2);
    int t1r2 = DBHandler.getTablePlayerPoints(3);
    int t1b1 = DBHandler.getTablePlayerPoints(0);
    int t1b2 = DBHandler.getTablePlayerPoints(1);
    int t1TeamDiffInt = (t1r1 + t1r2) - (t1b1 + t1b2);
    String t1TeamDiff = String.valueOf(t1TeamDiffInt).replaceAll("-", "");

    String t1Intense = "";
    if (t1TeamDiffInt >= Util.INTENSE_LEVEL) {
        t1TeamDiff += " !!!!!INTENSE!!!!! Winning blue team takes 2 points!";
        t1Intense = "t1b";
    } else if (t1TeamDiffInt <= Util.INTENSE_LEVEL * -1) {
        t1TeamDiff += " !!!!!INTENSE!!!!! Winning red team takes 2 points!";
        t1Intense = "t1r";
    }


    //table 2
    int t2r1 = DBHandler.getTablePlayerPoints(6);
    int t2r2 = DBHandler.getTablePlayerPoints(7);
    int t2b1 = DBHandler.getTablePlayerPoints(4);
    int t2b2 = DBHandler.getTablePlayerPoints(5);
    int t2TeamDiffInt = (t2r1 + t2r2) - (t2b1 + t2b2);
    String t2TeamDiff = String.valueOf(t2TeamDiffInt).replaceAll("-", "");

    String t2Intense = "";
    if (t2TeamDiffInt >= Util.INTENSE_LEVEL) {
        t2TeamDiff += " !!!!!INTENSE!!!!! Winning blue team takes 2 points!";
        t2Intense = "t2b";
    } else if (t2TeamDiffInt <= Util.INTENSE_LEVEL * -1) {
        t2TeamDiff += " !!!!!INTENSE!!!!! Winning red team takes 2 points!";
        t2Intense = "t2r";
    }





%>

<form action="index.jsp" method="POST">
    <input type="hidden" name="newGame" value="true">
    <input type="submit" value="Generate new random game">
</form>

<br>
<br>


<form action="index.jsp" method="POST">
    <b>Table 1 (new table) - teamDiff = <%=t1TeamDiff%></b><br>

    Red team:&nbsp;
    <input type="text" size="5" name="t1r1" value="<%=Util.playersReadyListList.get(2).get(0)%>"> &
    <input type="text" size="5" name="t1r2" value="<%=Util.playersReadyListList.get(3).get(0)%>">
    <input type="text" hidden name="t1Intense" value="<%=t1Intense%>"/>
    (<%=t1r1%> & <%=t1r2%> = <%=(t1r1 + t1r2)%> points)
    <input type="submit" name="t1RedWinner" value="Claim red victory on table 1!"> <br>

    Blue team:
    <input type="text" size="5" name="t1b1" value="<%=Util.playersReadyListList.get(0).get(0)%>"> &
    <input type="text" size="5" name="t1b2" value="<%=Util.playersReadyListList.get(1).get(0)%>">
    <input type="text" hidden name="t1Intense" value="<%=t1Intense%>"/>
    (<%=t1b1%> & <%=t1b2%> = <%=(t1b1 + t1b2)%> points)
    <input type="submit" name="t1BlueWinner" value="Claim blue victory on table 1!">

    <br>
    <br>

    <b>Table 2 (old table) - teamDiff = <%=t2TeamDiff%></b></b><br>

    Red team:&nbsp;
    <input type="text" size="5" name="t2r1" value="<%=Util.playersReadyListList.get(6).get(0)%>"> &
    <input type="text" size="5" name="t2r2" value="<%=Util.playersReadyListList.get(7).get(0)%>">
    <input type="text" hidden name="t2Intense" value="<%=t2Intense%>"/>
    (<%=t2r1%> & <%=t2r2%> = <%=(t2r1 + t2r2)%> points)
    <input type="submit" name="t2RedWinner" value="Claim red victory on table 2!"> <br>

    Blue team:
    <input type="text" size="5" name="t2b1" value="<%=Util.playersReadyListList.get(4).get(0)%>"> &
    <input type="text" size="5" name="t2b2" value="<%=Util.playersReadyListList.get(5).get(0)%>">
    <input type="text" hidden name="t2Intense" value="<%=t2Intense%>"/>
    (<%=t2b1%> & <%=t2b2%> = <%=(t2b1 + t2b2)%> points)
    <input type="submit" name="t2BlueWinner" value="Claim blue victory on table 2!">

</form>
