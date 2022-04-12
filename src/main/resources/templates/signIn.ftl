<#include 'base.ftl'>

<#macro title>
<title>Авторизация</title>
<link rel="shortcut icon" href="static/img/pancake.jpg" type="image/jpg">
</#macro>

<#macro content>
<br>

<h1>Авторизация</h1>

<br>

<form action="/signIn" method="post" novalidate>
    <p class="lead">
        Введите логин:<br>
        <input name="login" type="text"/><br>
    </p>

    <p class="lead">
        Введите пароль:<br>
        <input name="password" type="password"><br>
    </p>

    <p class="lead">
        <input type="checkbox" name="rememberMe"> Запомнить меня
    </p>

    <p class="lead">
        <input type="submit" value="Войти">
    </p>

    <br>
</form>
    <#if err?has_content>
        <div class="alert alert-danger" role="alert">
            ${err}
        </div>
    </#if>

</#macro>
