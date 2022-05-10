<#include 'base.ftl'>

<#macro title>
    <title>Все пользователи</title>
    <link rel="shortcut icon" href="pancake.jpg" type="image/png">

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function showResult() {

            let html = ""
            const xmlhttp = new XMLHttpRequest();

            xmlhttp.onreadystatechange=function() {
                if (this.readyState===4 && this.status===200) {
                    let response = JSON.parse(this.response)

                    if (response.length !== 0) {
                        for (let i = 0; i < response.length; i++) {

                            html += "<a href='/detailUser/" + response[i]['id'] + "'><div class='alert alert-dark' role='alert'><table><tr>"
                            html += "<td><img alt='user_img' src='" + response[i]['avatar'] + "'width='50' height='50' class='rounded-circle'>"
                            html += "</td><td><h3><strong>" + response[i]['nickname'] + "</strong></h3></td></tr></table></div></a>"
                        }
                    } else {
                        html = "<p class='lead'>Нет пользователей!</p>"
                    }

                    document.getElementById("result").innerHTML = html;
                }
            }

            xmlhttp.open("GET","/allFindUsers?nickname=" + document.getElementById('nickname').value, true);
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
            <input id="nickname" name="nickname" type="text" onkeyup="showResult()"/><br>
        </p>

        <br>
    </form>
    <br>
    <br>
    <br>

    <div id="result">
        <#if allUsers?has_content>
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
        <#else>
            <p class="lead">Нет пользователей!</p>
        </#if>
    </div>
</#macro>
