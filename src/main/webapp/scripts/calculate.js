var needReload = false;
function calculate() {
    $('#input-form').hide();
    var formData = new FormData();
    formData.append("formDataJson", document.getElementById("file").files[0]);
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/mytestapp/calculate";
    xhr.open("POST", url, true);

    xhr.onreadystatechange = function() {
        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            console.dir(JSON.stringify(xhr.responseText));
            console.log(JSON.parse(xhr.responseText));
            if (!needReload) {
                $('#table-form').show();
                $('#table').bootstrapTable({data: JSON.parse(xhr.responseText)});
                needReload = true;
            } else {
                reload();
            }
            function reload() {
                $('#table-form').show();
                var data = JSON.parse(xhr.responseText);
                $('#table').bootstrapTable("load", data);
            }
        }
    };
    xhr.send(formData);
}
