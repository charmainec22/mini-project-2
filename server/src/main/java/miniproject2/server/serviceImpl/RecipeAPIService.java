package miniproject2.server.serviceImpl;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.extern.slf4j.Slf4j;
import miniproject2.server.model.Recipe;

@Slf4j
@Service
public class RecipeAPIService {
    
    @Value("${RECIPE_API_KEY}")
    private String apiKey;

    private static final String RECIPE_URL = "https://api.spoonacular.com/recipes/complexSearch";

    private String createUrlWithAuthParam() {
        final String url = UriComponentsBuilder
        .fromUriString(RECIPE_URL)
        .queryParam("apiKey", apiKey)
        .toUriString();

        log.info("URL IS:", url);
        return url;
    }

    public List<Recipe> getRecipeByName (
        String query,
        Integer number
    ) {
        String urlWithAuthParams = createUrlWithAuthParam();
        final String url = UriComponentsBuilder
        .fromUriString(urlWithAuthParams)
        .queryParam("query", query)
        .queryParam("number", number)
        .toUriString();

        return getRecipe(url);
    }

    private List<Recipe> getRecipe (String url) {
        RequestEntity req = RequestEntity 
        .get(url)
        .accept(MediaType.APPLICATION_JSON)
        .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject json = reader.readObject();

        // {
        //     "results": [
        //         {
        //             "id": 634698,
        //             "title": "Beef Tataki",
        //             "image": "https://spoonacular.com/recipeImages/634698-312x231.jpg",
        //             "imageType": "jpg",
        //             "nutrition": {
        //                 "nutrients": [
        //                     {
        //                         "name": "Fat",
        //                         "amount": 17.3046,
        //                         "unit": "g"
        //                     }
        //                 ]
        //             }
        //         },
        //     ],
        //     "offset": 0,
        //     "number": 2,
        //     "totalResults": 59
        // }

        JsonArray results = json.getJsonArray("results");

        List<Recipe> recipes = results
        .stream()
        .map(jv -> jv.asJsonObject())
        .map(jo -> Recipe.createFromJson(jo))
        .toList();

        return recipes;
    }
}
