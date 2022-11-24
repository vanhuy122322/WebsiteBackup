package fa.training.spring.customrepository.customproductrepository;

import fa.training.spring.entity.productentity.Checkout;
import fa.training.spring.entity.productentity.Product;

public interface CustomCheckoutRepository {
    Checkout checkoutCancel(Checkout checkout);
    
    Checkout updateStatusCheckout(Checkout checkout);
}
