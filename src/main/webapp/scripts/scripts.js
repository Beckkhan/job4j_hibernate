$(function(){
    loadItems();
});

function loadItems() {
    resetTable();
    var check = document.getElementById("check");
    var id;
    var date;
    $.ajax({
        url: "/todo",
        type: "GET",
        success: function (data) {
            console.log(data);
            $.each(data, function (indexInArray, value) {
                date = new Date(value.created).toLocaleDateString();
                id = value.id;
                if (value.done && check.checked === true) {
                    $('#table tr:last').after(
                        '<tr><td>' + value.id
                        + '</td><td>' + value.description
                        + '</td><td>' + date
                        + '</td><td><input type="checkbox" name="checkDone" id="' + value.id +'" checked></td></tr>');
                } else if (!value.done) {
                    $('#table tr:last').after(
                        '<tr><td>' + value.id
                        + '</td><td>' + value.description
                        + '</td><td>' + date
                        + '</td><td><input type="checkbox" name="checkDone" id="' + value.id +'"></td></tr>');
                }

            })

        }
    })
}

function resetTable() {
    $("#table tr").not(":first").remove();
}

function validate() {
    var description = document.getElementById("desc").value;
    console.log(description);
    var result = true;
    if (description === "") {
        alert("Enter description for new Task!");
        result = false;
    }
    return result;
}

$(document).on('click', 'input[name=checkDone]', function () {
    var itemId = $(this).attr('id');
    console.log(itemId + $(this).is(':checked'));
    $.ajax({
        url: "/status",
        type: "GET",
        contentType: 'text/html',
        data: ({itemId: itemId, itemStatus: $(this).is(':checked')})
    });
});