package fa.training.spring.dto.product;

import lombok.Data;

@Data
public class ProductDetailDTO {
    private String id;
    private String title;
    private String description;
    private String material;
    private String origin; // xuat xu
    private String size;
    private String color;
}
