$(document).ready(function () {
    getBodyTypes();
    getEngineTypes();
    getTransTypes();
    getLocation();
});

function validateCarInput() {
    var name = document.getElementById("name").value;
    var body = document.getElementById("body").value;
    var engine = document.getElementById("engine").value;
    var transmission = document.getElementById("transmission").value;
    var location = document.getElementById("location").value;
    var price = document.getElementById("price").value;
    var owner = document.getElementById("owner").value;

    if (name === "") {
        alert("Enter Car name");
        return false;
    } else if (body === "") {
        alert("Choose car body type");
        return false;
    } else if (engine === "") {
        alert("Chose car engine type");
        return false;
    } else if (transmission === "") {
        alert("Chose car transmission type");
        return false;
    } else if (location === "") {
        alert("Chose location");
        return false;
    } else if (price === "") {
        alert("Enter car price");
        return false;
    } else if (owner === "") {
        alert("Enter login name ");
        return false;
    }
    return true;
}

function getBodyTypes() {
    $.ajax({
        type: "GET",
        url: "getBodytype",
        success: function (data) {
            console.log(data);
            $("#body option").not(":first").remove();
            $.each(data, function (indexInArray, value) {
                $('#body option:last').after('<option>' + value + '</option>');
            });

        }
    });
}
function addNewBodyType() {
    var newBody = document.getElementById("newBody").value;
    $('#body option:last').after('<option>' + newBody + '</option>');
    document.getElementById("newBody").value = "";
}


function getEngineTypes() {
    $.ajax({
        type: "GET",
        url: "getEngine",
        success: function (data) {
            console.log(data);
            $("#engine option").not(":first").remove();
            $.each(data, function (indexInArray, value) {
                $('#engine option:last').after('<option>' + value + '</option>');
            });

        }
    });
}

function addNewEngineType() {
    var newEngine = document.getElementById("newEngine").value;
    $('#engine option:last').after('<option>' + newEngine + '</option>');
    document.getElementById("newEngine").value = "";
}


function getTransTypes() {
    $.ajax({
        type: "GET",
        url: "getTransmission",
        success: function (data) {
            console.log(data);
            $("#transmission option").not(":first").remove();
            $.each(data, function (indexInArray, value) {
                $('#transmission option:last').after('<option>' + value + '</option>');
            });

        }
    });
}

function addNewTransmission() {
    var newTransmission = document.getElementById("newTransmission").value;
    $('#transmission option:last').after('<option>' + newTransmission + '</option>');
    document.getElementById("newTransmission").value = "";
}


function getLocation() {
    $.ajax({
        type: "GET",
        url: "getLocation",
        success: function (data) {
            console.log(data);
            $("#location option").not(":first").remove();
            $.each(data, function (indexInArray, value) {
                $('#location option:last').after('<option>' + value + '</option>');
            });

        }
    });
}

function addNewLocation() {
    var newLocation = document.getElementById("newLocation").value;
    $('#location option:last').after('<option>' + newLocation + '</option>');
    document.getElementById("newLocation").value = "";
}