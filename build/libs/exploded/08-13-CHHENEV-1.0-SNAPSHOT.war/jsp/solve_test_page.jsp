<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KnowTest</title>
    <link rel="stylesheet" href="../css/solveTest.css">
    <link rel="icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="http://vladmaxi.net/favicon.ico" type="image/x-icon">
</head>
<body onload="onLoad()">
<header>
    <div id="logo" onclick="slowScroll('#top')">
        <span>KnowTest</span>
    </div>
    <div id="about">
        <!--<a href="#" title="Ваши тесты" onclick="slowScroll('#main')">Ваши тесты</a>-->
        <!--<li><a href="#" title="Ваши тесты" class="down" onclick="slowScroll('#aboutus')">Ваши тесты</a>
            <ul class="submenu">
                <li><a href="#" onclick="slowScroll('#aboutus1')">Пройденные успешно</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus2')">Пройденные неуспешно</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus3')">Неоцененные</a></li>
                <li><a href="#" onclick="slowScroll('#aboutus4')">Не начатые</a></li>
            </ul>
        </li>-->
        <a href="#" title="Пройти тест" onclick="slowScroll('#contact')" id="button">
            <aa>Пройти тест</aa>
        </a>

    </div>
</header>

<div id="top">
    <h1>KnowTest</h1>
    <h3><p id="top3">Автоматизированная система тестирования знаний РГРТУ</p></h3>
    <h5><p id="auth">Вы авторизованы как студент</p>
        <p id="authInfo">${sessionScope.sur_name} ${sessionScope.first_name} ${sessionScope.third_name}</p>
        <form class="logout-form tabletop-offset" action="/controller" method="get">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="Выйти">
        </form>
    </h5>

</div>

<div id="aboutus">
    <div class="intro">
        <h2>Тест</h2>
        <span>Основная информация</span>
    </div>
    <div class="text">

        <p>Описание теста:
            <br>
            <textarea name="description" cols="70" rows="2">${sessionScope.test_description2}</textarea>
        </p>
        <br>


        <p>Задание:</p>
        <p>
            <textarea name="task" cols="70" rows="3">${sessionScope.test_task2}</textarea>
            <%--<form name="AddUser" action="/controller" method="post">--%>
            <%--<input type="hidden" name="command" value="student_load_test_page">--%>
            <input type="button" value="Начать прохождение теста" onclick="slowScroll('#contact')">
            <%--</form>--%>
        </p>
    </div>
</div>

