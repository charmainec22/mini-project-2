package miniproject2.server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Recipe {
    Integer recipeId;
    String name;
    String imagePath;
    String url;
    String cuisine;

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    } 

    public static Recipe createFromJson(JsonObject json) {
        Recipe r = new Recipe();
        r.setRecipeId(json.getInt("id"));
        r.setName(json.getString("title"));
        r.setImagePath(json.getString("image"));
        r.setUrl(json.getString("sourceUrl"));
        //r.setCuisine(json.getString("cuisines"));
        return r;
    }

    public JsonObject toJson() {
        JsonObjectBuilder job = Json
        .createObjectBuilder()
        .add("recipeId", recipeId)
        .add("name", name)
        .add("imagePath", imagePath)
        .add("url", url);
        //.add("cuisine", cuisine);

        return job.build();
    }

}
