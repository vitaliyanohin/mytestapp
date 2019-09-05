function deleteCalculation() {
    var dataFromTable  = JSON.stringify($('#view').bootstrapTable('getData'));
    alert(dataFromTable);
    var formData = new FormData();
    formData.append("formDataJson", dataFromTable);
    console.dir(formData);
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/mytestapp/delete";
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
