<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <@title></@title>

    <style>
        .srcshot {
            position: relative;
            text-align: center;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .srcshot::before {
            content: "Еда";
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            padding: 6px 6px 4px;
            color: white;
            background: black;
            font-size: 18px;
        }
        .srcshot-dark::before {
            color: #fff;
            background: #616161;
        }
        .srcshot-item {
            display: inline-block;
            border: 3px solid black;
            box-shadow: 0 0 10px #9e9e9e;
            border-bottom-left-radius: 4px;
            border-bottom-right-radius: 4px;
            max-width: 100%;
            height: 1000px;
        }
        .srcshot-item-dark {
            border: 5px solid #616161;
        }
        .treiler {
            text-align: center;
            color: black;
        }
        body {
            background: white url(/food.jpeg);
            background-attachment: fixed;
            background-repeat: repeat-x;
            background-size: 100%;
        }
        .mainer {
            border: 1px solid white;
            margin: 0px 350px 10px 350px;
            background-color: white;
        }
        .recipe {
            width: 710px;
            height: 400px;
        }

        .comment {
            width: 710px;
            height: 100px;
        }

        table {
            border-collapse: separate;
        }
        td {
            padding-right: 50px;
        }
        .error {
            font-size: 25px;
            color: #900;
        }

        .error.active {
            font-size: 25px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="/info"><img alt="food_img" src="/pancake.jpg" width="50" height="50" class="rounded-circle" style=""></a>

    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="/info">Об этом сайте</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/allRecipes">Рецепты</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="allArticles.ftl">Статьи</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/allUsers">Все пользователи</a>
        </li>
    </ul>

    <#if user?has_content>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <img alt="user_img" src="${user.avatar}" width="50" height="50" class="rounded-circle" style="">
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/account"><strong>${user.nickname}</strong></a>
            </li>
        </ul>
    <#else>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/signIn">Войти</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/signUp">Регистрация</a>
            </li>
        </ul>
    </#if>

</nav>

<div class="mainer">
    <main role="main" class="container">
        <@content></@content>
    </main>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
