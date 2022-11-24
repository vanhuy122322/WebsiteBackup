$(document).ready(function() {;
});


$( window ).on("load", function() {
        getProductWhenStartApp();
});

var products = [];

function getProductWhenStartApp() {
	let subCategory = $("#subcategory").val();
	let category = $("#category").val();
	console.log(category);
	let isNullCategory = $("#category").val() == "";
	let isNullSubCategory = $("#subcategory").val() == "";
	console.log(isNullCategory);
	console.log(isNullSubCategory);
	console.log(!isNullCategory);
	console.log(!isNullSubCategory);
	if (!isNullSubCategory) {
		$.post("/search-product", { subCategory }, (resp) => {
			products = resp;
		});
	}
	if (!isNullCategory) {
		$.post("/search-product", { category }, (resp) => {
			products = resp;
		});
	}
}

function btnSearchCategorys() {
	let subCategory = $("#subcategory").val();
	let category = $("#category").val();
	let from = $("#product-from-price").val();
	let isNullFrom = $("#product-from-price").val().length == 0;
	let to = $("#product-to-price").val();
	let isNullTo = $("#product-to-price").val().length == 0;
	let checkboxColor = $("input[name='check_color']:checked");
	let checkboxSize = $("input[name='check_size']:checked");
	let checkboxOrigin = $("input[name='check_origin']:checked");
	let checkboxMaterial = $("input[name='check_material']:checked");
	let listColor = [];
	let listSize = [];
	let listOrigin = [];
	let listMaterial = [];
	$.each(checkboxColor, function() {
		listColor.push($(this).val());
	});
	$.each(checkboxSize, function() {
		listSize.push($(this).val());
	});
	$.each(checkboxOrigin, function() {
		listOrigin.push($(this).val());
	});
	$.each(checkboxMaterial, function() {
		listMaterial.push($(this).val());
	});
	let productDetail;
	$("#table-tbody-content").html(``);
	products.map((product, index) => {
		productDetail = product.productDetail;
		isColor = listColor.includes(productDetail.color);
		isSize = listSize.includes(productDetail.size);
		isOrigin = listOrigin.includes(productDetail.origin);
		isMaterial = listMaterial.includes(productDetail.material);
		isPriceBeetween = from < product.price && to > product.price;
		let isCheckboxColor = listColor.length > 0;
		let isCheckBoxSize = listSize.length > 0;
		let isCheckboxOrigin = listOrigin.length > 0;
		console.log(product.category);
		let isCheckboxMaterial = listMaterial.length > 0;
		if (product.subCategory == subCategory && product.deleteStatus == false || product.category == category && product.deleteStatus == false) {
			if (
				!isCheckboxColor &&
				!isCheckBoxSize &&
				!isCheckboxOrigin &&
				!isCheckboxMaterial &&
				isNullFrom &&
				isNullTo
			) {
				$("#table-tbody-content").append(`
				<div class="info-related-product">
				<a href="/product-detail/${product.slug}" style="text-decoration: none;">
										<div class="card" style="width: 13.7rem">
											<img src="${product.image}" class="card-img-top" alt="..." />
											<div class="card-body">
												<div style="display: flex;">
													<img
														src="https://media3.scdn.vn/img4/2020/07_30/h6fJaiL5WkEbDU2eQRZb.png"
														alt="Shop's badge" class="image-shop"
														style="width: 50px; height: 30px"> <span
														class="d7e-03afbc _405-7e8814 undefined d7e-d87aa1"
														>${product.name}</span>
												</div>
												<div class="d7e-6f98d6">
													<span class="_405-dff05d undefined d7e-266ced d7e-fb1c84"
														>${product.price.toLocaleString({
					style: "currency",
					currency: "VND",
				})}đ </span>
													<div class="">
														<span class="undefined d7e-7ef9b0 d7e-5269d7">379.000đ</span><span
															class="d7e-ee369c undefined d7e-7ef9b0 d7e-a4f628" ml="1">-22%</span>
													</div>
												</div>
												<div class="d7e-f8515b d7e-451688" py="1">
													<div
														class="d7e-f8515b d7e-451688 d7e-dd9ef1 d7e-22372d _405-a2690e d7e-a90f22 d7e-2243f4"
														style="background-color: rgb(241, 243, 249)">
														<img
															src="https://media3.scdn.vn/img4/2022/06_24/V5PHsdxRbMf35yH1KO0h.png"
															alt="promotion-icon" class="" /><span
															class="d7e-43a364 undefined d7e-7ef9b0" ml="2"
															style="color: rgb(19, 48, 150)">Trả góp Kredivo</span>
													</div>
												</div>
												<div
													class="d7e-faee4b _405-2c7cab _405-dbd399 d7e-a90f22 d7e-04f3bc d7e-2243f4"
													mt="1">
													<span class="undefined d7e-d87aa1">Đã bán 147</span>
													<div class="_405-934736 d7e-a90f22 d7e-5af16c d7e-2243f4"></div>
												</div>
												<div class="d7e-faee4b _405-a4dd8d _405-344c14 d7e-a90f22"
													mt="1" style="display: flex;">
													<span class="undefined d7e-d87aa1"
														>${product.productDetail.origin}</span>
												</div>
											</div>
										</div></a>
									</div>`);
			} else if (isColor || isSize || isOrigin || isMaterial || isPriceBeetween) {
				$("#table-tbody-content").append(`
				<div class="info-related-product">
				<a href="/product-detail/${product.slug}" style="text-decoration: none;">
										<div class="card" style="width: 13.7rem">
											<img src="${product.image}" class="card-img-top" alt="..." />
											<div class="card-body">
												<div style="display: flex;">
													<img
														src="https://media3.scdn.vn/img4/2020/07_30/h6fJaiL5WkEbDU2eQRZb.png"
														alt="Shop's badge" class="image-shop"
														style="width: 50px; height: 30px"> <span
														class="d7e-03afbc _405-7e8814 undefined d7e-d87aa1"
														>${product.name}</span>
												</div>
												<div class="d7e-6f98d6">
													<span class="_405-dff05d undefined d7e-266ced d7e-fb1c84"
														>${product.price.toLocaleString({
					style: "currency",
					currency: "VND",
				})}đ </span>
													<div class="">
														<span class="undefined d7e-7ef9b0 d7e-5269d7">379.000đ</span><span
															class="d7e-ee369c undefined d7e-7ef9b0 d7e-a4f628" ml="1">-22%</span>
													</div>
												</div>
												<div class="d7e-f8515b d7e-451688" py="1">
													<div
														class="d7e-f8515b d7e-451688 d7e-dd9ef1 d7e-22372d _405-a2690e d7e-a90f22 d7e-2243f4"
														style="background-color: rgb(241, 243, 249)">
														<img
															src="https://media3.scdn.vn/img4/2022/06_24/V5PHsdxRbMf35yH1KO0h.png"
															alt="promotion-icon" class="" /><span
															class="d7e-43a364 undefined d7e-7ef9b0" ml="2"
															style="color: rgb(19, 48, 150)">Trả góp Kredivo</span>
													</div>
												</div>
												<div
													class="d7e-faee4b _405-2c7cab _405-dbd399 d7e-a90f22 d7e-04f3bc d7e-2243f4"
													mt="1">
													<span class="undefined d7e-d87aa1">Đã bán 147</span>
													<div class="_405-934736 d7e-a90f22 d7e-5af16c d7e-2243f4"></div>
												</div>
												<div class="d7e-faee4b _405-a4dd8d _405-344c14 d7e-a90f22"
													mt="1" style="display: flex;">
													<span class="undefined d7e-d87aa1"
														>${product.productDetail.origin}</span>
												</div>
											</div>
										</div></a>
									</div>`);
			}
		}
	});
}

