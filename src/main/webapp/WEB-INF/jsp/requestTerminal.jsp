<META NAME="firstAuthor" CONTENT="Rens">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/success.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
    <%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <div class="informationField pagination justify-content-center">
        <h1 >Welkom bij GBBC ${customerName}!</h1>
    </div>
    <!--    <Hier onze eigen functionaliteiten voor betreffende view>-->
    <div class = "accountinfo">
        <h4>Er is een pinautomaat voor u aangevraagd!</h4>
        <div class = "headimage"><img class = "picture" src="../../../../images/pinterminal.png" alt="Succes!"></div>
        <table class = "accounttable table table-sm table-hover">
            <tr>
                <th>Soort rekening</th>
                <td>GBBC ${terminalstatus.accountType}</td>
            </tr>
            <tr>
                <th>IBAN van rekening</th>
                <td> ${terminalstatus.iban}</td>
            </tr>
            <tr>
                <th>Naam aanvrager</th>
                <td> ${terminalstatus.first_name}&nbsp;${terminalstatus.last_name}</td>
            </tr>
            <tr>
                <th>Aanvraag datum</th>
                <td> ${terminalstatus.requestDate}</td>
            </tr>
            <tr>
                <th>Status van uw aanvraag</th>
                <td> ${terminalstatus.status}</td>
            </tr>
            <tr>
                <th></th>
                <td>U krijgt zo snel mogelijk bericht!</td>
            </tr>

        </table>
        <br>
        <div class="text-center">
            <form:form method="get" action="/goToAccountOverView">
                <input type="submit" value="Terug naar mijn accounts!" class="btn btn-warning submit">
            </form:form>
        </div></div>
</section>
<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>