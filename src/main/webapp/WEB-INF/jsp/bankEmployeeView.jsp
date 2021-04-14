<META NAME="firstAuthor" CONTENT="Rens">
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/bankEmployee.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
<%@include file="../jspf/headerEmployee.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>

    <div class="intro">
        <h2><strong>Pinautomaat Aanvraag Overzicht</strong></h2>
    </div>
    <div class="datatable">
        <table class="aanvraagtable table table-fixed table-sm table-responsive-sm table-hover table-borderless">
            <thead>
            <tr>
                <th scope="col" ro>Aanvrager</th>
                <th scope="col">Tenaamstelling</th>
                <th scope="col">IBAN</th>
                <th scope="col">Aanvraag Datum</th>
                <th scope="col">Afhandel Datum</th>
                <th scope="col">Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${!empty(requests)}">
            <c:forEach var="request" items="${requests}">
            <form:form method="post" action="/controlCode" modelAttribute="pinTerminalStatus">
            <tr>
                <form:input type="hidden" path="user_name" value="${request.customer.userName}"/>
                <form:input type="hidden" path="first_name" value="${request.customer.first_name}"/>
                <form:input type="hidden" path="last_name" value="${request.customer.last_name}"/>
                <form:input type="hidden" path="customerId" value="${request.customer.id}"/>
                <form:input type="hidden" path="iban" value="${request.bankAccount.iban}"/>
                <form:input type="hidden" path="accountId" value="${request.bankAccount.id}"/>
                <form:input type="hidden" path="title" value="${request.bankAccount.title}"/>
                <form:input type="hidden" path="accountType" value="${request.bankAccount.type}"/>
                <form:input type="hidden" path="requestDate" value="${request.requestDate}"/>
                <form:input type="hidden" path="responseDate" value="${request.responseDate}"/>
                <form:input type="hidden" path="status" value="${request.status}"/>
                <form:input type="hidden" path="controlCode" value="${request.controlCode}"/>
                <td>${request.customer.first_name}&nbsp;${request.customer.last_name}</td>
                <td>${request.bankAccount.title}</td>
                <td>${request.bankAccount.iban}</td>
                <td>${request.requestDate}</td>
                <td>${request.responseDate}</td>
                <td>${request.status}</td>
                <td align="right">
                <c:if test="${request.status != 'Code verstuurd!'}"><input class="submit btn btn-warning" type="submit" value="Genereer Code" id="submit">
                </c:if>
                </td>
            </tr>
            </form:form>
            </c:forEach>
            </c:if>
        </table>
        <%--		<a href="/private-banking-customers"><button class="btn btn-outline-success" type="button">Particulieren</button></a>--%>
        <%--		<a href="/business-customers"><button class="btn btn-outline-success" type="button">MKB</button></a>--%>
        <%--		<a href="/login"><button class="btn btn-outline-success" type="button">Uitloggen</button></a>--%>
    </div>
</section>
<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf" %>
</div>
<script>
    function change() // no ';' here
    {
        alert("67283");
    }
</script>
</body>