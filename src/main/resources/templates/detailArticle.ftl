<#include 'base.ftl'>

<#macro title>
    <title>Статья</title>
    <link rel="shortcut icon" href="static/img/pancake.jpg" type="image/png">
</#macro>

<#macro content>
    <br>
    <#if u??>
        <p class="lead"><a href="/myArticles">Назад</a></p>
    <#else>
        <p class="lead"><a href="allArticles.ftl">Назад</a></p>
    </#if>
    <br>

    <#if article?has_content>
        <h1>${article.title}</h1>
        <br>
        <div>${article.text}</div>
        <br>
        <img src="${article.photo}" width="709" height="350">
        <br>
        <br>
        <div>
            <table>
                <tr>
                    <td><img alt="user_img" src="${author.avatar}" width="50" height="50" class="rounded-circle"></td>

                    <td><strong style="font-size:20px"><a href="/detailUser?id=${author.id}">${article.userNickname}</a></strong></td>
                    <td><small class="text-muted" style="font-size:17px"><em>${article.data}</em></small></td>
                    <td><small class="text-muted" style="font-size:17px">Статья ${article.id}</small></td>
                </tr>
            </table>
        </div>

        <br>

        <#if isComments??>
            <#if comments?has_content>
                <p class="lead">Комментарии:</p>

                <#list comments as comment>
                    <table>
                        <tr>
                            <td><img alt="user_img" src="${comment.user.avatar}" width="50" height="50" class="rounded-circle"></td>
                            <td><strong style="font-size:20px"><a href="/detailUser?id=${comment.user.id}">${comment.user.nickname}</a></strong></td>
                        </tr>
                    </table>
                    <div class="alert alert-dark" role="alert">
                        <div>${comment.text}</div>
                    </div>
                </#list>
            <#else>
                <p class="lead">Нет комментариев!</p>
            </#if>

            <#if userNow?has_content>
                <form action="/detailArticle?id=${article.id}" method="post" novalidate>
                    <p class="lead">Введите комментарий:</p>
                    <p class="lead">
                        <label>
                            <textarea name="comment" placeholder="Комментарий..." class="comment"></textarea>
                        </label><br>
                    </p>
                    <p class="lead"><input type="submit" value="Сохранить"></p>
                    <br>
                </form>
            <#else>
                <p class="lead">Ввойдите в аккаунт, чтобы оставлять комментарии!</p>
            </#if>
        </#if>
    <#else>
        <p class="lead">Что-то пошло не так...</p>
    </#if>

</#macro>