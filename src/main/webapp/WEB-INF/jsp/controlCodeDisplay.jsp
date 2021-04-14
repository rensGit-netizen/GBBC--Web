<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf" %>
    <link rel="stylesheet" href="css/success.css" type="text/css"/>
</head>
<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
    <%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <div class="informationField p">
        <H1>Welcome ${employee.first_name}!</H1>
    </div>
    <!--    <Hier onze eigen functionaliteiten voor betreffende view>-->
    <div class="accountinfo">
        <h5>${terminalstatus.first_name}&nbsp;${terminalstatus.last_name} heeft een pinautomaat aangevraagd</h5>
        <h4><i>Er is een controle code gegenereerd</i></h4>
        <div class="headimage"><img class="picture" src="../../../../images/success.jpg" alt="Succes!"></div>
        <table class="accounttable table table-sm table-hover">
            <tr>
                <th>Soort Rekening</th>
                <td>Gold Bar Banking Co. &nbsp;${terminalstatus.accountType} </td>
            </tr>
            <tr>
                <th>Tenaamstelling</th>
                <td><b>${terminalstatus.title}</b></td>
            </tr>&nbsp;
            <tr>
                <th>Iban</th>
                <td>${terminalstatus.iban}</td>
            </tr>&nbsp;
            <tr>
                <th>Aanvraag datum</th>
                <td>${terminalstatus.requestDate}</td>
            </tr>&nbsp;
            <tr>
                <th>Status van aanvraag</th>
                <td>${terminalstatus.status}</td>
            </tr>&nbsp;
            <tr>
                <th>Behandelingsdatum</th>
                <td>${terminalstatus.responseDate}</td>
            </tr>&nbsp;
            <tr>
                <th>Controle code</th>
                <td><b>${terminalstatus.controlCode}</b></td>
            </tr>&nbsp;
        </table>
        <div>
            <a href="/terminal-requests">
                <button class="btn btn-warning" type="button">Terug naar overzicht</button></a>
            <a href="mailto:${terminalstatus.user_name}?subject=Aanvraag Pinautomaat&body=Beste ${terminalstatus.first_name} ${terminalstatus.last_name},%0d Activeer uw pinautomaat met de volgende Controle Code: ${terminalstatus.controlCode}">
                <button class="btn btn-warning" type="button">Verstuur Controle Code naar aanvrager</button>
            </a>

        </div>
    </div>
</section>

<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf" %>
</div>
</body>