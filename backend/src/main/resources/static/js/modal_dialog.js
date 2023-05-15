function showDeleteConfirmModal(link, entityName) {
    entityId = link.attr("entityId");
    $("#yesButton").attr("href", link.attr("href"));
    $("#modalTitle").text(entityName);
    $("#modalBody").text("Are you sure you want to delete this " + entityName +" ID " + entityId + "?");
    $("#modalDialog").modal("show");
  }

function clearFilter() {
    window.location = moduleURL;
}

function showModalDialog(title, message) {
    $("#modalTitle").text(title);
    $("#modalBody").text(message);
    $("#modalDialog").modal("show");
}

function showErrorModal(message) {
    showModalDialog("Error", message);
}

function showWarningModal(message) {
    showModalDialog("Warning", message);
}