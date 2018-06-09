package ua.nure.manuilova.akira.web.service;

import ua.nure.manuilova.models.RecipeDto;

import java.util.List;

public interface RecipeService {
    void addRecipe(RecipeDto recipeDto);

    void updateRecipe(RecipeDto recipeDto);

    void removeRecipe(int id);

    RecipeDto getRecipeById(int id);

    List<RecipeDto> listRecipes();
}
