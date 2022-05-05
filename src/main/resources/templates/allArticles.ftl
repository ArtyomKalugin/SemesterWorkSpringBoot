<#include 'base.ftl'>

<#macro title>
    <title>Все статьи</title>
    <link rel="shortcut icon" href="pancake.jpg" type="image/png">

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        function showResult(title) {

            let html = ""
            const xmlhttp = new XMLHttpRequest();

            xmlhttp.onreadystatechange=function() {
                if (this.readyState===4 && this.status===200) {
                    let response = JSON.parse(this.response)

                    for (let i = 0; i < response.length; i++) {
                        html += "<a href='/detailArticle/" + response[i]['id'] + "'/>"
                        html += "<div class='alert alert-dark' role='alert'>"
                        html += "<h2>" + response[i]['title'] +"</h2>"
                        html += "<div>" + response[i]['text'] +"</div>"
                        html += "<br>"
                        html += "<img src=\"" + response[i]['photo'] + "\" width='665' height='350'>"
                        html += "<br><br>"
                        html += "<div><small class='text-muted'>" + response[i]['userNickname'] + " " +
                            response[i]['data'] + "</small></div>"
                        html += "<div><small class='text-muted'>Статья " + response[i]['id'] + "</small></div>"
                        html += "</div>" + "</a>"
                    }

                    document.getElementById("result").innerHTML = html;
                } else {
                    html = "<p class='lead'>" + "Нет статей!" + "</p>"

                    document.getElementById("result").innerText = html
                }
            }

            xmlhttp.open("GET","/allArticles/" + title, true);
            xmlhttp.send();
        }
    </script>
</#macro>

<#macro content>
    <br>
    <h1>Все статьи</h1>
    <br>

    <form>
        <p class="lead" id="1" style="float: left; margin-right: 50px;">
            Поиск по названию:<br>
            <input name="title" type="text" onkeyup="showResult(this.value)"><br>
        </p>
    </form>

    <br>
    <br>
    <br>
    <br>

    <div id="result">
        <#if allArticles?has_content>
            <#list allArticles as article>
                <a href="/detailArticle/${article.id}/0">
                    <div class="alert alert-dark" role="alert">
                        <h2>
                            ${article.title}
                        </h2>
                        <div>
                            ${article.text}
                        </div>
                        <br>
                        <img src="${article.photo}" width="665" height="350">
                        <br>
                        <br>
                        <div>
                            <small class="text-muted">
                                ${article.userNickname} ${article.data}
                            </small>
                        </div>
                        <div>
                            <small class="text-muted">
                                Статья ${article.id}
                            </small>
                        </div>
                    </div>
                </a>
            </#list>
        <#else>
            <p class="lead">Нет статей!</p>
        </#if>
    </div>
</#macro>
