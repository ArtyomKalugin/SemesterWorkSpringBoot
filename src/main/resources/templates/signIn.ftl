<#include 'base.ftl'>

<#macro title>
<title>Авторизация</title>
<link rel="shortcut icon" href="pancake.jpg" type="image/jpg">
</#macro>

<#macro content>
<br>

<h1>Авторизация</h1>
<br>

<form action="/signIn" method="post" novalidate>
    <p class="lead">
        Введите логин:<br>
        <input name="email" type="text"/><br>
    </p>

    <p class="lead">
        Введите пароль:<br>
        <input name="password" type="password"><br>
    </p>

    <p class="lead">
        <input type="submit" value="Войти">
    </p>

    <br>
</form>
    <#if authenticationFailure?has_content>
        <div class="alert alert-danger" role="alert">
            Неверный логин или пароль!
        </div>
    </#if>

</#macro>
