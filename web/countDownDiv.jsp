<%@ page import="java.util.Random" %>

<%
    int playtime = 120;
    if (request.getParameter("playtime") != null) {
        playtime = Integer.valueOf(request.getParameter("playtime"));
    }
%>
<script type="text/javascript">
    function startTimer(duration, display) {
        var start = Date.now(),
                diff,
                minutes,
                seconds;

        function timer() {
            // get the number of seconds that have elapsed since
            // startTimer() was called
            diff = duration - (((Date.now() - start) / 1000) | 0);

            // does the same job as parseInt truncates the float
            minutes = (diff / 60) | 0;
            seconds = (diff % 60) | 0;

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            display.textContent = minutes + ":" + seconds;

            if (diff <= 0 && diff > -1) {
                // add one second so that the count down starts at the full duration
                // example 05:00 not 04:59
                //start = Date.now() + 1000;

                //display.textContent = "00:00";
                EvalSound('finish');
            }
        };
        // we don't want to wait a full second before the timer starts
        timer();
        setInterval(timer, 1000);
    }

    window.onload = function () {
        var twoMinutes = <%=playtime%>,
                display = document.querySelector('#time');
        startTimer(twoMinutes, display);
    };


    function EvalSound(soundobj) {
        var thissound = document.getElementById(soundobj);
        thissound.play();
    }
</script>

<div>

    <%
        int dukeAudio = new Random().nextInt(7) + 1;
    %>
    <audio id="startAudio" src="sounds/duke/<%=dukeAudio%>.wav" preload="auto" autobuffer></audio>
    <audio id="finish" src="sounds/finish.wav" preload="auto" autobuffer/>
    </audio>


    <table>
        <tr>
            <td>
                <form action="index.jsp" method="POST">
                    <input type="hidden" name="startTimer" value="true">
                    <select name="playtime" class="btn">
                        <option value="5">5 sec</option>
                        <option value="60">1 min</option>
                        <option value="120" selected="selected">2 min</option>
                        <option value="180">3 min</option>
                        <option value="240">4 min</option>
                        <option value="300">5 min</option>
                    </select>

                    <input type="submit" value="Start Timer!" class="btn green large">



                </form>

            </td>
            <td>
                <p style="font-size:50px; color: red";>
                    Countdown:
                    <%
                        if (request.getParameter("startTimer") != null) {
                    %>
                    <span id="time"></span>
                    <script>
                        EvalSound('startAudio');
                    </script>
                    <%
                    } else {
                    %>
                    00:00
                    <%
                        }
                    %>
                </p>
            </td>
        </tr>
    </table>



</div>




