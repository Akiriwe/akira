package ua.nure.manuilova.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Grabber {
    public static void main(String[] args) throws IOException {
        String url = "http://daily-menu.ru/dailymenu/recipes/view/12763";

        Document doc = Jsoup.connect(url).get();
        Elements recipeTitle = doc.getElementsByClass("recipe_title");
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setRecipeName(recipeTitle.first().ownText());

        Elements recipeCalculation = doc.select(".recipe_calculation > tbody");

        Elements ingredients = recipeCalculation.first().getElementsByTag("tr");

        for (Element ingredient : ingredients) {
            ProductDto productDto = new ProductDto();
            Elements productName = ingredient.getElementsByAttribute("itemprop");
            productDto.setProductName(productName.text());
            Elements variables = ingredient.getElementsByClass("variable");
            List<String> variablesList = new ArrayList<>();
            for (Element variable : variables) {
                variablesList.add(variable.ownText());
            }
            productDto.setWeight(parseNumber(variablesList.get(0)));
            productDto.setKcal(parseNumber(variablesList.get(1)));
            productDto.setProteins(parseNumber(variablesList.get(2)));
            productDto.setFats(parseNumber(variablesList.get(3)));
            productDto.setCarbohydrates(parseNumber(variablesList.get(4)));
            recipeDto.addProduct(productDto);
        }

        System.out.println(recipeDto);
    }

    private static Double parseNumber(String number) {
        return number.contains("-") ? 0 : Double.parseDouble(number);
    }
}
