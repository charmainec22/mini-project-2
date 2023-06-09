package miniproject2.server.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import miniproject2.server.wrapper.UserWrapper;



@RequestMapping(path = "/user")
public interface UserRest {
    
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map <String,String> requeMap);

    @PostMapping(path = "/signupAdmin")
    public ResponseEntity<String> signupAdmin(@RequestBody(required = true) Map <String,String> requeMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login (@RequestBody(required = true) Map <String,String> requeMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requeMap);

    @GetMapping(path = "/checkToken")
    ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword (@RequestBody Map <String,String> requeMap);

    @PostMapping(path = "/forgotPassword")
    public ResponseEntity<String> forgotPassword (@RequestBody Map <String,String> requeMap);

   

}
