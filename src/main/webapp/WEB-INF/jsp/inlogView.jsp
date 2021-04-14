<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/inlogview.css" type="text/css">
    <script src="js/validation.js" type="text/javascript"></script>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
<%@include file="../jspf/header.jspf" %>
</div>
<%-- section> = een sectie in de pagina--%>
<section>
    <!--    <Hier onze eigen functionaliteiten voor betreffende view-->
    <div class="inlogPanel">
        <%--@elvariable id="customer" type="com"--%>
        <form:form class="gebruiker" autocomplete="on" method="post" modelAttribute="customer" action="/loginControle"><%--@elvariable id="invalidLogin" type="com"--%>
            <h2>Inloggen bij GBBC</h2>
            <div class = "headimage"><img class = "picture" src="../../images/goldBarLogInLogo.jpg" alt="Open Account"></div>
            <label>Gebruikersnaam</label><br>
            <form:input type="text"
                        path="userName"
                        id="customerEmail"
                        class="form-control"
                        placeholder="E-mailadres"
                        onkeyup="validateEmail()"/><br>
            <label>Wachtwoord</label><br>
            <form:input type="password"
                        class="form-control"
                        id="myPassword"
                        placeholder="Wachtwoord"
                        onfocusout="isEmpty(this)"
                        path="password"/><br>
            <input type="checkbox" onclick="seePassword()" id="checkBox"><label style="padding-left: 20px">Toon wachtwoord</label><br><br>
            <button onclick="noMoneyMore()" class="btn btn-warning wachtwoordvergeten">Wachtwoord vergeten</button>
            <input type="submit" value="Login" class="btn btn-warning submit">
            <span style="margin-bottom: -15px"><p id="warningText">${invalidLogin}</p></span><br>
        </form:form>
    </div>
</section>



<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
<script>
    function noMoneyMore(){
        alert("Sorry, geld is nu van de bank");
    }
</script>
<script>
    function seePassword(){
        var x = document.getElementById("myPassword");

        if (document.getElementById("checkBox").checked){
            x.type = "text";
        }
        else {
            x.type = "password";
        }
    }
</script>
</body>