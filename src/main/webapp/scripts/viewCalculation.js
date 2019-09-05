var needReload = false;

function viewData(e) {
    $('#input-form').hide();
    var formData = new FormData();
    formData.append("formDataJson", e);
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/mytestapp/getCalculation";
    xhr.open("POST", url, true);

    xhr.onreadystatechange = function() {

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            if (!needReload) {
                $('#table-view').show();
                $("#date").append(e);
                $('#view').bootstrapTable({data: JSON.parse(xhr.responseText)});
                needReload = true;
            } else {
                reload();
            }
            function reload(){
                var data = JSON.parse(xhr.responseText);
                $("#date").empty().append(e);
                $('#view').bootstrapTable("load", data);
            }
        }
    };
    xhr.send(formData);
}
