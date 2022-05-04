<#include 'base.ftl'>

<#macro title>
    <title>Все пользователи</title>
    <link rel="shortcut icon" href="pancake.jpg" type="image/png">

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
    </form>
    <br>
    <br>
    <br>

    <#if allUsers?has_content>
        <div id="result">
            <#list allUsers as user>
                <a href="/detailUser/${user.id}">
                    <div class="alert alert-dark" role="alert">
                        <table>
                            <tr>
                                <td>
                                    <img alt="user_img" src="${user.avatar}"width="50" height="50" class="rounded-circle">
                                </td>
                                <td>
                                    <h3>
                                        <strong>
                                            ${user.nickname}
                                        </strong>
                                    </h3>
                                </td>
                            </tr>
                        </table>
                    </div>
                </a>
            </#list>
        </div>
    <#else>
        <p class="lead">Нет пользователей!</p>
    </#if>
</#macro>
