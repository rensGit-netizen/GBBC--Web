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
<%@include file="../jspf/headerEmployee.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <div class="informationField">
        <H1>Welcome ${userSession.first_name}!</H1>
    </div>
    <!--<Hier onze eigen functionaliteiten voor betreffende view>-->
    <div class="datatable">
        <table class="smestable table table-fixed table-sm table-responsive-sm table-hover table-borderless">
            <thead>
            <tr>
                <th scope="col" ro>1e Rekeninghouder</th>
                <th scope="col">IBAN</th>
                <th scope="col">Saldo</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty(accounts)}">
                <c:forEach var="Smes" items="${accounts}">
                    <tr class="align-right">
                        <td>${Smes.first_name}&nbsp;${Smes.last_name}</td>
                        <td>${Smes.iban}</td>
                        <td align="right">&euro;${Smes.saldo}</td>
                        <td align="right"><a href="mailto:${Smes.user_name}?subject=Niet te weigeren aanbod!&body=Beste ${Smes.first_name} ${Smes.last_name},"><button class="btn btn-warning" type="button">Contact</button></a></td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</section>
<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>