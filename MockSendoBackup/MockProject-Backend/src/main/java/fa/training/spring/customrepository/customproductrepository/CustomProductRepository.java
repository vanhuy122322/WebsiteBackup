package fa.training.spring.customrepository.customproductrepository;

import java.util.List;

import fa.training.spring.entity.productentity.Product;

public interface CustomProductRepository {
    Product updateProduct(Product product);
    
    List<Product> findBeetwenPrice(String category,String subcategory,double startPrice, double endPrice);
}
