<META NAME="firstAuthor" CONTENT="Ismael">
<META NAME="secondAuthor" CONTENT="Rens">
<%--@elvariable id="customerSession" type="com.gbbc.webapplication.beans.Customer"--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad--%>
    <title>GBBC - Uw Rekeningen</title>
    <%@include file="../jspf/head.jspf" %>
    <link rel="stylesheet" href="css/accountOverviewView.css" type="text/css"/>
    <link rel="stylesheet" href="css/infoBox.css" type="text/css"/>
</head>
<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
    <%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <div class="informationField">
        <c:if test="${not empty userSession}">
            <H1>Welcome ${userSession.first_name} !</H1>
        </c:if>
    </div>
    <c:if test="${not empty infopanel}">
        <div class="callout">
            <div class="callout-header">Gelukt!</div>
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            <div class="callout-container">
                <p>${infopanel}</p>
            </div>
        </div>
    </c:if>
    <%--Hier onze eigen functionaliteiten voor betreffende view--%>
    <!--        Hier onze eigen functionaliteiten voor betreffende view -->
    <h2 class="overzicht"><strong>Overzicht</strong></h2>
    <div class="bankrekeningoverzicht" onload="window.location.reload()">
        <dl class="rekeninglijst">
            <%--@elvariable id="userSession" type="com"--%>
            <c:if test="${not empty userSession.bankAccount}">
                <c:forEach items="${userSession.bankAccount}" var="bankaccount">
                    <%--@elvariable id="account" type="com"--%>
                    <form:form method="post" onclick="submit();" action="/overviewViewFormHandler"
                               modelAttribute="account" id="formToSend">
                        <a href="#">
                            <!--MOET DOORVERWIJZEN NAAR het rekeningoverzicht ("/account-statement")-->
                            <form:hidden path="id" value="${bankaccount.id}"/>
                            <form:hidden path="iban" value="${bankaccount.iban}"/>
                            <dt class="rekening">
                                <div class="typeEnSaldo">
                                    <c:if test="${not empty bankaccount.type}">
                                        <h4 class="type"> GBBC-${bankaccount.type} </h4>
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${bankaccount.saldo<0}">
                                            <h4 class="saldo" style="color: darkred">&euro;<fmt:formatNumber
                                                    value="${bankaccount.saldo}" type="number" minFractionDigits="2"
                                                    maxFractionDigits="2"/></h4>
                                        </c:when>
                                        <c:otherwise>
                                            <h4 class="saldo">&euro;<fmt:formatNumber
                                                    value="${bankaccount.saldo}" type="number" minFractionDigits="2"
                                                    maxFractionDigits="2"/></h4>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <c:if test="${not empty bankaccount.title}">
                                    ${bankaccount.title}<br>
                                </c:if>
                                <c:forEach items="${bankaccount.customer}" var="customerName">
                                    ${customerName.first_name} ${customerName.last_name}<br>
                                </c:forEach>
                                    ${bankaccount.iban}<br>
                            </dt>
                            <br>
                        </a>
                    </form:form>
                </c:forEach>
            </c:if>
        </dl>
    </div>
    <br>
    <div class="koppelen">
        <form:form method="get" action="/goToConnectAccount" modelAttribute="AddAccountHolderRequest">
            <input type="submit" value="Rekening Koppelen" class="btn btn-warning submit">
            <a href="/logOut">
                <button class="btn btn-warning uitloggen" type="button"> Uitloggen</button>
            </a>
        </form:form>
    </div>

</section>

<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf" %>
</div>

<script>
    function formToSend() {
        document.getElementById("formToSend").submit();
    }
</script>
</body>