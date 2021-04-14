<%--@elvariable id="userSession" type="com.gbbc.webapplication"--%>
<%--@elvariable id="transactionBankAccount" type="com.gbbc.webapplication"--%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@ include file="../jspf/head.jspf" %>

    <link rel="stylesheet" href="css/transactionView.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
    <%@ include file="../jspf/header.jspf" %>
    <!-- einde navbar -->
</div>

<%-- <section> = een sectie in de pagina--%>
<section>


    <%--Hier onze eigen functionaliteiten voor betreffende view--%>
    <div class="formulier">

        <%--@elvariable id="transaction" type="com.gbbc.webapplication"--%>
        <%--@elvariable id="transactionViewModel" type="com.gbbc.webapplication"--%>
        <form:form class="transactie" autocomplete="on" method="post" action="/transactionViewMapping"
                   modelAttribute="transactionViewModel" id="registrationForm">
            <!-- TODO: waardes voor 'class', 'action' enz. nog controleren/aanpassen
            TODO: waarde voor 'modelAttribute' nog controleren/aanpassen -->

            <h4>Overboeking</h4>
            <br>

            <dt class="rekening">
                <div class="rekeninghouderEnSaldo">
                    <h5 class="rekeninghouder"> ${userSession.first_name} ${userSession.last_name} </h5>
                    <h5 class="saldo"> &euro; <fmt:formatNumber type="number" maxFractionDigits="2"
                                                                minFractionDigits="2"
                                                                value="${transactionBankAccount.saldo}"/></h5>
                </div>
                <h6> GBBC ${transactionBankAccount.type} rekening</h6>
                <h6> ${transactionBankAccount.iban}</h6>
            </dt>


            <label class="form-label">Bedrag</label> <br>
            <form:input class="form-control" path="amount" placeholder="0.00" id="transactionAmount" onkeyup="validatetransactionAmount()"/>
            <c:if test="${not empty sellSoul}">
                ${sellSoul}
            </c:if>
            <br>

            <label class="form-label">Begunstigde</label><br/>
            <form:input class="form-control" path="reciever"/>
            <br>

            <label class="form-label">Rekeningnummer (IBAN)</label><br/>
            <form:input class="form-control" path="creditIban" onfocusout="isEmpty(this)"/>
            <c:if test="${not empty doesntExist}">
                ${doesntExist}
            </c:if>
            <br>

            <label class="form-label">Omschrijving of betalingskenmerk</label><br/>
            <form:input class="form-control" path="description"/>
            <br><br>

            <form:hidden path="DebitIban" value="${transactionBankAccount.iban}"/>
            <form:hidden path="bankId" value="${transactionBankAccount.id}"/>
            <form:hidden path="totalAmount" value="${transactionBankAccount.saldo}"/>

            <button class="submit" type="submit" id="btnsubmit">Bevestigen</button>
                    <a href="#" onclick="history.back()">
                        <button class="btn btn-warning annuleren" type="button">Annuleren</button>
                    </a>
        </form:form>
    </div>


</section>


<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf" %>
</div>
<script>
    function anuleerScript() {
        document.getElementById("anuleerFunctie").submit();
    }
</script>
</body>