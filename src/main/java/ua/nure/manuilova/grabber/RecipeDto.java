package ua.nure.manuilova.grabber;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class RecipeDto {
    private String recipeName = "";
    @Setter(AccessLevel.PRIVATE)
    private List<ProductDto> products = new ArrayList<>();

    public void addProduct(ProductDto productDto) {
        products.add(productDto);
    }
}
