<#include 'base.ftl'>

<#macro title>
<title>Все рецепты</title>
<link rel="shortcut icon" href="pancake.jpg" type="image/png">

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>

        </script>
</#macro>

<#macro content>
<br>
<h1>Все рецепты</h1>
<br>

    <form>
        <p class="lead" id="1" style="float: left; margin-right: 50px;">
            Поиск по названию:<br>
            <input name="title" id="title" type="text" onkeyup="showResult()"><br>
        </p>
    </form>

    <br>
    <br>
    <br>
    <br>

    <div id="result">
        <#if allRecipes?has_content>
                <#list allRecipes as recipe>
                    <a href="/detailRecipe/${recipe.id}/0">
                        <div class="alert alert-dark" role="alert">
                            <h2>
                                ${recipe.title}
                            </h2>
                            <div>
                                ${recipe.text}
                            </div>
                            <br>
                            <img src="${recipe.photo}" width="665" height="350">
                            <br>
                            <br>
                            <div>
                                <small class="text-muted">
                                    ${recipe.userNickname} ${recipe.data}
                                </small>
                            </div>
                            <div>
                                <small class="text-muted">
                                    Рецепт ${recipe.id}
                                </small>
                            </div>
                        </div>
                    </a>
                </#list>
        <#else>
            <p class="lead">Нет рецептов!</p>
        </#if>
    </div>
</#macro>
