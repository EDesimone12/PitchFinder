<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 11/01/2021
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>PitchFinder</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="./css/navbar/style_navbar.css" rel="stylesheet" />

    <link href="./css/partita/style_matchCreation.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/partita/js_matchCreation.js"></script>
</head>
<body id="page-top">
<!-- Navigation-->
<%@ include file="/view/navbar/navbar.jsp"%>
<!-- Masthead-->
<header class="masthead">
    <div class="container">
        <div class="masthead-subheading">Benvenuto!</div>
        <div class="masthead-heading text-uppercase">Prenota ora!</div>
    </div>
</header>
<section class="page-section" id="match-section">
    <form action="Creation-Servlet" method="post" id="form-creation" onsubmit="return validateForm()">
        <fieldset>
            <legend> Creazione Partita </legend>
            <input type="hidden" class="code" name="idCampo" value="1002">
            <label for="creation-data" id="creation-label-data">Data partita:</label>
            <input type="date" id="creation-data" name="date" required onblur="validateDate()"><br>
            <label for="creation-timestr" id="creation-label-str">Oario Inizio:</label>
            <input type="time" id="creation-timestr" name="start" min="09:00" max="23:00" required onblur="validateStart()"><br>
            <label for="creation-timeend" id="creation-label-end">Oario Fine:</label>
            <input type="time" id="creation-timeend" name="end" min="09:00" max="23:00" required onblur="validateEnd()"><br>
            <label for="creation-player" id="creation-label-player">Numero giocatori:</label>
            <input type="number" id="creation-player" name="maxGiocatori" min="1" max="3" value="1" required onchange="validateNPlayer()"><br>
            <label for="nome" id="creation-label-nameG1">Nome:</label>
            <input type="text" id="nome" name="nameG1" required onkeyup="validateName()" >
            <label for="cognome" id="creation-label-surnameG1"> Cognome: </label>
            <input type="text" id="cognome" name="surnameG1" required onkeyup="validateSurname()" ><br>
        </fieldset>
    </form>
</section>

<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Core theme JS-->
<script src="./js/homepage/js_homepage.js"></script>
</body>
</html>
