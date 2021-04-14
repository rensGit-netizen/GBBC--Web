/**
 * @author Emily
 */
$(document).ready(function () {
    console.log("Register Library js is loaded...++");
    $("#btnsubmit").click(validateFormFields);
});


/**
 * field validation: fields may not be empty
 * @param field in the form
 * @author Emily
 */
function isEmpty(field) {
    // check if field is empty
    if (field.value !== "") { // if true GROEN VINKJE
        console.log("correcte invoer")
        field.classList.add("is-valid");
        field.classList.remove("is-invalid");
    } else { // if false KRUISJE
        console.log("geen input")
        field.classList.add("is-invalid");
        field.classList.remove("is-valid");
    }
}

/**
 * validate BSN
 * @author Emily
 */
function validateBsn() {
    var bsn = $("#customerBsn");
    // check bsn pattern only digits, max length = 9
    const regex = new RegExp('^\\d{9}$');
    var result = regex.test(bsn.val());
    // if true GROEN VINKJE
    console.log(result);
    if (result) {
        bsn.addClass(" is-valid").removeClass(" is-invalid");
    } else {     // if false kruisje
        bsn.addClass(" is-invalid").removeClass(" is-valid");
    }
}

/**
 * validate Code
 * @author Rens
 */
function validateCode() {
    var code = $("#controlCode");
    // check code pattern only digits, max length = 5
    const regex = new RegExp('^\\d{5}$');
    var result = regex.test(code.val());
    // if true GROEN VINKJE
    console.log(result);
    if (result) {
        code.addClass(" is-valid").removeClass(" is-invalid");
    } else {     // if false kruisje
        code.addClass(" is-invalid").removeClass(" is-valid");
    }
}

/**
 * validate Date
 * @author Emily
 */
function validateDate() {
    var date = $("#customerDateOfBirth");
    // check date of birth pattern & leapyear
    const regex = new RegExp('^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$')
// const regex = new RegExp(['^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)', '\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)', '\\2))(?:(?:1[6-9]|[2-9]\\d)?\d{2})$|^(?:0?2(\\/|-|\\.)', '29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|', '[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))', '$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)', '(?:0?[1-9]|1\\d|2[0-8])\\4', '(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$'].join(''), "g");
    var result = regex.test(date.val());
    // if true GROEN VINKJE
    console.log(result);
    console.log(date);
    if (result) {
        date.addClass("is-valid").removeClass("is-invalid");
    } else {
        date.addClass("is-invalid").removeClass("is-valid");
    }
}

/**
 * validate email
 * @author Emily
 * @author Rens
 */
function validateEmail(email) {
    var emailField = $("#customerEmail");
    // check email for pattern
    const regex = new RegExp('^([a-zA-Z0-9_\\-\.]+)@([a-zA-Z0-9_\\-\.]+)\\.([a-zA-Z]{2,5})$');
    var result = regex.test(emailField.val());
    // if true GROEN VINKJE
    if (result) {
        emailField.addClass(" is-valid").removeClass(" is-invalid");
        $('#isValidCustomer').html("");
        getUsername(email);
    } else {     // if false kruisje
        emailField.addClass(" is-invalid").removeClass(" is-valid");
    }
}

/**
 * validate email database
 * @author pair programming Rens & Emily
 * @param email
 */
function getUsername(email) {
    //backend call mbv .ajax()
    console.log("in username check" + email)
    $.ajax({
        method: "POST",
        url: "/api/check-username",
        data: '{ "username": "' + email + '" }',
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).done(function (data) {
        //JSON is meteen als variabele/object te gebruiken!
        console.log(data);
        $('#isValidCustomer').html(data.message);
        if(data.message != null){
            $("#customerEmail").addClass(" is-invalid").removeClass(" is-valid");
        } else {
            $("#customerEmail").addClass(" is-valid").removeClass(" is-invalid");
            $('#isValidCustomer').html("");
        }
    });
}

/**
 * validate at least one letter and one digit
 * @author Emily
 */
function validateCharAndDigit() {
    var charAndDigit = $("#userStreetNr")
    // const regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$');
    const regex = new RegExp('^(\\b\\D+\\b)\\s*(\\b.*?\\d.*?\\b)\\s*(\\b\\D+\\b)?$');
    var result = regex.test(charAndDigit.val());

    if (result) {
        charAndDigit.addClass("is-valid").removeClass("is-invalid");
    } else {
        charAndDigit.addClass("is-invalid").removeClass("is-valid")
    }
}

/**
 * validate postal code
 * @author Emily
 */
function validatePostalCode() {
    var postalCode = $("#userPostalCode")
    const regex = new RegExp('^([0-9]{4})([a-zA-Z]{2})$');
    var result = regex.test(postalCode.val());

    if (result) {
        postalCode.addClass("is-valid").removeClass("is-invalid");
    } else {
        postalCode.addClass("is-invalid").removeClass("is-valid")
    }
}

/**
 * validate form fields on invalid inputs
 * @author Emily
 */
function validateFormFields() {
    console.log("formulier velden worden gevalideerd");
    // count the is-invalid fields
    // put in arraylist & check the length
    let amountInvalid = document.getElementsByClassName("is-invalid").length;
    // als >0 is-invalid ERROR
    if (amountInvalid > 0) { // alert check your fields
        // console.log("velden invullen kut");
        alert("Onjuist ingevulde velden.");
    } else { // submit form
        // $("#registrationForm").submit(); // jquery = library van javascript
        document.getElementById("registrationForm").submit(); // javascript
    }
}

/**
 * enable form fields for creating a business account
 * @author Emily
 */
function enablePrivateFields() {

    var checkedPrivate = $("#flexRadioDefault2");
    var companyName = $("#companyName");
    var companySector = $("#companySector");

    // if selected enable business fields
    if (checkedPrivate) {
        companyName.prop("disabled", false);
        companySector.prop("disabled", false);
        companyName.focus();
    }
}

/**
 * disable form fields for business account
 * @author Emily
 */
function disablePrivateFields() {
    console.log("Zakelijk velden zijn uitgeschakeld");
    var checkedPrivate = $("#flexRadioDefault1");
    var companyName = $("#companyName");
    var companySector = $("#companySector");
    data = "";

    if (checkedPrivate) {
        companyName.html(data);
        companySector.html(data);
        companyName.attr("disabled", true);
        companySector.attr("disabled", true);

    }
}

/**
 * Forgot password alert
 * @author Ismael
 */
function noMoneyMore() {
    alert("Sorry, geld is nu van de bank");
}

function validatetransactionAmount() {
    console.log("Transaction amount check for only numbers, 1 dot and not more than 2 numbers" +
        "after the dot.");
    var transactionAmount = $("#transactionAmount")
    const regex = new RegExp('^(\\d)+(\\.(\\d){0,2})?$');
    var result = regex.test(transactionAmount.val());

    if (result) {
        transactionAmount.addClass("is-valid").removeClass("is-invalid");
    } else {
        transactionAmount.addClass("is-invalid").removeClass("is-valid")
    }
}

