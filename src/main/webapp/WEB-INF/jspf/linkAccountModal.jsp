
<META NAME="author" CONTENT="Rens">
<head>
    <meta charset="UTF-8">
    <%--    weergegeven titel in tabblad (elke keer aanpassen dus)--%>
    <title>GBBC</title>
    <%@include file="../jspf/head.jspf"%>
    <link rel="stylesheet" href="css/accountHolder.css" type="text/css"/>
    <script src="js/validation.js" type="text/javascript"></script>
</head>
<div class="modal" id="linkAccountModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Iemand uw rekening laten machtigen?</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <div class="inlogPanel">
                    <form:form class="koppelen" autocomplete="on" method="post" action="/connectAccount" modelAttribute="AddAccountHolderRequest">
                        <h2>Rekening koppelen</h2>
                        <div class ="headimage"><img class = "picture" src="../../images/moneyChain.jpg" alt="Open Account"></div>
                        <label>Te koppelen Rekening</label>
                        <form:input type="text"
                                    path="iban"
                                    class="form-control"
                                    onclick="isEmpty(this)"
                                    placeholder="NL**GBBC**********"/><br>
                        <label>Beveiligingscode</label>
                        <form:input id="controlCode"
                                    type="text"
                                    path="code"
                                    class="form-control"
                                    placeholder="*****"
                                    onkeyup="validateCode()"/><br>
                        <form:input type="hidden" path="customer_username"  value="${userSession.userName}"/>
                        <input type="submit" id="btnsubmit" value="Koppelen" class="btn btn-warning submit">
                        <a href="#" onclick="history.back()"><button type="button" class="btn btn-warning wachtwoordvergeten">Annuleren</button></a>
                        <span style="margin-bottom: -15px"><p id="warningText">${invalidCode}</p></span><br>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

