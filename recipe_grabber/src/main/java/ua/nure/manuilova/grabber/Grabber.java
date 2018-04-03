package ua.nure.manuilova.grabber;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grabber {
    public static void main(String[] args) throws IOException {
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (int i = 119; i <= 119; ++i) {
            String link = "http://daily-menu.ru/dailymenu/recipes/view/" + i;
            RecipeDto dto = new DailyMenuRecipeDocumentParser().getRecipe(link);
            recipeDtos.add(dto);
            Gson json = new Gson();
            System.out.println(json.toJson(dto));
        }
        Gson json = new Gson();
        System.out.println(json.toJson(recipeDtos));
    }
}
