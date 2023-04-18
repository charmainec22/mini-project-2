package miniproject2.server.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import miniproject2.server.wrapper.ProductWrapper;

public interface ProductService {

    ResponseEntity<String> addNewProduct(Map<String, String> requeMap);

    ResponseEntity<List<ProductWrapper>> getAllProduct();

    ResponseEntity<String> updateProduct(Map<String, String> requeMap);

    ResponseEntity<String> deleteProduct(Integer id);

    ResponseEntity<String> updateStatus(Map<String, String> requeMap);

    ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

    ResponseEntity<ProductWrapper> getProductById(Integer id);

    ResponseEntity<String> getRecipe(String query, Integer number, String information);

	ResponseEntity<String> getRecipeByCuisine(String cuisine, Integer number, String information);
    
}
