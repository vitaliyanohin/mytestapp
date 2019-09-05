function runningFormatter(value, row, index) {
    return index;
}
function linkFormatter(value, row) {
    console.dir();

    return "<a href ='javascript:void(0)' onclick='viewData(\"" + row.date_calculation + "\")'> Link </a>";
}
