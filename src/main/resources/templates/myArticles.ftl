<#include 'base.ftl'>

<#macro title>
    <title>Статьи</title>
    <link rel="shortcut icon" href="static/img/pancake.jpg" type="image/png">
</#macro>

<#macro content>
    <br>
    <h1>Ваши статьи</h1>
    <br>

    <form action="/myArticles" method="post" novalidate>
        <p class="lead" id="1" style="float: left; margin-right: 50px;">
            Поиск по названию:<br>
            <input name="title" type="text"/><br>
        </p>

        <br>
        <p class="lead" style="margin-right: 1000px;">
            <input type="submit" value="Найти">
        </p>
    </form>

    <br>

    <#if articles??>
        <#if articles?has_content>
            <#list articles as article>
                <a href="/myDetailArticle?id=${article.id}">
                    <div class="alert alert-dark" role="alert">
                        <h2>${article.title}</h2>
                        <div>${article.text}</div>
                        <br>
                        <img src="${article.photo}" width="665" height="350">
                        <br>
                        <br>
                        <div><small class="text-muted">${article.userNickname} ${article.data}</small></div>
                        <div><small class="text-muted">Статья ${article.id}</small></div>
                        <br>
                        <div><small class="text-muted" style="font-size:20px"><a href="/deleteArticle?id=${article.id}">Удалить</a></small></div>
                    </div>
                </a>
            </#list>

        <#else>
            <p class="lead">Нет статей!</p>
        </#if>
    </#if>

</#macro>
