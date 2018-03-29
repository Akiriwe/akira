package ua.nure.manuilova.grabber;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DailyMenuRecipeDocumentParser {
    public RecipeDto getRecipe(String url) throws IOException {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (HttpStatusException e) {
            if (e.getStatusCode() == 404) {
                return null;
            }
        }

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setSourceLink(url);
        Elements recipeTitle = doc.getElementsByClass("recipe_title");
        recipeDto.setRecipeName(recipeTitle.first().ownText());

        Elements image = doc.select(".orange_block > a");
        recipeDto.setImageLink("http://daily-menu.ru" + image.first().attributes().get("href"));

        Elements recipeCalculationBody = doc.select(".recipe_calculation > tbody");

        Elements ingredients = recipeCalculationBody.first().getElementsByTag("tr");

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

        Elements footValues = doc.select(".orange_block > .recipe_calculation_container > .recipe_calculation > tfoot > tr");

        List<String> variablesTotal = parseVariables(footValues.get(0));

        recipeDto.setTotalWeight(parseNumber(variablesTotal.get(0)));
        recipeDto.setTotalKcal(parseNumber(variablesTotal.get(1)));
        recipeDto.setTotalProteins(parseNumber(variablesTotal.get(2)));
        recipeDto.setTotalFats(parseNumber(variablesTotal.get(3)));
        recipeDto.setTotalCarbohydrates(parseNumber(variablesTotal.get(4)));

        List<String> dataForOnePortion = parseVariables(footValues.get(1));

        recipeDto.setWeightPerPortion(parseNumber(dataForOnePortion.get(0)));
        recipeDto.setKcalPerPortion(parseNumber(dataForOnePortion.get(1)));
        recipeDto.setProteinsPerPortion(parseNumber(dataForOnePortion.get(2)));
        recipeDto.setFatsPerPortion(parseNumber(dataForOnePortion.get(3)));
        recipeDto.setCarbohydratesPerPortion(parseNumber(dataForOnePortion.get(4)));

        List<String> dataFor100g = parseVariables(footValues.get(2));

        recipeDto.setKcalPer100g(parseNumber(dataFor100g.get(1)));
        recipeDto.setProteinsPer100g(parseNumber(dataFor100g.get(2)));
        recipeDto.setFatsPer100g(parseNumber(dataFor100g.get(3)));
        recipeDto.setCarbohydratesPer100g(parseNumber(dataFor100g.get(4)));

        List<String> percents = parseVariables(footValues.get(3));

        recipeDto.setProteinsPercent(parsePercent(percents.get(2)));
        recipeDto.setFatsPercent(parsePercent(percents.get(3)));
        recipeDto.setCarbohydratesPercent(parsePercent(percents.get(4)));

        Elements infoBlocks = doc.select(".info_block > div[style] > div[itemprop]");
        recipeDto.setCookTime(infoBlocks.get(0).text());
        recipeDto.setAdvancedCookTime(infoBlocks.get(1).text());

        Element preparationElement = doc.getElementById("recipe_content_block");
        Elements prepSteps = preparationElement.getElementsByTag("p");
        StringBuilder preparation = new StringBuilder();
        for (Element step : prepSteps) {
            if (!step.text().contains("Смотреть рецепты других ")) {
                preparation.append(step.text()).append(System.lineSeparator());
            }
        }
        recipeDto.setPreparation(preparation.toString());

        return recipeDto;
    }

    private List<String> parseVariables(Element element) {
        return element.getElementsByClass("variable").stream().map(Element::text).collect(Collectors.toList());
    }

    private Double parseNumber(String number) {
        return number.contains("-") ? 0 : Double.parseDouble(number);
    }

    private Double parsePercent(String number) {
        return Double.parseDouble(number.substring(0, number.length() - 1));
    }
}
