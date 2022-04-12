<#include 'base.ftl'>

<#macro title>
    <title>Добавление статьи</title>
    <link rel="shortcut icon" href="static/img/pancake.jpg" type="image/png">
</#macro>

<#macro content>
    <br>
    <h1>Статья</h1>
    <br>
    <form action="/createArticle" method="post" novalidate enctype="multipart/form-data">
        <p class="lead">
            Введите название:<br>
            <input name="title" type="text" style="width: 710px"><br>
        </p>


        <p class="lead">
            Введите статью:<br>
            <label>
                <textarea name="content" placeholder="Статья..." class="recipe"></textarea>
            </label><br>
        </p>

        <p class="lead">
            <input name="photo" type="file"><br>
        </p>
        <br>
        <p class="lead"><input type="submit" value="Сохранить"></p>
        <br>
    </form>
</#macro>
