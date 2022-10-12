<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация KnowTest</title>
    <link rel="stylesheet" href="../css/style.css" media="screen" type="text/css" />
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body>

<!-- top bar -->

<div id="login2">
    <form name="AuthorizationForm" action="/controller" method="post">
        <input type="hidden" name="command" value="login">
        <fieldset class="clearfix">
            <p><span class="fontawesome-user"></span><input id="login" name="login" type="text" value="Логин" onBlur="if(this.value == '') this.value = 'Логин'" onFocus="if(this.value == 'Логин') this.value = ''" required></p>
            <p><span class="fontawesome-lock"></span><input id="password" name="password" type="password"  value="Пароль" onBlur="if(this.value == '') this.value = 'Пароль'" onFocus="if(this.value == 'Пароль') this.value = ''" required></p>
            <p><input type="submit" value="ВОЙТИ"></p>
            <p class="errors" >
                ${wrongLogin}
            </p>
        </fieldset>
    </form>
</div>
</body>
</html>