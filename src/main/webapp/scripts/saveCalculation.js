function saveCalculation() {
    var jsonTableData  = JSON.stringify($('#table').bootstrapTable('getData'));
    var formData = new FormData();
    formData.append("formDataJson", jsonTableData);
    console.dir(formData);
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/mytestapp/save";
    xhr.open("POST", url, true);

    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        if (xhr.readyState != 4) return;


        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);

        } else {
            getAllCalculations();
            $('#table-form').hide();
        }
    };
    xhr.send(formData);
}
