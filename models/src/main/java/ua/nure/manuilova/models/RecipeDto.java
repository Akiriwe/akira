package ua.nure.manuilova.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class RecipeDto {
    private Integer _id;
    private String sourceLink = "";
    private String recipeName = "";
    private String imageLink = "";
    private Double totalWeight;
    private Double totalKcal;
    private Double totalProteins;
    private Double totalFats;
    private Double totalCarbohydrates;
    private Double weightPerPortion;
    private Double kcalPerPortion;
    private Double proteinsPerPortion;
    private Double fatsPerPortion;
    private Double carbohydratesPerPortion;
    private Double kcalPer100g;
    private Double proteinsPer100g;
    private Double fatsPer100g;
    private Double carbohydratesPer100g;
    private Double proteinsPercent;
    private Double fatsPercent;
    private Double carbohydratesPercent;
    private String cookTime;
    private String advancedCookTime;
    private String preparation;

    @Setter(AccessLevel.PRIVATE)
    private List<ProductDto> products = new ArrayList<>();

    public void addProduct(ProductDto productDto) {
        products.add(productDto);
    }
}
