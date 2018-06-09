package ua.nure.manuilova.akira.web.dao;

import ua.nure.manuilova.models.RecipeDto;

import java.util.List;

public interface RecipeDao {
    void addRecipe(ua.nure.manuilova.models.RecipeDto recipeDto);

    void updateRecipe(ua.nure.manuilova.models.RecipeDto recipeDto);

    void removeRecipe(int id);

    RecipeDto getRecipeById(int id);

    List<RecipeDto> listRecipes();
}
