<#include "base.ftl">
<html lang="ru">
<#macro title>
    <title>Регистрация</title>
    <link rel="shortcut icon" href="pancake.jpg" type="image/jpg">
</#macro>

<#macro content>
    <script>
        const form  = document.getElementById("form");
        let isValid = true;

        function validFunction() {
            return isValid;
        }

        function showResult(email) {
            const xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
                if (this.readyState === 4 && this.status === 200) {
                    if (this.responseText === "taken") {
                        document.getElementById("error").innerHTML = "Аккаунт с такой почтой уже существует!";
                        isValid = false
                    } else {
                        document.getElementById("error").innerHTML = ""
                        document.getElementById("error").className = "error"
                        isValid = true
                    }
                }
            }
            xmlhttp.open("GET","/checkEmail?email=" + email, true);
            xmlhttp.send();
        }

        const nickname = document.getElementById("nickname");
        nickname.addEventListener("input", function (event) {
            if (nickname.validity.valid) {
                nickname.setCustomValidity("");
            } else {
                nickname.setCustomValidity("Никнейм должен содержать больше 4 символов");
            }
        });

        const password = document.getElementById("password");
        password.addEventListener("input", function (event) {
            if (password.validity.valid) {
                password.setCustomValidity("");
            } else {
                password.setCustomValidity("Пароль должен быть больше 4 символов");
            }
        });

        const email = document.getElementById("mail");
        email.addEventListener("input", function (event) {
            if (email.validity.typeMismatch) {
                email.setCustomValidity("Введите электронную почту!");
            } else {
                email.setCustomValidity("");
            }
        });
    </script>

    <br>

    <h1>Регистрация</h1>
    <br>
    <div id="error" class="error active"></div>
    <br>
    <form action="/signUp" method="post" id="form" onsubmit="return validFunction()">
        <p class="lead">
            Введите никнейм:<br>
            <input name="nickname" type="text" id="nickname" required minlength="5"/><br>
        </p>

        <p class="lead">
            Введите имя:<br>
            <input name="firstName" type="text" required/><br>
        </p>

        <p class="lead">
            Введите фамилию:<br>
            <input name="secondName" type="text" required/><br>
        </p>

        <p class="lead">
            Введите электронную почту:<br>
            <input name="email" type="email" id="mail" onkeyup="showResult(this.value)" required/><br>
        </p>

        <p class="lead">
            Введите пароль:<br>
            <input name="password" type="password" required id="password" minlength="5"><br>
        </p>

        <br>
        <p class="lead">
            <input type="submit" value="Зарегистрироваться" id="button">
        </p>
        <br>
    </form>
</#macro>
</html>
