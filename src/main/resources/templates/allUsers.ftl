<#include 'base.ftl'>

<#macro title>
    <title>Все пользователи</title>
    <link rel="shortcut icon" href="static/img/pancake.jpg" type="image/png">

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function showAll() {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange=function() {
                if (this.readyState===4 && this.status===200) {
                    document.getElementById("result").innerHTML=this.responseText;
                }
            }
            xmlhttp.open("GET","/allUsers?nickname=", true);
            xmlhttp.send();
        }

        function showResult(nickname) {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange=function() {
                if (this.readyState===4 && this.status===200) {
                    document.getElementById("result").innerHTML = this.responseText;
                }
            }
            xmlhttp.open("GET","/allUsers?nickname=" + nickname, true);
            xmlhttp.send();
        }
    </script>
</#macro>

<#macro content>
    <br>
    <h1>Все пользователи</h1>
    <br>

    <form>
        <p class="lead" id="1" style="float: left; margin-right: 50px;">
            Поиск по никнейму:<br>
            <input name="nickname" type="text" onkeyup="showResult(this.value)"/><br>
        </p>

        <br>
        <p class="lead" style="margin-right: 1000px;">
            <input type="button" value="Показать всех" onclick="showAll()">
        </p>
    </form>
    <br>
    <div id="result"></div>
</#macro>
