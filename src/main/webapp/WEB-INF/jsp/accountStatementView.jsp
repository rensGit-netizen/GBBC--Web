<META NAME="firstAuthor" CONTENT="Ismael">
<META NAME="secondAuthor" CONTENT="Emily">
<META NAME="thirdAuthor" CONTENT="Rens">
<%--@elvariable id="accountForBalance" type="com.gbbc.webapplication"--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf" %>
    <link rel="stylesheet" href="css/accountStatement.css" type="text/css"/>
    <link rel="stylesheet" href="css/infoBox.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
    <%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <br/>
    <c:if test="${not empty infopanel}">
        <div class="callout right">
            <div class="callout-header">Gelukt!</div>
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            <div class="callout-container">
                <p>${infopanel}</p>
            </div>
        </div>
    </c:if>
    <div class="displaytable">
        <div class="headGrid">
            <div class="headText">
                <h3 style="padding-left: 15px"><b>GBBC ${accountForBalance.type}</b>   ${accountForBalance.iban}</h3>
            </div>
            <div class="centerTextleft">
                <c:if test="${not empty accountForBalance}">
                    <h4 style="padding-left: 10px">huidig saldo:</h4>
                    <c:choose>
                        <c:when test="${accountForBalance.saldo<0}">
                            <h4 style="color: darkred">&euro;<fmt:formatNumber
                                    value="${accountForBalance.saldo}" type="number" minFractionDigits="2"
                                    maxFractionDigits="2"/></h4>
                        </c:when>
                        <c:otherwise>
                            <h4>&euro;<fmt:formatNumber
                                    value="${accountForBalance.saldo}" type="number" minFractionDigits="2"
                                    maxFractionDigits="2"/></h4>
                        </c:otherwise>
                    </c:choose>
                    <a href="#" style="padding-left: 10px"><%--@elvariable id="account" type="com.gbbc.webapplication"--%>
                        <form:form method="post"
                                   onclick="submit();"
                                   modelAttribute="account"
                                   action="/toTransactions">
                            <form:hidden path="id" value="${accountForBalance.id}"/>
                            <button class="btn btn-success geld">Maak geld over!</button>
                        </form:form></a>
                    <a href="#" style="padding-left: 30px">
                        <button type="submit"
                                class="btn btn-success geld"
                                data-bs-toggle="modal" data-bs-target="#myModal">Machtigen
                        </button>
                    </a>
                </c:if>
            </div>
            <div class="textGrid">
                <div class="textAllignLeft">Datum en tijd</div>
                <div style="text-align: left" class="textAllignCenter">Beschrijving</div>
                <div style="padding-left: 10px"> Rekening nr</div>
                <div class="textAllignRight">Bedrag</div>
            </div>
        </div>
        <div class="textTable">
            <div class="textGrid">
                <%--@elvariable id="transactionList" type="com.gbbc.webapplication"--%>
                <c:if test="${not empty transactionList}">
                    <c:forEach items="${transactionList}" var="transaction">
                        <c:choose>
                            <c:when test="${transaction.creditAccount.id == accountForBalance.id}">
                                <div class="textAllignLeft"><fmt:formatDate type="date" value="${transaction.date}"
                                                                            dateStyle="short" timeStyle="short"/></div>
                                <div style="text-align: left" class="textAllignCenter">${transaction.description}</div>
                                <div class="textAllignLeft">${transaction.debetAccount.iban}</div>
                                <div class="textAllignRight" style="color: #2DD28C">&euro; <fmt:formatNumber
                                        type="number" value="${transaction.amount}" minFractionDigits="2"
                                        maxFractionDigits="2"/></div>
                            </c:when>
                            <c:otherwise>
                                <div class="textAllignLeft"><fmt:formatDate type="date" value="${transaction.date}"
                                                                            dateStyle="short" timeStyle="short"/></div>
                                <div style="text-align: left" class="textAllignCenter">${transaction.description}</div>
                                <div class="textAllignLeft">${transaction.creditAccount.iban}</div>
                                <div class="textAllignRight" style="color: darkred">- &euro; <fmt:formatNumber
                                        type="number" value="${transaction.amount}" minFractionDigits="2"
                                        maxFractionDigits="2"/></div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</section>
<section class="buttons">
    <div class="keuzebuttons">
        <%--@elvariable id="AddAccountHolderRequest" type="com.gbbc.webapplication"--%>
        <form:form class="formButton"
                   method="post"
                   action="/goToAddAccountHolder"
                   modelAttribute="AddAccountHolderRequest">
            <form:input type="hidden" path="iban" value="${accountForBalance.iban}"/>
            <button type="submit"
                    class="btn btn-warning koppelen">Rekeninghouder Koppelen
            </button>
        </form:form>
        <c:if test="${accountForBalance.type == 'Zakelijk'}">
            <%--@elvariable id="pinTerminalStatus" type="com.gbbc.webapplication"--%>
            <form:form class="formButton"
                       method="post"
                       action="/request-terminal"
                       modelAttribute="pinTerminalStatus">
                <form:input type="hidden" path="accountId" value="${accountForBalance.id}"/>
                <%--			<form:input type="hidden" path="customerId" value=""/>--%>
                <button type="submit" value="Pinterminal aanvragen"
                        class="btn btn-warning submit aanvragen" style="font-weight: bold">
                    Pinterminal Aanvragen
                </button>
            </form:form>
        </c:if>
        <%--@elvariable id="userSession" type="com.gbbc.webapplication"--%>
        <c:choose>
            <c:when test="${empty userSession}">
                <a href="/goToAccountOverView">
                    <button class="btn btn-warning overzicht">Terug naar Overzicht</button>
                </a>
            </c:when>
            <c:otherwise>
                <%--@elvariable id="customer" type="com.gbbc.webapplication"--%>
                <form:form method="post" action="/loginControle" modelAttribute="customer" onclick="submit();">
                    <a href="#">
                        <button class="btn btn-warning overzicht">Terug naar Overzicht</button>
                    </a>
                    <form:hidden path="userName" value="${userSession.userName}"/>
                    <form:hidden path="password" value="${userSession.password}"/>
                </form:form>
            </c:otherwise>
        </c:choose>
    </div>
</section>
<%@include file="../jspf/authorizeModal.jspf" %>
<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf" %>
</div>
</body>

