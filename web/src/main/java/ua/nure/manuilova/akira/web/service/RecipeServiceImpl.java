package ua.nure.manuilova.akira.web.service;

import ua.nure.manuilova.akira.web.dao.RecipeDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.manuilova.models.RecipeDto;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeDao recipeDao;

    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    @Transactional
    public void addRecipe(RecipeDto recipeDto) {
        this.recipeDao.addRecipe(recipeDto);
    }

    @Override
    @Transactional
    public void updateRecipe(RecipeDto recipeDto) {
        this.recipeDao.updateRecipe(recipeDto);
    }

    @Override
    @Transactional
    public void removeRecipe(int id) {
        this.recipeDao.removeRecipe(id);
    }

    @Override
    @Transactional
    public RecipeDto getRecipeById(int id) {
        return this.recipeDao.getRecipeById(id);
    }

    @Override
    @Transactional
    public List<RecipeDto> listRecipes() {
        return this.recipeDao.listRecipes();
    }
}
