<#include 'base.ftl'>

<#macro title>
<title>Добавление рецепта</title>
<link rel="shortcut icon" href="static/img/pancake.jpg" type="image/png">
</#macro>

<#macro content>
<br>
<h1>Рецепт</h1>
<br>
<form action="/createRecipe" method="post" novalidate enctype="multipart/form-data">
    <p class="lead">
        Введите название:<br>
        <input name="title" type="text" style="width: 710px"><br>
    </p>


    <p class="lead">
        Введите рецепт:<br>
        <label>
            <textarea name="content" placeholder="Рецепт..." class="recipe"></textarea>
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
