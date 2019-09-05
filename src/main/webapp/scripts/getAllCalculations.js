var visibleMainTable = false;

function getAllCalculations() {
    $('#input-form').show();
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/mytestapp/all";
    xhr.open('GET', url, false);
    xhr.send();
    if (xhr.status != 200) {
        alert(xhr.status + ': ' + xhr.getResponseHeader("text"));
    } else {
        if (!visibleMainTable) {
            $('#Main-table').bootstrapTable({data: JSON.parse(xhr.responseText)});
            visibleMainTable = true;
        } else {
            reload();
        }
        function reload() {
            var data = JSON.parse(xhr.responseText);
            $('#Main-table').bootstrapTable("load", data);
        }
    }
}
