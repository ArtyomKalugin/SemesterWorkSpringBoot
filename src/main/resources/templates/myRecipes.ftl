<#include 'base.ftl'>

<#macro title>
    <title>Ваши рецепты</title>
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
                        html += "<a href='/detailRecipe/" + response[i]['id'] + "'/>"
                        html += "<div class='alert alert-dark' role='alert'>"
                        html += "<h2>" + response[i]['title'] +"</h2>"
                        html += "<div>" + response[i]['text'] +"</div>"
                        html += "<br>"
                        html += "<img src=\"" + response[i]['photo'] + "\" width='665' height='350'>"
                        html += "<br><br>"
                        html += "<div><small class='text-muted'>" + response[i]['userNickname'] + " " +
                            response[i]['data'] + "</small></div>"
                        html += "<div><small class='text-muted'>Рецепт " + response[i]['id'] + "</small></div>"
                        html += "</div>" + "</a>"
                    }

                    document.getElementById("result").innerHTML = html;
                } else {
                    html = "<p class='lead'>" + "Нет рецептов!" + "</p>"

                    document.getElementById("result").innerText = html
                }
            }

            xmlhttp.open("GET","/allRecipes/" + title, true);
            xmlhttp.send();
        }
    </script>
</#macro>

<#macro content>
    <br>
    <h1>Ваши рецепты</h1>
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
        <#if myRecipes?has_content>
            <#list myRecipes as recipe>
                <a href="/detailRecipe/${recipe.id}/1">
                    <div class="alert alert-dark" role="alert">
                        <h2>
                            ${recipe.title}
                        </h2>
                        <div>
                            ${recipe.text}
                        </div>
                        <br>
                        <img src="${recipe.photo}" width="665" height="350">
                        <br>
                        <br>
                        <div>
                            <small class="text-muted">
                                ${recipe.userNickname} ${recipe.data}
                            </small>
                        </div>
                        <div>
                            <small class="text-muted">
                                Рецепт ${recipe.id}
                            </small>
                        </div>
                        <br>
                        <div>
                            <small class="text-muted" style="font-size:20px">
                                <a href="/deleteRecipe/${recipe.id}">Удалить</a>
                            </small>
                        </div>
                    </div>
                </a>
            </#list>
        <#else>
            <p class="lead">Нет рецептов!</p>
        </#if>
    </div>
</#macro>

