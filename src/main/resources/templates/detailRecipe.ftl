<#include 'base.ftl'>

<#macro title>
    <title>Рецепт</title>
    <link rel="shortcut icon" href="pancake.jpg" type="image/png">
</#macro>

<#macro content>
    <br>
    <#if isMyRecipe??>
        <p class="lead"><a href="/myRecipes">Назад</a></p>
    <#else>
        <p class="lead"><a href="/allRecipes">Назад</a></p>
    </#if>
    <br>

    <#if detailRecipe?has_content>
        <h1>${detailRecipe.title}</h1>
        <br>
        <div>${detailRecipe.text}</div>
        <br>
        <img src="${detailRecipe.photo}" width="709" height="350">
        <br>
        <br>
        <div>
            <table>
                <tr>
                    <td><img alt="user_img" src="${author.avatar}" width="50" height="50" class="rounded-circle"></td>

                    <td><strong style="font-size:20px"><a href="/detailUser/${author.id}">${detailRecipe.userNickname}</a></strong></td>
                    <td><small class="text-muted" style="font-size:17px"><em>${detailRecipe.data}</em></small></td>
                    <td><small class="text-muted" style="font-size:17px">Рецепт ${detailRecipe.id}</small></td>
                </tr>
            </table>
        </div>

        <br>

        <#if recipeComments?has_content>
                <p class="lead"><strong>Комментарии:</strong></p>

                <#list recipeComments as comment>
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
                <form action="/createRecipeComment/${detailRecipe.id}/0" method="post" novalidate>
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
