<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KnowTest</title>
    <link rel="stylesheet" href="../css/addTest.css">
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body onload="onLoad()">
<header>
    <div id="logo" onclick="slowScroll('#top')">
        <span>KnowTest</span>
    </div>
    <div id="about">
        <a href="#" title="Создать тест" onclick="slowScroll('#contact')"  id="button"><aa>Создать тест</aa></a>
    </div>
</header>

<div id="top">
    <h1>KnowTest</h1>
    <h3><p id = "top3">Автоматизированная система тестирования знаний РГРТУ</p></h3>
    <h5><p id = "auth">Вы авторизованы как преподаватель</p><p id = "authInfo">${sessionScope.sur_name} ${sessionScope.first_name} ${sessionScope.third_name}</p>
        <form class="logout-form tabletop-offset" action="/controller" method="get">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="Выйти">
        </form>
    </h5>
</div>

<form name="AddQuestion" action="/controller" method="post">
    <input type="hidden" name="command" value="teacher_page_create_question">
<div id="contact">

  <form>
    <p>Тематика вопроса:</p>
    <input name="question_type" type="radio" id="radioButton1" value="1">
    <label for="radioButton1">Общий теоретический вопрос</label><br>
    <input name="question_type" type="radio" id="radioButton2" value="2">
    <label for="radioButton2">Вопрос о выводе элементов словаря</label><br>
    <input name="question_type" type="radio" id="radioButton3" value="3">
    <label for="radioButton3">Вопрос об удалении элементов словаря</label><br>
    <input name="question_type" type="radio" id="radioButton4" value="4">
    <label for="radioButton4">Вопрос о получении элементов и доступе к ним</label><br>
  </form>

  <br>

  <div>
    <p>
      Максимальный балл:

      <select name="question_max_point">
        <option value="1">1 балл
        <option value="2">2 балла
        <option value="3">3 балла
        <option value="4">4 балла
      </select>
    </p>
    <p>
      Значимость вопроса в курсе:
      <select name="question_weight">
        <option value="10">10%
        <option value="20">20%
        <option value="30">30%
        <option value="40">40%
        <option value="50">50%
        <option value="60">60%
        <option value="70">70%
        <option value="80">80%
        <option value="90">90%
        <option value="100">100%
      </select>
    </p>

  </div>
  <br><br><br>
  <p>
    Текст вопроса:
    <br>
    <textarea name="question_text" cols="70" rows="3"></textarea>
  </p>
  <br>
  <p>
    Первый ответ:
    <br>
    <input type="text" size="75" name="answer_text"><br>
    <select name="answer_condition">
      <option value="0">Неверный ответ
      <option value="1">Верный ответ
    </select>
  </p>
  <br>
  <p>
    Второй ответ:
    <br>
    <input type="text" size="75" name="answer_text2"><br>
    <select name="answer_condition2">
        <option value="0">Неверный ответ
        <option value="1">Верный ответ
    </select>
  </p>
  <br>
  <p>
    Третий ответ:
    <br>
    <input type="text" size="75" name="answer_text3"><br>
    <select name="answer_condition3">
      <option value="0">Неверный ответ
      <option value="1">Верный ответ
    </select>
  </p>
  <br>
  <p>
    Четвертый ответ:
    <br>
    <input type="text" size="75" name="answer_text4"><br>
    <select name="answer_condition4">
      <option value="0">Неверный ответ
      <option value="1">Верный ответ
    </select>
  </p>
  <br>

<input type="submit" value="Добавить вопрос">

</div></form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
    function slowScroll(id){
        $('html,body').animate({
            scrollTop: $(id).offset().top
        }, 500);

    }

    function onLoad2(){
        localStorage.setItem('int', '1');
    }

    function onLoad(){
        if (localStorage.getItem('int') === '1'  ) {
            slowScroll('#contact2');
            //localStorage.setItem('int', '0');
        }
            localStorage.setItem('int', '0');

    }

    $(document).on("scroll", function () {
        if ($(window).scrollTop() === 0)
            $("header").removeClass("fixed");
        else {
            $("header").attr("class","fixed");
        }
    });
</script>
</body>

<footer class="footer">
    <div class="container grid grid-3">
        <div class="text-center">
            <h1>KnowTest.rsreu</h1>
            <p>Copyright &copy; 2022</p>
        </div>
        <div></div>
        <nav class="text-center">
            <ul>
                <li><a href="#" onclick="slowScroll('#top')">KnowTest</a></li>
                <!--<li><a href="#" onclick="slowScroll('#main')">Ваши тесты</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus')">Студенты</a></li>-->
            </ul>
        </nav>
    </div>
</footer>

</html>
