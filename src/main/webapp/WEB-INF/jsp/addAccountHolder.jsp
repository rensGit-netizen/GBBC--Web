<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf" %>
    <link rel="stylesheet" href="css/accountHolder.css" type="text/css"/>
    <script src="js/validation.js" type="text/javascript"></script>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
    <%@include file="../jspf/header.jspf" %>
    <!-- einde navbar -->
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <!--    <Hier onze eigen functionaliteiten voor betreffende view-->
    <!--    Dit is dummy text hieronder>-->
    <div class="inlogPanel">
        <form:form class="koppelen" method="post" action="/addAccountHolder" modelAttribute="AddAccountHolderRequest">
            <h4>Rekeninghouder toevoegen</h4>
            <div class="headimage"><img class="picture" src="../../images/moneyChain.jpg" alt="Open Account"></div>
            <label>Rekeningnummer</label><br>
            <form:input type="text"
                        path="iban"
                        class="form-control"
                        value="${iban}"
            /><br>
            <label>Gebruikersnaam nieuwe Rekeninghouder</label>
            <form:input type="text"
                        path="customer_username"
                        id="customerEmail"
                        class="form-control"
                        placeholder="E-mailadres"
                        onkeyup="validateEmail()"/><br>
            <label>Kies een 5 cijferige beveiligingscode</label>
            <form:input id="controlCode"
                        type="text"
                        path="code"
                        class="form-control"
                        placeholder="*****"
                        onkeyup="validateCode()"/><br>
            <a href="#" onclick="history.back()">
                <button type="button" class="btn btn-warning wachtwoordvergeten">Annuleren</button>
            </a>
            <input type="submit" value="Koppelen" class="btn btn-warning submit">
            <span style="margin-bottom: -15px"><p id="warningText">${invalidCode}</p></span><br>
        </form:form>
    </div>

    <%--    <button class="btn btn-warning addbutton uitloggen" type="button"> Uitloggen</button>--%>
</section>
<script>
</script>
<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf" %>
</div>
</body>