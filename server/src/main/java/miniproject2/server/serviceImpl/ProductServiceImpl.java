package miniproject2.server.serviceImpl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.extern.slf4j.Slf4j;
import miniproject2.server.JWT.JwtFilter;
import miniproject2.server.constants.CafeConstants;
import miniproject2.server.dao.ProductDao;
import miniproject2.server.model.Category;
import miniproject2.server.model.Product;
import miniproject2.server.model.Recipe;
import miniproject2.server.service.ProductService;
import miniproject2.server.utils.CafeUtils;
import miniproject2.server.wrapper.ProductWrapper;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao productDao;

    @Autowired
    JwtFilter jwtFilter;

    @Value("${RECIPE_API_KEY}")
    private String apiKey;

    private static final String RECIPE_URL = "https://api.spoonacular.com/recipes/complexSearch";

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requeMap) {
        try {
            if (jwtFilter.isAdmin()) {

                if (validateProductMap(requeMap,false)) {
                    productDao.save(getProductFromMap(requeMap, false));
                    return CafeUtils.getResponseEntity("Product added successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Product getProductFromMap(Map<String, String> requeMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requeMap.get("categoryId")));

        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requeMap.get("id")));
        } else {
            product.setStatus("true");
        }

        product.setCategory(category);
        product.setName(requeMap.get("name"));
        product.setDescription(requeMap.get("description"));
        product.setPrice(Integer.parseInt(requeMap.get("price")));
        return product;
    }

    private boolean validateProductMap(Map<String, String> requeMap, boolean validateId) {
        if (requeMap.containsKey("name")) {
            if(requeMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            log.info("INSIDE GET");
            return new ResponseEntity<>(productDao.getAllProduct(), HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requeMap) {
        try {
            if (jwtFilter.isAdmin()) {

                if (validateProductMap(requeMap,true)) {

                   Optional<Product> optional = productDao.findById(Integer.parseInt(requeMap.get("id")));
                   if (!optional.isEmpty()) {

                        Product product = getProductFromMap(requeMap, true);
                        product.setStatus(optional.get().getStatus());
                        productDao.save(product);
                        return CafeUtils.getResponseEntity("Product updated successfully", HttpStatus.OK);

                   } else {

                        return CafeUtils.getResponseEntity("Product Id does not exist", HttpStatus.OK);

                   }
                }
                 else {
                        return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
                    } 
                }
                else {
                    return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {

                

                   Optional optional = productDao.findById(id);
                   if (!optional.isEmpty()) {
                        productDao.deleteById(id);
                        return CafeUtils.getResponseEntity("Product deleted successfully", HttpStatus.OK);
                   } 
                   return CafeUtils.getResponseEntity("Product Id does not exist", HttpStatus.OK);     
                }
                else {
                    return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requeMap) {
        try {
            if (jwtFilter.isAdmin()) {

                Optional optional = productDao.findById(Integer.parseInt(requeMap.get("id")));
                   if (!optional.isEmpty()) {
                        productDao.updateProductStatus(requeMap.get("status"),Integer.parseInt(requeMap.get("id")));
                        return CafeUtils.getResponseEntity("Product status updated successfully", HttpStatus.OK);
                   } 
                   return CafeUtils.getResponseEntity("Product Id does not exist", HttpStatus.OK);     
                }
                else {
                    return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        try {

            return new ResponseEntity<>(productDao.getProductByCategory(id), HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductWrapper> getProductById(Integer id) {
        try {

            return new ResponseEntity<>(productDao.getProductById(id), HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getRecipe(String query, Integer number, String information) {
        log.info("INSIDE GET RECIPE!!!!!!!");
        try{
            log.info("SUCCESS");
            //get list of recipes
            List<Recipe> recipes = getRecipeByName(query, number, information);

            //List<Recipe> -> JsonArray
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            //add each recipe -> jsonobject
            recipes.forEach(r -> arrayBuilder.add(r.toJson()));

            JsonArray resp = arrayBuilder.build();
            log.info("RESPONSE IS: " + resp);

            return ResponseEntity.ok(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
            
    }

    @Override
    public ResponseEntity<String> getRecipeByCuisine(String cuisine, Integer number, String information) {
        log.info("INSIDE GET RECIPE BY CUISINE!!!!!!!");
        try{
            log.info("SUCCESS");
            //get list of recipes
            List<Recipe> recipes = getRecipeByCuisines(cuisine, number, information);

            //List<Recipe> -> JsonArray
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            //add each recipe -> jsonobject
            recipes.forEach(r -> arrayBuilder.add(r.toJson()));

            JsonArray resp = arrayBuilder.build();
            log.info("RESPONSE IS: " + resp);

            return ResponseEntity.ok(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public List<Recipe> getRecipeByName (
        String query,
        Integer number, String information
    ) {
        String urlWithAuthParams = createUrlWithAuthParam();
        final String url = UriComponentsBuilder
        .fromUriString(urlWithAuthParams)
        .queryParam("query", query)
        .queryParam("number", number)
        .queryParam("addRecipeInformation", information)
        .toUriString();

        return getRecipe(url);
    }

    public List<Recipe> getRecipeByCuisines (
        String cuisine,
        Integer number, String information
    ) {
        String urlWithAuthParams = createUrlWithAuthParam();
        final String url = UriComponentsBuilder
        .fromUriString(urlWithAuthParams)
        .queryParam("cuisine", cuisine)
        .queryParam("number", number)
        .queryParam("addRecipeInformation", information)
        .toUriString();

        return getRecipe(url);
    }

    private String createUrlWithAuthParam() {
        final String url = UriComponentsBuilder
        .fromUriString(RECIPE_URL)
        .queryParam("apiKey", apiKey)
        .toUriString();

        log.info("URL IS:", url);
        return url;
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
