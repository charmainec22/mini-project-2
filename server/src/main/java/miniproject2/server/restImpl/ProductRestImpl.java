package miniproject2.server.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import miniproject2.server.constants.CafeConstants;
import miniproject2.server.dao.CategoryDao;
import miniproject2.server.dao.ProductDao;
import miniproject2.server.rest.ProductRest;
import miniproject2.server.service.ProductService;
import miniproject2.server.utils.CafeUtils;
import miniproject2.server.wrapper.ProductWrapper;

@RestController
@Slf4j
public class ProductRestImpl implements ProductRest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductDao productDao;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requeMap) {
        try {

            return productService.addNewProduct(requeMap);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {

            return productService.getAllProduct();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requeMap) {
        try {

            return productService.updateProduct(requeMap);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {

            return productService.deleteProduct(id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requeMap) {
        try {

            return productService.updateStatus(requeMap);
            
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
            log.info("INSIDE PRODUCTBYID");
            return productService.getProductById(id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getRecipe(String query, Integer number, String information) {
        try {
            log.info("INSIDE");
            return productService.getRecipe(query,number, information);
            
        } catch (Exception e) {
            log.info("OUSIDE");
            e.printStackTrace();
        }
        log.info("test");
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> getRecipeByCuisine(String cuisine, Integer number, String information) {
        try {
            log.info("INSIDE");
            return productService.getRecipeByCuisine(cuisine,number,information);
            
        } catch (Exception e) {
            log.info("OUSIDE");
            e.printStackTrace();
        }
        log.info("test");
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
