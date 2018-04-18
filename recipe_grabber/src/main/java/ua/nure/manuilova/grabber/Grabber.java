package ua.nure.manuilova.grabber;

import ua.nure.manuilova.models.RecipeDto;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Grabber {
    public static void main(String[] args) throws IOException {
        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("Akira");
        DBCollection collection = database.getCollection("Recipies");
        List<DBObject> recipeDtos = new ArrayList<>();

        for (int i = 120; i <= 200; ++i) {
            RecipeDto dto = DailyMenuRecipeDocumentParser.getRecipe(i);
            recipeDtos.add(convert(dto));
        }

        collection.insert(recipeDtos.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private static DBObject convert(RecipeDto recipeDto) {
        Gson gson = new Gson();
        String json = gson.toJson(recipeDto);
        return (DBObject) JSON.parse(json);
    }
}
