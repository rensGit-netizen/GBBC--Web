<META NAME="firstAuthor" CONTENT="Rens">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
   <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/accountHolder.css" type="text/css"/>
    <script src="js/validation.js" type="text/javascript"></script>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
    <%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <!--    <Hier onze eigen functionaliteiten voor betreffende view-->
    <!--    Dit is dummy text hieronder>-->
    <div class="inlogPanel">
        <form:form class="koppelen" autocomplete="on" method="post" action="/connectAccount" modelAttribute="AddAccountHolderRequest">
            <h2>Rekening koppelen</h2>
            <div class ="headimage"><img class = "picture" src="../../images/moneyChain.jpg" alt="Open Account"></div>
            <label>Te koppelen Rekening</label>
            <form:input type="text"
                        path="iban"
                        class="form-control"
                        onkeyup="isEmpty(this)"
                        placeholder="NL**GBBC**********"/><br>
            <label>Beveiligingscode</label>
            <form:input id="controlCode"
                        type="text"
                        path="code"
                        class="form-control"
                        placeholder="*****"
                        onkeyup="validateCode()"/><br>
            <form:input type="hidden" path="customer_username"  value="${userSession.userName}"/>
            <a href="#" onclick="history.back()"><button type="button" class="btn btn-warning wachtwoordvergeten">Annuleren</button></a>
            <input type="submit" id="btnsubmit" value="Koppelen" class="btn btn-warning submit">
            <span style="margin-bottom: -15px"><p id="warningText">${invalidCode}</p></span><br>
        </form:form>
    </div>
<%--    <button class="btn btn-warning addbutton uitloggen" type="button"> Uitloggen</button>--%>
</section>

<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>