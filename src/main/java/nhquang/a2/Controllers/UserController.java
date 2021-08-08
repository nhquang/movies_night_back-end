package nhquang.a2.Controllers;

import nhquang.a2.Models.User;
import nhquang.a2.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity getAllUsers(){
        try{
            return new ResponseEntity(service.getUsers(), HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getAnUser(@PathVariable("id") String id){
        try{
            return new ResponseEntity(service.getAnUser(id), HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/users", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity createUsers(@RequestBody User user)
    {
        try {
            return new ResponseEntity(service.addUser(user), HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
