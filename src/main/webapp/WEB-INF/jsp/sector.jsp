<META NAME="firstAuthor" CONTENT="Rens">
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/smesTable.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
<%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <div class="informationField">
        <H1>Welcome ${userSession.first_name}!</H1>
    </div>
    <!--<Hier onze eigen functionaliteiten voor betreffende view>-->
    <div class="sectordatatable">
        <table class="smestable table table-fixed table-sm table-responsive-sm table-hover table-borderless">
            <thead>
            <tr>
                <th scope="col" ro>Sector</th>
                <th scope="col">Gemiddeld saldo</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty(sectors)}">
                <c:forEach var="Sector" items="${sectors}">
                    <tr class="align-right">
                        <td>${Sector.name}</td>
                        <td align="right">&euro;${Sector.avgBalance}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class ="keuzebuttons">
        <h6>Kies hier welk overzicht u wil zien</h6>
        <a href="/smes-customers" method = "get"><button class="btn btn-warning">Alle zakelijke klanten</button></a>
        <a href="/top10" method = "get"><button class="btn btn-warning">(Top 10) Hoogste saldo</button></a>
        <a href="/mostActive" method = "get"><button class="btn btn-warning">(Top 10) Meest actief</button></a>
        <a href="/averageSector" method = "get"><button class="btn btn-warning">Saldo per sector</button></a>
    </div>
</section>
<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>