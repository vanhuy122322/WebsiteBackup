$(document).ready(function() {
	$("#category-table").DataTable();
	subCategoryAdd();
	validationAddCategory();
});

function categoryDelete(id) {
	if (confirm("Bạn muốn xóa không?")) {
		$.post("/admin/category/delete", { id }, () => {
			document.location.href = "/admin/category";
		});
	}
}
function subCategoryDelete(slug) {

	let id = $("#check_id").val();
	let subCategories = {
		slug,
	};
	let data = {
		id,
		subCategories
	};
	console.log(slug);
	console.log(id);
	if (confirm("Bạn muốn xóa không?")) {
		$.post("/admin/category/deletesub", { id, slug }, () => {
			document.location.href = "/admin/category";
		});
	}
}
function subCategoryAdd() {
	$(".btn-add-sub-category").click(function(event) {
		let lengthSubCategoty = $("#length-sub-category .row").length;
		console.log(lengthSubCategoty);
		$("#sub-category-add").append(
			`<div class="form-group" id="length-sub-category">
               <div class="row">
               <div class="ms-3">
               <label for="name">SubCategory:</label>
                <div class="col-sm-1">
      <label for="name">SubCategory Name:</label>
    </div>
    <div class="col-sm-12 pl-5">
      <input type="text" id="name" name="subCategories[${lengthSubCategoty}].name">
    </div>
   <div class="col-sm-1">
      <label for="name">SubCategory Image:</label>
    </div>
    <div class="col-sm-12 pl-5">
      <input type="text" id="name" name="subCategories[${lengthSubCategoty}].image">
    </div>
    </div>
  </div>
          </div>`

		);
	});
}
function validationAddCategory() {
	$("#form-add-category").validate({
		onfocusout: false,
		onkeyup: function(element) {
			$(element).valid();
		},
		onclick: false,
		wrapper: "div",
		rules: {
			name: {
				required: true

			}
		},
		messages: {
			name: {
				required: "Yêu cầu nhập tên danh mục sản phẩm"
			}
		},
	});
}

