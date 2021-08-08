package nhquang.a2.Controllers;

import nhquang.a2.Models.TVShow;
import nhquang.a2.Services.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TVShowController {
    @Autowired
    private TVShowService service;

    @GetMapping("/tvshows")
    public ResponseEntity getTVShows(){
        try {
            return new ResponseEntity(service.getAllTVShows(), HttpStatus.OK);
        }
        catch(Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tvshows/featured")
    public ResponseEntity getFeaturedTVShows(){
        try {
            return new ResponseEntity(service.getFeaturedTVShows(), HttpStatus.OK);
        }
        catch(Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tvshows/{id}")
    public ResponseEntity getTVShow(@PathVariable("id") String id){
        try {
            return new ResponseEntity(service.getATVShow(id), HttpStatus.OK);
        }
        catch(Exception ex){
            return  new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/tvshows/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateTVShow(@PathVariable String id, @RequestBody TVShow tvShow){
        try{
            return new ResponseEntity(service.updateTVShow(id, tvShow), HttpStatus.ACCEPTED);
        }
        catch(Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/tvshows", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addTVShow(@RequestBody TVShow tvShow)
    {
        try {
            service.addTVShow(tvShow);
            return new ResponseEntity(tvShow, HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "tvshows/{id}")
    public ResponseEntity deleteATVShow(@PathVariable("id") String id){
        try {
            service.deleteATVShow(id);
            return new ResponseEntity("Deleted!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
