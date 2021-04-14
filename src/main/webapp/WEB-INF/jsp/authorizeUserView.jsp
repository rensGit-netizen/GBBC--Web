<META NAME="secondAuthor" CONTENT="Emily">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/authorizeUser.css" type="text/css"/>
    <script src="js/validation.js" type="text/javascript"></script>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
<%@include file="../jspf/header.jspf" %>
</div>
<section>
    <%--Hier onze eigen functionaliteiten voor betreffende view--%>
            <!--    <Hier onze eigen functionaliteiten voor betreffende view-->
    <!--    Dit is dummy text hieronder>-->
            <div class="machtigen pagination justify-content-center">
                <form:form class="machtigen"
                           autocomplete="on"
                           method="get"
                           action="/account-statement"
                           modelAttribute="customer">

                    <h3>Gemachtigde toevoegen</h3>
                    <div class ="headimage">
                        <img class = "picture"
                             src="../../images/yesyes.jpg"
                             alt="Open Account"></div>
                    <div class="col-md-12">
                        <label class="form-label">Rekeningnummer</label>
                        <form:input type="text"
                                    value="${selectedIban}"
                                    disabled="true"
                                    class="form-control"
                                    path="bankAccount"/>
                    </div>

                    <div class="col-md-12">
                        <label class="form-label">Gebruikersnaam</label>
                        <div class="input-group">
                            <div class="input-group-text">@</div>
                            <form:input id="customerEmail"
                                        type="text"
                                        class="form-control"
                                        placeholder="E-mailadres"
                                        onkeyup="validateEmail()"
                                        path="user_name"/>
                        </div>
                    </div>
                    <br><br>
                    <a href = "/account-overview"><button class="btn btn-warning annuleren" type="button">Annuleren</button></a>
                    <input type="submit" style="font-weight: bold" value="Machtigen" class="btn btn-warning submit">
                </form:form>
            </div>
        </section>




<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>