<META NAME="firstAuthor" CONTENT="Rens">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/success.css" type="text/css"/>
</head>

<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
<%@include file="../jspf/header.jspf" %>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
        <div class="informationField pagination justify-content-center">
            <h1 >Welkom bij GBBC ${openAccount.first_name}!</h1>
        </div>
        <!--    <Hier onze eigen functionaliteiten voor betreffende view>-->
        <div class = "accountinfo">
            <h4>De gegevens van uw nieuwe rekening</h4>
            <div class = "headimage"><img class = "picture" src="../../../../images/success.jpg" alt="Succes!"></div>
            <table class = "accounttable table table-sm table-hover">
                <tr>
                    <th>Naam Rekening</th>
                    <td>Gold Bar Banking Co. ${openAccount.customer_type}</td>
                </tr>
                <tr>
                    <th>Tenaamstelling</th>
                    <td><b>${openAccount.title}</b> ${openAccount.first_name} ${openAccount.last_name}</td>
                </tr>
                <tr>
                    <th>BSN</th>
                    <td>${openAccount.bsn}</td>
                </tr>
                <tr>
                    <th>Adres</th>
                    <td>${openAccount.street}</td>
                </tr>
                <tr>
                    <th></th>
                    <td>${openAccount.postal_code} ${openAccount.city}</td>
                </tr>
                <tr>
                    <th></th>
                    <td>${openAccount.country}</td>
                </tr>
                <tr>
                    <th>Soort rekening</th>
                    <td>${openAccount.customer_type}</td>
                </tr>
                <tr>
                    <th>Rekeningnummer (IBAN)</th>
                    <td>${openAccount.iban}</td>
                </tr>
                <tr>
                    <th>Gebruikersnaam</th>
                    <td>${openAccount.user_name}</td>
                </tr>
                <tr>
                    <th>Wachtwoord</th>
                    <td><i>${openAccount.password}</i></td>
                </tr>
                <tr>
                    <th>Je pas met pincode wordt per post naar u toe gestuurd.</th>
                </tr>

            </table>
            <br>
            <div class="text-center">
            <a href="/login"><button class ="btn btn-warning" type="button">Ik wil mijn geld zien!</button></a>
        </div></div>
    </section>
<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>