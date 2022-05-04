<#include 'base.ftl'>

<#macro title>
<title>Кабинет пользователя</title>
<link rel="shortcut icon" href="/pancake.jpg" type="image/png">
</#macro>

<#macro content>
    <br>
    <p class="lead"><a href="/allUsers">Назад</a></p>

<#if detailUser?has_content>
    <br>
    <h1>Страница пользователя</h1>
    <br>
    <table>
        <tr>
            <td><img alt="user_img" src="${detailUser.avatar}" width="150" height="150" class="rounded-circle"></td>
            <td>
                <table>
                    <tr>
                        <td>
                            <h2>
                                <strong>${detailUser.nickname}</strong>
                            </h2>
                        </td>

                    </tr>

                    <tr>
                        <td>
                            <h3>
                                <em>${detailUser.firstName}  ${detailUser.secondName}</em>
                            </h3>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <br>
    <p class="lead">Всего рецептов: ${count}</p>
    <p class="lead">Почта: ${detailUser.email}</p>
<#else>
    <br>
    <p class="lead"><em>Что-то пошло не так...</em></p>
    <br>
</#if>

</#macro>
