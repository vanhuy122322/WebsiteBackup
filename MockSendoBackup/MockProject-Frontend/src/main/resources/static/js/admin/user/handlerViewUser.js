$(document).ready(function () {
  $("#user-table").DataTable(
	{
		order: [[5, 'desc']],
	}
);
});

function userDelete(id) {
  if (confirm("Bạn muốn xóa không?")) {
    $.post(
      "/admin/user/delete",
      { id },
      (result) => {
        document.location.href = "/admin/user";
      }
    );
  }
}
