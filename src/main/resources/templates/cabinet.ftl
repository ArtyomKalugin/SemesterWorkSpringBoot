<#include 'base.ftl'>

<#macro title>
<title>Личный кабинет</title>
<link rel="shortcut icon" href="static/img/pancake.jpg" type="image/jpg">
</#macro>

<#macro content>
<br>
<h1>Ваш личный кабинет</h1>
<br>
<table>
    <tr>
        <td><img alt="user_img" src="${user.avatar}" width="150" height="150" class="rounded-circle"></td>
        <td>
            <table>
                <tr>
                    <td>
                        <h2>
                            <strong>${user.nickname}</strong>
                        </h2>
                    </td>

                </tr>

                <tr>
                    <td>
                        <h3>
                            <em>${user.firstName}  ${user.secondName}</em>
                        </h3>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<form action="/cabinet" method="post" novalidate enctype="multipart/form-data">
    <br>
    <p style="font-size:25px">
        Смена аватарки
    </p>

    <p class="lead">
        <input name="avatar" type="file">
    </p>

    <p class="lead">
        <input type="submit" value="Поменять">
    </p>
</form>

<br>
<table>
    <tr>
        <td><p class="lead">Ваша почта: ${user.email}</p></td>
        <td><p class="lead">Ваш id: ${user.id}</p></td>
    </tr>
    <tr>
        <td><p class="lead"><a href="/createRecipe">Добавить рецепт</a></p></td>
        <td><p class="lead"><a href="/myRecipes">Мои рецепты</a></p></td>
    </tr>
    <tr>
        <td><p class="lead"><a href="/createArticle">Добавить статью</a></p></td>
        <td><p class="lead"><a href="/myArticles">Мои статьи</a></p></td>
    </tr>
</table>
<br>
    <tr>
        <td><p class="lead"><a href="/logout">Выйти</a></p></td>
        <td><p class="lead"><a href="/deleteUser">Удалить аккаунт</a></p></td>
    </tr>
<br>

</#macro>
