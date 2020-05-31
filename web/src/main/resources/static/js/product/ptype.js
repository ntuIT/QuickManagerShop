$(document).ready(function () {
    $("#btnSubmit").click(function (event) {

        // Fetch form to apply custom Bootstrap validation
        var form = $("#myForm")

        if (form[0].checkValidity() === false) {
            event.preventDefault()
            event.stopPropagation()
        }

        form.addClass('was-validated');
        // Perform ajax submit here...

    });
    $(".btn-delete").click(function (event) {
        var code = $(this).attr('id');
        showConfirmPopup(code);
    });
});

function showConfirmPopup(code) {
    // Show confirm dialog
    // then call below ajax in yes handler
    $.ajax({
        type : "POST",
        url : "/product_type/delete",
        dataType : "json",
        contentType : "application/json; charset=utf-8",
        data : JSON.stringify({
            "code" : code
        }),
        success : function (result) {
            console.log(result.message);
            // Remove parent card view of this button
            $("#item"+code).remove();
        },
        error : function (response, status, error) {
            // Show notification error with bootstrap 4
            console.log(response.responseJSON.message);
        }
    });
}