<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>TestSite</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<h3>Type in the bankaccountNumber</h3>
<input type="text" id="accountInput" required><br>
<h3>Type in the amount</h3>
<input type="text" id="amountInput" required><br>
<h3>Type in the pincode</h3>
<input type="text" id="pinInput" required><br>
<button onclick="myRestFunction()">Ok</button>
<br>
<div id="confirmation">
    -
</div>

<script>
    function myRestFunction() {
        //backend call mbv .ajax()
        var accountNumberDebit = document.getElementById("accountInput").value;
        var amount = document.getElementById("amountInput").value;
        var pin = document.getElementById("pinInput").value;
        if (accountNumberDebit == "" || amount == "" || pin == "") {
            $('#confirmation').html("Vul alstublieft alle velden in.");
        } else {
            $.ajax({
                method: "POST",
                url: "/api/saldo",
                data: JSON.stringify({
                    "debetBankAcc": accountNumberDebit, "creditBankAcc": "1395419486",
                    "amount": amount, "description": "Rens WortelPaleis", "pincodeDebetAcc": pin
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function (data) {
                //JSON is meteen als variabele/object te gebruiken!
                console.log(data);
                console.log(data.message);
                $('#confirmation').html(data.message);
            });
        }

    }
</script>
</body>
</html>
