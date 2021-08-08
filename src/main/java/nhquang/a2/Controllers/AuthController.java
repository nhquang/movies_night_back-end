package nhquang.a2.Controllers;

import nhquang.a2.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody User user)
    {


        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            return new ResponseEntity("Succeeded!", HttpStatus.OK);

        }

        catch (BadCredentialsException ex)
        {
            return new ResponseEntity("Failed!", HttpStatus.UNAUTHORIZED);
        }



    }
}
