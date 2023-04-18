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
import miniproject2.server.rest.UserRest;
import miniproject2.server.service.UserService;
import miniproject2.server.utils.CafeUtils;
import miniproject2.server.wrapper.UserWrapper;

@Slf4j
@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {

            return userService.signUp(requestMap);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        
        try {
            return userService.login(requestMap); 
        } catch (Exception e) {
           e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
       try {
        
        return userService.getAllUser();

       } catch (Exception e) {
            e.printStackTrace();
       }

       return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            log.info("time to update");
            return userService.update(requestMap);
        } catch (Exception e) {
           e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try {

          return  userService.checkToken(); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requeMap) {
       try {

        return userService.changePassword(requeMap);
        
       } catch (Exception e) {
            e.printStackTrace();
       }

       return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requeMap) {
        try {

            return userService.forgotPassword(requeMap);
            
           } catch (Exception e) {
                e.printStackTrace();
           }
    
           return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
