package nhquang.a2.Controllers;

import nhquang.a2.Models.Movie;
import nhquang.a2.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping("/movies")
    public ResponseEntity getMovies(){
        try {
            return new ResponseEntity(service.getAllMovies(), HttpStatus.OK);
        }
        catch(Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/movies/featured")
    public ResponseEntity getFeaturedMovies(){
        try {
            return new ResponseEntity(service.getFeaturedMovies(), HttpStatus.OK);
        }
        catch(Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getAMovie(@PathVariable("id") String id){
        try{
            return  new ResponseEntity(service.getAMovie(id), HttpStatus.OK);
        }
        catch(Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/movies/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateMovie(@PathVariable("id") String id, @RequestBody Movie movie){
        try {
            Movie updated = service.updateMovie(id, movie);
            return new ResponseEntity(updated, HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMovie(@RequestBody Movie movie)
    {
        try {
            service.addMovie(movie);
            return new ResponseEntity(movie, HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id){
        try {
            service.deleteAMovie(id);
            return new ResponseEntity("Deleted!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
