<#include 'base.ftl'>

<#macro title>
    <title>Статья</title>
    <link rel="shortcut icon" href="pancake.jpg" type="image/png">
</#macro>

<#macro content>
    <br>
    <#if isMyArticle??>
        <p class="lead"><a href="/myArticles">Назад</a></p>
    <#else>
        <p class="lead"><a href="/allArticles">Назад</a></p>
    </#if>
    <br>

    <#if detailArticle?has_content>
        <h1>${detailArticle.title}</h1>
        <br>
        <div>${detailArticle.text}</div>
        <br>
        <img src="${detailArticle.photo}" width="709" height="350">
        <br>
        <br>
        <div>
            <table>
                <tr>
                    <td><img alt="user_img" src="${author.avatar}" width="50" height="50" class="rounded-circle"></td>

                    <td><strong style="font-size:20px"><a href="/detailUser/${author.id}">${detailArticle.userNickname}</a></strong></td>
                    <td><small class="text-muted" style="font-size:17px"><em>${detailArticle.data}</em></small></td>
                    <td><small class="text-muted" style="font-size:17px">Статья ${detailArticle.id}</small></td>
                </tr>
            </table>
        </div>

        <br>

        <#if articleComments?has_content>
            <p class="lead"><strong>Комментарии:</strong></p>

            <#list articleComments as comment>
                <table>
                    <tr>
                        <td><img alt="user_img" src="${comment.user.avatar}" width="50" height="50" class="rounded-circle"></td>
                        <td><strong style="font-size:20px"><a href="/detailUser/${comment.user.id}">${comment.user.nickname}</a></strong></td>
                    </tr>
                </table>
                <div class="alert alert-dark" role="alert">
                    <div>${comment.text}</div>
                </div>
            </#list>
        <#else>
            <p class="lead">Нет комментариев!</p>
        </#if>

        <#if user?has_content>
            <form action="/createArticleComment/${detailArticle.id}/0" method="post" novalidate>
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

    <#else>
        <p class="lead">Что-то пошло не так...</p>
    </#if>

</#macro>
