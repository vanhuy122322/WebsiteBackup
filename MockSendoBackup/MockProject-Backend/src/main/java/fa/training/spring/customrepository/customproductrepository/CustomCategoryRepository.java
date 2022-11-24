package fa.training.spring.customrepository.customproductrepository;

import fa.training.spring.entity.productentity.Category;

public interface CustomCategoryRepository {
    Category updateCategory(Category category);

    Category deleteSub(Category category);

}
