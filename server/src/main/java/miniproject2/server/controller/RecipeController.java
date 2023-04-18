package miniproject2.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import lombok.extern.slf4j.Slf4j;
import miniproject2.server.model.Recipe;
import miniproject2.server.serviceImpl.RecipeAPIService;

@Slf4j
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class RecipeController {
    
    @Autowired
    RecipeAPIService recipeSvc;

    @GetMapping(path = "/recipe")
    public ResponseEntity<String> getRecipe (
        @RequestParam String query,
        @RequestParam(defaultValue = "20") Integer number
        )     
        {

            log.info("INSIDE GET RECIPE!!!!!!!");
            //get list of recipes
            List<Recipe> recipes = recipeSvc.getRecipeByName(query, number);

            //List<Recipe> -> JsonArray
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            //add each recipe -> jsonobject
            recipes.forEach(r -> arrayBuilder.add(r.toJson()));

            JsonArray resp = arrayBuilder.build();


            return ResponseEntity.ok(resp.toString());
   }
}
