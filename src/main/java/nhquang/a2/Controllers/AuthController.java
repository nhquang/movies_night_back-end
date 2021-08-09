package nhquang.a2.Controllers;

import nhquang.a2.Models.User;
import nhquang.a2.Services.UserService;
import nhquang.a2.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody User user)
    {


        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            //return new ResponseEntity("Succeeded!", HttpStatus.OK);

        }

        catch (BadCredentialsException ex)
        {
            return new ResponseEntity("Failed!", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails
                = userService.loadUserByUsername(user.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return  new ResponseEntity(token, HttpStatus.ACCEPTED);


    }
}