<div id="contact">
    <div class="intro">
        <h2>Пройти тест </h2>
        <span></span>
    </div>
    <div>
        <form name="AddUser" action="/controller" method="post">
            <input type="hidden" name="command" value="student_solve_test">
            <p>
                Вопрос
                <br>
                <textarea name="current_question" cols="70" rows="3">${sessionScope.currentQuestion}</textarea>
            </p>

            <br>


            <%--<form>--%>
            <p></p>
            <input type="radio" id="radioButton1" value="1" name="student_answer">
            <label for="radioButton1">${sessionScope.currentAnswer1}</label><br>
            <input type="radio" id="radioButton2" value="2" name="student_answer">
            <label for="radioButton2">${sessionScope.currentAnswer2}</label><br>
            <input type="radio" id="radioButton3" value="3" name="student_answer">
            <label for="radioButton3">${sessionScope.currentAnswer3}</label><br>
            <input type="radio" id="radioButton4" value="4" name="student_answer">
            <label for="radioButton4">${sessionScope.currentAnswer4}</label><br>
            <%--</form>--%>


            <input type="submit" value="Ответить" onclick="onLoad2()">



        </form>
    </div>
    <div>&nbsp</div>
    <div>
        <canvas id="myCanvas" width="300" height="300"
                style="background-color:#eee; border:1px solid #ccc;" value="${sessionScope.currentQuestion}">

        </canvas>
    </div>

    <div>
        <form name="AddUser" action="/controller" method="post">
            <input type="hidden" name="command" value="student_page_add_solution">
            <input type="submit" value="Завершить тест">
            <p>
                <textarea name="from" hidden> из </textarea>
            </p>

            <p>
                <textarea name="studentHadAnswered" hidden> Студент ответил верно на </textarea>
            </p>
            <p>
                <textarea name="firstSection" hidden> вопросов раздела 'Теоретические вопросы', </textarea>
            </p>
            <p>
                <textarea name="secondSection" hidden> вопросов раздела 'Вывод элементов', </textarea>
            </p>
            <p>
                <textarea name="thirdSection" hidden> вопросов раздела 'Удаление элементов', </textarea>
            </p>
            <p>
                <textarea name="fourthSection" hidden> вопросов раздела 'Получение элементов и доступ к ним'.</textarea>
            </p>
        </form>
        <div id="mouthValue" hidden>${sessionScope.mouthValue} </div>
        <div id="eyeValue" hidden>${sessionScope.eyeValue} </div>
        <div id="eyeBrowsValue" hidden>${sessionScope.eyeBrowsValue} </div>
        <div id="noseValue" hidden>${sessionScope.noseValue} </div>
        <div id="hairValue" hidden>${sessionScope.hairValue} </div>
        <div id="headValue" hidden>${sessionScope.headValue} </div>



        <%--session.setAttribute("from", " из ");
        session.setAttribute("studentHadAnswered", "Студент ответил верно на ");
        session.setAttribute("firstSection", " вопросов раздела 'Теоретические вопросы', ");
        session.setAttribute("secondSection", " вопросов раздела 'Вывод элементов', ");
        session.setAttribute("thirdSection", " вопросов раздела 'Удаление элементов', ");
        session.setAttribute("fourthSection", " вопросов раздела 'Получение элементов и доступ к ним'.");--%>


    </div>
    <div></div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>


    let canvas = document.getElementById("myCanvas"),
        context = canvas.getContext("2d"),
        mouthValue = document.getElementById("mouthValue").innerText,
        eyeValue = document.getElementById("eyeValue").innerText,
        eyeBrowsValue = document.getElementById("eyeBrowsValue").innerText,
        noseValue = document.getElementById("noseValue").innerText,
        hairValue = document.getElementById("hairValue").innerText,
        headValue = document.getElementById("headValue").innerText;

    mouthValue = parseInt(mouthValue);
    eyeValue = parseInt(eyeValue);
    eyeBrowsValue = parseInt(eyeBrowsValue);
    noseValue = parseInt(noseValue);
    hairValue = parseInt(hairValue);
    headValue = parseInt(headValue);

    //head
    context.beginPath();
    context.arc(150, 150, 100, 0, Math.PI * 2, false);
    if (headValue === 50) {
        context.fillStyle = "#FFFF00";
    } else if (headValue <= 10 && headValue >= -20) {
        context.fillStyle = "#fd0000";
    } else if (headValue <= 20 && headValue > 10) {
        context.fillStyle = "#ff4848";
    } else if (headValue <= 30 && headValue > 20) {
        context.fillStyle = "#f57145";
    } else if (headValue <= 40 && headValue > 30) {
        context.fillStyle = "#ff9a00";
    } else if (headValue <= 50 && headValue > 40) {
        context.fillStyle = "#ffa500";
    } else if (headValue <= 60 && headValue > 50) {
        context.fillStyle = "#edff00";
    } else if (headValue <= 70 && headValue > 60) {
        context.fillStyle = "#ebf300";
    } else if (headValue <= 80 && headValue > 70) {
        context.fillStyle = "#ddff00";
    } else if (headValue <= 90 && headValue > 80) {
        context.fillStyle = "#b2ff28";
    } else if (headValue <= 1000 && headValue > 90) {
        context.fillStyle = "#56ff00";
    }
    //context.fillStyle = "#FFFF00";
    context.fill();
    context.closePath();
    context.stroke();

    if (eyeValue < 25) {
        //eyes left
        context.beginPath();
        context.arc(55, 120 + eyeValue / 10, 15, 0, 2 * Math.PI);
        //context.fillStyle = "#333333";
        //context.fill();
        context.closePath();
        context.stroke();
    }
     else if (eyeValue < 50 && eyeValue >= 25){
        //eyes left
        context.beginPath();
        context.arc(70 + eyeValue / 10, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(190, 120 - eyeValue / 10, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();
    } else if (eyeValue === 50){
        //eyes left
        context.beginPath();
        context.arc(120, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(180, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();
    } else if (eyeValue < 75 && eyeValue > 50){
        //eyes left
        context.beginPath();
        context.arc(120, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(180, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        context.beginPath();
        context.arc(120, 120 - eyeValue / 10, 5, 0, 2 * Math.PI);
        context.fillStyle = "#333333";
        context.fill();
        context.closePath();
        context.stroke();
    } else if (eyeValue >= 75){
        //eyes left
        context.beginPath();
        context.arc(120, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        //eyes right
        context.beginPath();
        context.arc(180, 120, 15, 0, 2 * Math.PI);
        context.closePath();
        context.stroke();

        context.beginPath();
        context.arc(120, 120, 5, 0, 2 * Math.PI);
        context.fillStyle = "#333333";
        context.fill();
        context.closePath();
        context.stroke();

        context.beginPath();
        context.arc(180, 120, 5, 0, 2 * Math.PI);
        context.fillStyle = "#333333";
        context.fill();
        context.closePath();
        context.stroke();
    }

    //mouth
    context.beginPath();
    context.moveTo(120, 205);
    context.quadraticCurveTo(150, 155 + mouthValue, 180, 205);
    context.closePath();
    context.fillStyle = "red";
    context.fill();
    context.lineWidth = 1;
    context.strokeStyle = "black";
    context.stroke();


    //eyebrows left
    context.beginPath();
    context.moveTo(105, 85 + eyeBrowsValue / 10);
    context.lineTo(130, 95 - eyeBrowsValue / 10);
    context.closePath();
    context.stroke();

    //eyebrows right
    context.beginPath();
    context.moveTo(168, 95 - eyeBrowsValue / 10);
    context.lineTo(193, 85 + eyeBrowsValue / 10);
    context.closePath();
    context.stroke();


    if (noseValue <= 15) {

    } else if (noseValue <= 30 && noseValue > 15) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(140, 170, 10, 0, Math.PI / 8, true);
        context.closePath();
        context.stroke();
    } else if (noseValue <= 40 && noseValue > 30) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(150, 170, 10, 0, Math.PI / 4, true);
        context.closePath();
        context.stroke();
    } else if (noseValue < 50 && noseValue > 40) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(145, 170, 10, 0, Math.PI / 2, true);
        context.closePath();
        context.stroke();
    } else if (noseValue === 50) {
        //nose
        context.beginPath();
        //context.moveTo(140, 160);
        context.arc(150, 170, 10, 0, Math.PI / 2, true);
        context.closePath();
        context.stroke();
    } else if (noseValue > 50 && noseValue <= 70) {
        //nose
        context.beginPath();
        context.arc(145, 170, 10, 0, Math.PI, true);
        context.closePath();
        context.stroke();
    } else if (noseValue > 70 && noseValue <= 90) {
        //nose
        context.beginPath();
        context.arc(147, 170, 10, 0, Math.PI, true);
        context.closePath();
        context.stroke();
    } else if (noseValue > 90) {
        //nose
        context.beginPath();
        context.arc(150, 170, 10, 0, Math.PI, true);
        context.closePath();
        context.stroke();
    }

    if (hairValue < 10) {
        //hair
        context.beginPath();
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.closePath();
        context.stroke();
    } else if (hairValue < 25 && hairValue >= 10) {
        //hair
        context.beginPath();
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.closePath();
        context.stroke();
    }else if (hairValue < 50 && hairValue >= 25) {
        //hair
        context.beginPath();
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.closePath();
        context.stroke();
    } else if (hairValue === 50){
        //hair
        context.beginPath();
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.closePath();
        context.stroke();
    } else if (hairValue < 70 && hairValue >= 50) {
        //hair
        context.beginPath();
        context.moveTo(116, 57);
        context.lineTo(109, 37);
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.moveTo(184, 57);
        context.lineTo(190, 37);
        context.closePath();
        context.stroke();
    }else if (hairValue < 90 && hairValue >= 70) {
        //hair
        context.beginPath();
        context.moveTo(108, 60);
        context.lineTo(100, 41);
        context.moveTo(116, 57);
        context.lineTo(109, 37);
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.moveTo(184, 57);
        context.lineTo(190, 37);
        context.moveTo(192, 60);
        context.lineTo(200, 41);
        context.closePath();
        context.stroke();
    } else if (hairValue >= 90) {
        //hair
        context.beginPath();
        context.moveTo(100, 62);
        context.lineTo(90,  45);
        context.moveTo(108, 60);
        context.lineTo(100, 41);
        context.moveTo(116, 57);
        context.lineTo(109, 37);
        context.moveTo(125, 54);
        context.lineTo(120, 35);
        context.moveTo(134, 52);
        context.lineTo(130, 33);
        context.moveTo(142, 51);
        context.lineTo(140, 31);
        context.moveTo(150, 50);
        context.lineTo(150, 30);
        context.moveTo(158, 51);
        context.lineTo(160, 31);
        context.moveTo(166, 52);
        context.lineTo(170, 33);
        context.moveTo(175, 54);
        context.lineTo(180, 35);
        context.moveTo(184, 57);
        context.lineTo(190, 37);
        context.moveTo(192, 60);
        context.lineTo(200, 41);
        context.moveTo(200, 62);
        context.lineTo(210, 45);
        context.closePath();
        context.stroke();
    }

</script>
<script>
    function slowScroll(id) {
        $('html,body').animate({
            scrollTop: $(id).offset().top
        }, 500)
    }

    function onLoad2() {
        localStorage.setItem('int', '1');
    }

    function onLoad() {
        if (localStorage.getItem('int') === '1') {
            slowScroll('#contact');

        }
        localStorage.setItem('int', '0');

    }

    $(document).on("scroll", function () {
        if ($(window).scrollTop() === 0)
            $("header").removeClass("fixed");
        else {
            $("header").attr("class", "fixed");
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
                <%--<li><a href="#" onclick="slowScroll('#aboutus')">Ваши тесты</a></li>--%>
                <li><a href="#" onclick="slowScroll('#contact')">Пройти тест</a></li>
            </ul>
        </nav>
    </div>
</footer>

</html>
