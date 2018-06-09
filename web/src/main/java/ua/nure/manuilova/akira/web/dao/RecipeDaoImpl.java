package ua.nure.manuilova.akira.web.dao;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ua.nure.manuilova.models.RecipeDto;

import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RecipeDaoImpl implements RecipeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeDaoImpl.class);
    private static final Gson GSON = new Gson();
    private DBCollection collection;

    public RecipeDaoImpl() {
        try {
            MongoClient mongoClient = new MongoClient();
            collection = mongoClient.getDB("Akira").getCollection("Recipies");
        } catch (UnknownHostException e) {
            LOGGER.error("Unknown host", e);
        }
    }

    @Override
    public void addRecipe(RecipeDto recipeDto) {
        collection.insert((DBObject) JSON.parse(GSON.toJson(recipeDto)));
        LOGGER.info("Recipe successfully saved. Recipe details: " + recipeDto);
    }

    @Override
    public void updateRecipe(RecipeDto recipeDto) {
        BasicDBObject searchQuery = new BasicDBObject().append("_id", recipeDto.get_id());
        collection.update(searchQuery, (DBObject) JSON.parse(GSON.toJson(recipeDto)));
        LOGGER.info("Recipe successfully updated. Recipe details: " + recipeDto);
    }

    @Override
    public void removeRecipe(int id) {
        DBObject object = collection.findAndRemove(new BasicDBObject().append("_id", id));
        LOGGER.info("Recipe successfully removed. Recipe details: " + object);
    }

    @Override
    public RecipeDto getRecipeById(int id) {
        DBObject object = collection.findOne(new BasicDBObject().append("_id", id));
        LOGGER.info("Recipe successfully loaded. Recipe details: " + object);

        return GSON.fromJson(GSON.toJson(object), RecipeDto.class);
    }

    @Override
    public List<RecipeDto> listRecipes() {
        List<DBObject> recipeDtoList = collection.find().toArray();
        LOGGER.info("RecipeDto list: " + recipeDtoList);

        return recipeDtoList.stream()
                .map(dbObject -> GSON.fromJson(GSON.toJson(dbObject), RecipeDto.class))
                .collect(Collectors.toList());
    }
}
