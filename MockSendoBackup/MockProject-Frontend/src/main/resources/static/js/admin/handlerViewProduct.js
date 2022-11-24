$(document).ready(function () {
  $("#product-table").DataTable({
    order: [[5, "desc"]],
  });
  loadSubCategory();
  customValidation();
  validationAddProduct();
});

function loadSubCategory() {
  let name = $("#select-category").val();
  $.post("/admin/category/search", { name }, (resp) => {
    $("#wait-load-by-category").html("");
    resp.subCategories.map((e) => {
      if (e.name != null) {
        $("#wait-load-by-category").append(`
        <option value="${e.name}" ${
          e.name == $("#input-subCategory").val() ? "selected" : ""
        }> ${e.name}</option>
      `);
      }
    });
  });
}

function onChangeCategory(value) {
  let name = value;
  $.post("/admin/category/search", { name }, (resp) => {
    $("#wait-load-by-category").html("");
    resp.subCategories.map((e) => {
      if (e.name != null) {
        $("#wait-load-by-category").append(`
        <option value="${e.name}">${e.name}</option>
      `);
      }
    });
  });
}

function customValidation() {
  jQuery.validator.addMethod(
    "notNumber",
    function (value, element, param) {
      var reg = /[0-9]/;
      if (reg.test(value)) {
        return false;
      } else {
        return true;
      }
    },
    "Không được có chứa số"
  );
}

function productDelete(id) {
  if (confirm("Bạn muốn xóa không?")) {
    $.post("/admin/product/delete", { id }, () => {
      document.location.href = "/admin/product";
    });
  }
}

function validationAddProduct() {
  $("#form-add-product").validate({
    onfocusout: false,
    onkeyup: function (element) {
      $(element).valid();
    },
    onclick: false,
    wrapper: "div",
    rules: {
      name: {
        required: true,
        minlength: 6,
      },
      price: {
        required: true,
        min: 0,
      },
      amount: {
        required: true,
      },
      image: {
        required: true,
      },
      "productDetail.title": {
        required: true,
      },
      "productDetail.description": {
        required: true,
      },
      "productDetail.material": {
        required: true,
      },
      "productDetail.origin": {
        required: true,
      },
      "productDetail.size": {
        required: true,
      },
      "productDetail.color": {
        required: true,
      },
    },
    messages: {
      name: {
        required: "Yêu cầu nhập tên sản phẩm",
        maxlength: "Tên tối thiểu 6 ký tự",
      },
      price: {
        required: "Yêu cầu nhập giá sản phẩm",
        min: "Giá tối thiểu là 0",
      },
      amount: {
        required: "Yêu cầu nhập số lượng sản phẩm",
      },
      image: {
        required: "Yêu cầu nhập hình ảnh sản phẩm",
      },
      "productDetail.title": {
        required: "Yêu cầu nhập tiêu đề sản phẩm",
      },
      "productDetail.description": {
        required: "Yêu cầu nhập miêu tả sản phẩm",
      },
      "productDetail.material": {
        required: "Yêu cầu nhập chất liệu sản phẩm",
      },
      "productDetail.origin": {
        required: "Yêu cầu nhập xuất sứ sản phẩm",
      },
      "productDetail.size": {
        required: "Yêu cầu nhập kích thước sản phẩm",
      },
      "productDetail.color": {
        required: "Yêu cầu nhập màu sắc sản phẩm",
      },
    },
  });
}
