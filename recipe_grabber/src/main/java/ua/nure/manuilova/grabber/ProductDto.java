package ua.nure.manuilova.grabber;

import lombok.Data;

@Data
public class ProductDto {
    private String productName;
    private Double weight;
    private Double kcal;
    private Double proteins;
    private Double fats;
    private Double carbohydrates;
}