// function btnSearchCategorys() {
// 	var input = $('#subcategory').val();
// 	var from = $('#product-from-price').val();
// 	var to = $('#product-to-price').val();
// 	var listColor = [

// 	];
// 	var listSize = [

// 	];
// 	var listOrigin = [

// 	];
// 	var listMaterial = [

// 	];
// 	$.each($("input[name='check_color']:checked"), function() {
// 		listColor.push($(this).val());
// 	});

// 	$.each($("input[name='check_size']:checked"), function() {
// 		listSize.push($(this).val());
// 	});
// 	$.each($("input[name='check_origin']:checked"), function() {
// 		listOrigin.push($(this).val());
// 	});
// 	$.each($("input[name='check_material']:checked"), function() {
// 		listMaterial.push($(this).val());
// 	});
// 	$.post("/search-by-product-detail", { from, to, input, listColor, listSize, listOrigin,listMaterial }, (resp) => {
// 		$("#table-tbody-content").html(``);
// 		console.log(resp)
// 		products = resp.listData;
// 		products.map((product, index) => {
// 			$("#table-tbody-content").append(`
// 				<div class="info-related-product">
// 				<a href="/product-detail/${product.slug}" style="text-decoration: none;">
// 										<div class="card" style="width: 13.7rem">
// 											<img src="${product.image}" class="card-img-top" alt="..." />
// 											<div class="card-body">
// 												<div style="display: flex;">
// 													<img
// 														src="https://media3.scdn.vn/img4/2020/07_30/h6fJaiL5WkEbDU2eQRZb.png"
// 														alt="Shop's badge" class="image-shop"
// 														style="width: 50px; height: 30px"> <span
// 														class="d7e-03afbc _405-7e8814 undefined d7e-d87aa1"
// 														>${product.name}</span>
// 												</div>
// 												<div class="d7e-6f98d6">
// 													<span class="_405-dff05d undefined d7e-266ced d7e-fb1c84"
// 														>${product.price} </span>
// 													<div class="">
// 														<span class="undefined d7e-7ef9b0 d7e-5269d7">379.000đ</span><span
// 															class="d7e-ee369c undefined d7e-7ef9b0 d7e-a4f628" ml="1">-22%</span>
// 													</div>
// 												</div>
// 												<div class="d7e-f8515b d7e-451688" py="1">
// 													<div
// 														class="d7e-f8515b d7e-451688 d7e-dd9ef1 d7e-22372d _405-a2690e d7e-a90f22 d7e-2243f4"
// 														style="background-color: rgb(241, 243, 249)">
// 														<img
// 															src="https://media3.scdn.vn/img4/2022/06_24/V5PHsdxRbMf35yH1KO0h.png"
// 															alt="promotion-icon" class="" /><span
// 															class="d7e-43a364 undefined d7e-7ef9b0" ml="2"
// 															style="color: rgb(19, 48, 150)">Trả góp Kredivo</span>
// 													</div>
// 												</div>
// 												<div
// 													class="d7e-faee4b _405-2c7cab _405-dbd399 d7e-a90f22 d7e-04f3bc d7e-2243f4"
// 													mt="1">
// 													<span class="undefined d7e-d87aa1">Đã bán 147</span>
// 													<div class="_405-934736 d7e-a90f22 d7e-5af16c d7e-2243f4"></div>
// 												</div>
// 												<div class="d7e-faee4b _405-a4dd8d _405-344c14 d7e-a90f22"
// 													mt="1" style="display: flex;">
// 													<span class="undefined d7e-d87aa1"
// 														>${product.productDetail.origin}</span>
// 												</div>
// 											</div>
// 										</div></a>
// 									</div>

// 				`)
// 		})
// 	});
// }

