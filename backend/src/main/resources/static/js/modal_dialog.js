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