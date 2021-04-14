<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf" %>
    <link rel="stylesheet" href="css/transactionSucceeded.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
<%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <br/>
    <%--Hier onze eigen functionaliteiten voor betreffende view
    Dit is dummy text hieronder--%>
    <section>
        <!--    <Hier onze eigen functionaliteiten voor betreffende view>-->
        <div class="transactioninfo">
            <h3><strong>Je hebt betaald!</strong></h3>
            <div class="headimage"><img class="picture" src="../../images/vrouwVliegendGeld.jpg" alt="Gelukt!"></div>
            <%--@elvariable id="account" type="com.gbbc.webapplication"--%>
            <form:form method="post" onclick="submit();" action="/overviewViewFormHandler" modelAttribute="account">
                <a href="#"><%--@elvariable id="bankIban" type="com.gbbc.webapplication"--%>
                    <form:hidden path="iban" value="${bankIban}"/>
                    <button class="btn btn-warning overzicht" type="button">Overzicht</button>
                </a>
            </form:form>
        </div>
    </section>
</section>


<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>