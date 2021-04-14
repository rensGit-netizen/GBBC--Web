<META NAME="author" CONTENT="Emily">
<META NAME="secondAuthor" CONTENT="Rens">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GBBC - Open een rekening</title>
    <%@include file="../jspf/head.jspf" %>
    <link rel="stylesheet" href="css/openAccountV2.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>

<div id="header">
    <%@include file="../jspf/header.jspf" %>
</div>

<section>
    <!--Hier onze eigen functionaliteiten voor betreffende view>-->
    <div class="formulier pagination justify-content-center">
        <%--@elvariable id="customer" type="com.gbbc.webapplication.beans"--%>
        <form:form
                id="registrationForm"
                class="gebruiker"
                autocomplete="on"
                method="post"
                action="/account-created-succes" modelAttribute="openAccount">

            <h3>Schrijf je in en open nu een <br><strong>GBBC Gold Account</strong></h3>

            <div class="headimage"><img class="picture" src="../../../../images/openaccount.jpg" alt="Open Account">
            </div>

            <h4>Persoonsgegevens</h4>
            <div class="row g-2">
                <div class="col-md-6">
                    <label class="form-label">Voornaam</label>
                    <form:input type="text"
                                class="form-control"
                                placeholder="Voornaam"
                                onfocusout="isEmpty(this)"
                                path="first_name"/>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Achternaam</label>
                    <form:input type="text"
                                class="form-control"
                                placeholder="Achternaam"
                                onfocusout="isEmpty(this)"
                                path="last_name"/>
                </div>
            </div>
            <div class="col-md-12">
                <label class="form-label">Burgerservicenummer</label>
                <form:input id="customerBsn"
                            type="text"
                            class="form-control"
                            placeholder="BSN"
                            onkeyup="validateBsn()"
                            path="bsn"/>
            </div>
            <div class="col-md-12">
                <label class="form-label">Geboortedatum</label>
                <form:input id="customerDateOfBirth"
                            type="text"
                            class="form-control"
                            placeholder="__-__-____"
                            onkeyup="validateDate()"
                            path="date_of_birth"/>
            </div>
            <div class="col-md-12">
                <label class="form-label">Straatnaam & huisnummer</label>
                <form:input id="userStreetNr"
                            type="text"
                            class="form-control"
                            placeholder="Straatnaam & huisnummer"
                            onkeyup="validateCharAndDigit()"
                            path="street"/>
            </div>
            <div class="row g-2">
                <div class="col-md-4">
                    <label class="form-label">Postcode</label>
                    <form:input id="userPostalCode"
                                type="text"
                                class="form-control"
                                placeholder="0000XX"
                                onkeyup="validatePostalCode()"
                                path="postal_code"/>
                </div>
                <div class="col-md-8">
                    <label class="form-label">Woonplaats</label>
                    <form:input type="text"
                                class="form-control"
                                placeholder="Woonplaats"
                                onfocusout="isEmpty(this)"
                                path="city"/>
                </div>
            </div>
            <div class="col-md-12">
                <label class="form-label">Land</label>
                <form:input type="text"
                            class="form-control"
                            placeholder="Land"
                            onfocusout="isEmpty(this)"
                            path="country"/>
            </div>

            <h4>Accountgegevens</h4>
            <div class="col-md-12">
                <label class="form-label">Gebruikersnaam &nbsp;</label><a href="/login" style="color: hotpink"
                                                                          id="isValidCustomer"></a>
                <div class="input-group">
                    <div class="input-group-text">@</div>
                    <form:input id="customerEmail"
                                type="text"
                                class="form-control"
                                placeholder="E-mailadres"
                                onkeyup="validateEmail(); getUsername(document.getElementById('customerEmail').value)"
                                path="user_name"/>
                </div>
            </div>

            <div class="col-md-12">
                <label class="form-label">Wachtwoord</label>
                <form:input type="password"
                            class="form-control"
                            placeholder="Wachtwoord"
                            onfocusout="isEmpty(this)"
                            path="password"/>
            </div>

            <h4>Soort Rekening</h4>
            <div class="form-check form-check-inline">
                <form:radiobutton class="form-check-input"
                                  name="flexRadioDefault"
                                  id="flexRadioDefault3"
                                  path="customer_type"
                                  onclick="disablePrivate()"
                                  value="Particulier"
                                  checked="checked"/>
                <label class="form-check-label ">Particulier</label>
            </div>
            <div class="form-check form-check-inline">
                <form:radiobutton class="form-check-input"
                                  name="flexRadioDefault"
                                  id="flexRadioDefault4"
                                  path="customer_type"
                                  onclick="enablePrivate()"
                                  value="Zakelijk"/>
                <label class="form-check-label">Zakelijk</label>
            </div>
            <fieldset>
                <div class="col-md-12">
                    <label class="form-label">Bedrijfsnaam</label>
                    <form:input id="nameCompany"
                                type="text"
                                class="form-control"
                                placeholder="Bedrijfsnaam"
                                path="title"
                                disabled="true"/>
                </div>
                <div class="col-md-12">
                    <label class="form-label">Sector</label>
                    <form:input id="sectorCompany"
                                type="text"
                                class="form-control"
                                placeholder="Sector"
                                path="sector"
                                disabled="true"/>
                </div>
            </fieldset>
            <div class="form-check">
                <input class="form-check-input" type="checkbox">
                <label class="form-check-label">
                    Akkoord met de <a href="http://www.google.com">algemene voorwaarden</a>
                </label>
                <br><br>
                <button class="laatgeldzien btn btn-warning submit"
                        style="font-weight: bold"
                        type="button"
                        id="btnsubmit"
                        onclick="validateFormFields()">
                    Meldt u nu aan!
                </button>
                <a href="/home">
                    <button class="btn btn-warning annuleren"
                            type="button">Annuleren
                    </button>
                </a>

            </div>
        </form:form></div>
</section>

<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf" %>
</div>
</body>

<script>
    function enablePrivate() {

        var checkedPrivate = $("#flexRadioDefault4");
        var companyName = $("#nameCompany");
        var companySector = $("#sectorCompany");

        // if selected enable business fields
        if (checkedPrivate) {
            companyName.prop("disabled", false);
            companySector.prop("disabled", false);
            companyName.focus();
        }
    }

    function disablePrivate() {
        console.log("Zakelijk velden zijn uitgeschakeld");
        var checkedPrivate = $("#flexRadioDefault3");
        var companyName = $("#nameCompany");
        var companySector = $("#sectorCompany");
        data = "";

        if (checkedPrivate) {
            companyName.html(data);
            companySector.html(data);
            companyName.attr("disabled", true);
            companySector.attr("disabled", true);

        }
    }
</script>