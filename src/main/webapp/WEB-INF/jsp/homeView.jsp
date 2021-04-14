<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad--%>
    <title>GBBC - home</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/home.css" type="text/css"/>
</head>
<%-- <body> = de inhoud van de webpagina (headings, paragraphs, images, hyperlinks, tables, lists, etc.)--%>
<body>
<div id="header">
<%@include file="../jspf/header.jspf"%>
</div>
<%-- <section> = een sectie in de pagina--%>
<section>
    <form class="d-flex">
    <input class="form-control me-2" type="search" placeholder="Zoeken" aria-label="Search">
    <button class="btn btn-outline-success" type="submit">Zoeken</button>
    </form>

    <br/>

    <div class="container">

        <h2><strong>Welkom bij GBBC</strong></h2>
                <h6 class="slogan"><em>Get your gold while it's hot!</em></h6>

        <img class="img-fluid" src="../../../../images/paymentHappy.jpg">

                <p>
                    I'm baby direct trade kitsch pork belly shoreditch crucifix hashtag occupy sustainable quinoa migas pour-over. Gochujang typewriter mumblecore activated charcoal biodiesel chartreuse post-ironic kitsch la croix single-origin coffee unicorn vape. Unicorn craft beer copper mug artisan iceland vexillologist glossier coloring book offal portland palo santo. Hashtag literally activated charcoal ethical woke humblebrag distillery occupy keytar yr hella 8-bit intelligentsia pickled godard. Beard four dollar toast fam hot chicken, fixie sartorial tote bag thundercats selfies banh mi paleo.<br><br/>

                    Gluten-free beard chicharrones chambray. Beard ethical distillery coloring book, drinking vinegar four loko keffiyeh chillwave sustainable mlkshk bushwick seitan butcher. Pork belly hell of retro gluten-free wayfarers. Tattooed bespoke air plant +1 quinoa, pitchfork squid portland echo park.

                    Dummy text? More like dummy thicc text, amirite?
                </p>
                <a href="/open-account"><button class="btn btn-outline-success" type="button">Open Nieuwe Rekening</button></a>
    </div>

</section>

<%--footer info op alle pagina's hetzelfde--%>
<div id="footer">
    <%@include file="../jspf/footer.jspf"%>
</div>
</body>