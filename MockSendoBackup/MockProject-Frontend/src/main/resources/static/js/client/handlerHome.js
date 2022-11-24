$(document).ready(function () {});

function btnSearchCategoryName(value) {
  let category = value;
  $.post("search-product", { category }, (response) => {
    $("#load-product-item").html("");
    response.map((product) => {
      if (product.deleteStatus == false) {
        $("#load-product-item").append(`
          <div class="product-item">
                          <a href="/product-detail/${product.slug}">
                              <div class="product-item-detail">
                                  <div class="product-item-detail-contain">
                                      <div class="product-item-detail-img">
                                          <img class="product-item-img" src="${
                                            product.image
                                          }" alt=""
                                              style="max-height: 204px" />
                                      </div>
                                      <div class="product-item-detail-content">
                                          <span> <img mr="1"
                                              src="https://media3.scdn.vn/img4/2020/07_30/h6fJaiL5WkEbDU2eQRZb.png"
                                              alt="shop-badge" class="" /> <span
                                              style="word-wrap: break-word">${
                                                product.name
                                              }</span>
                                          </span>
                                      </div>
                                      <div class="product-item-detail-content-price">
                                          <span>${product.price.toLocaleString({
                                            style: "currency",
                                            currency: "VND",
                                          })}đ</span>
                                          
                                      </div>
                                      <div class="">
                                          Số lượng: 
                                          <span>${product.amount}</span>
                                      </div>
                                  </div>
                              </div>
                          </a>
                      </div>
          `);
      }
    });
  });
}
