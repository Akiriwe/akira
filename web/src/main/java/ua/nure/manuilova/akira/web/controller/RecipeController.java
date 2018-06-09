package ua.nure.manuilova.akira.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.manuilova.akira.utils.SaveRecipeToFileUtil;
import ua.nure.manuilova.akira.web.service.RecipeService;
import ua.nure.manuilova.models.RecipeDto;

@Controller
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    @Qualifier(value = "recipeService")
    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "recipes", method = RequestMethod.GET)
    public String listRecipes(Model model){
        model.addAttribute("recipe", new RecipeDto());
        model.addAttribute("listRecipes", this.recipeService.listRecipes());

        return "recipes";
    }

    @RequestMapping(value = "/recipes/add", method = RequestMethod.POST)
    public String addRecipe(@ModelAttribute("recipe") RecipeDto recipeDto){
        if(recipeDto.get_id() == null){
            this.recipeService.addRecipe(recipeDto);
        } else {
            this.recipeService.updateRecipe(recipeDto);
        }

        return "redirect:/recipes";
    }

    @RequestMapping("/remove/{id}")
    public String removeRecipe(@PathVariable("id") int id){
        this.recipeService.removeRecipe(id);

        return "redirect:/recipes";
    }

    @RequestMapping("edit/{id}")
    public String editRecipe(@PathVariable("id") int id, Model model){
        model.addAttribute("recipe", this.recipeService.getRecipeById(id));
        model.addAttribute("listRecipes", this.recipeService.listRecipes());

        return "recipes";
    }

    @RequestMapping("recipedata/{id}")
    public String recipeData(@PathVariable("id") int id, Model model){
        model.addAttribute("recipe", this.recipeService.getRecipeById(id));

        return "recipedata";
    }

    @RequestMapping("recipedata/{id}/download")
    public String downloadRecipeData(@PathVariable("id") int id) {
        SaveRecipeToFileUtil.saveToFile(recipeService.getRecipeById(id));

        return "redirect:/recipedata/" + id;
    }
}
