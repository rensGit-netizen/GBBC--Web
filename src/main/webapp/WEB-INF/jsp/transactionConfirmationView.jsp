<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/transactionConfirm.css" type="text/css"/>

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
    <!--    <Hier onze eigen functionaliteiten voor betreffende view>-->
    <%--@elvariable id="transactionViewModel" type="com.gbbc.webapplication"--%>
    <form:form method="post" action="/transactionConfirmationController" modelAttribute="transactionViewModel"><%--@elvariable id="debitBankAccountMapping" type="com.gbbc.webapplication"--%>
    <%--@elvariable id="transactionViewMapping" type="com.gbbc.webapplication"--%>
        <div class="accountinfo">
            <h3><strong>Bevestig Overboeking</strong></h3>
            <h5>Controleer de onderstaande gegevens</h5>
            <br>
            <div class="overboekinfo">
                <h3>Bedrag: <i>&euro;<fmt:formatNumber type="number" maxFractionDigits="2"
                                                       minFractionDigits="2"
                                                       value="${transactionViewMapping.amount}"/></i></h3>
                <form:hidden path="amount" value="${transactionViewMapping.amount}"/>
                <br>
                <h3>Van</h3>
                <c:forEach items="${debitBankAccountMapping.customer}" var="customer">
                <div class="tekst">${customer.first_name} ${customer.last_name}</div>
                </c:forEach>
                <div class="tekst">${debitBankAccountMapping.iban}</div>
                <form:hidden path="DebitIban" value="${debitBankAccountMapping.iban}"/>
                <br>
                <h3>Naar</h3>
                <div class="tekst">${transactionViewMapping.reciever}</div>
                <div class="tekst">${transactionViewMapping.creditIban}</div>
                <%--@elvariable id="creditBankAccountMapping" type="com.gbbc.webapplication"--%>
                <form:hidden path="creditIban" value="${transactionViewMapping.creditIban}"/>
                <br>
                <h3>Datum van overboeken</h3>
                <div class="tekst"><fmt:formatDate type="date" value="${transactionViewMapping.date}" dateStyle="short"/></div>
                <form:hidden path="date" value="${transactionViewMapping.date}"/>
                <form:hidden path="description" value="${transactionViewMapping.description}"/>

                <br>


            </div>
            <br>
                <input type="submit" value="Overboeken" class="btn btn-warning overboeken">
            <a href="#" onclick="history.back()">
                <button class="btn btn-warning annuleren" type="button">Annuleren</button>
            </a>
        </div>
    </form:form>

</section>


<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>