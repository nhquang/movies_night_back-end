package nhquang.a2.Controllers;

import nhquang.a2.Models.Item;
import nhquang.a2.Models.Movie;
import nhquang.a2.Models.TVShow;
import nhquang.a2.Services.MovieService;
import nhquang.a2.Services.TVShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class SearchController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private TVShowService tvShowService;


    @GetMapping("/search")
    public ResponseEntity getItemsByName(@RequestParam String name){
        try {
            List<Item> rs = new ArrayList<Item>();
            rs.addAll(movieService.getMoviesByName(name));
            rs.addAll(tvShowService.getTVShowsByName(name));
            return new ResponseEntity(rs, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/banners")
    public ResponseEntity getBanners(){
        try {
            List<Item> rs = new ArrayList<Item>();
            rs.addAll(movieService.getFeaturedMovies());
            rs.addAll(tvShowService.getFeaturedTVShows());
            String[] banners = new String[rs.size()];
            for (int i = 0; i < banners.length; i++){
                banners[i] = rs.get(i).getPoster();
            }
            return new ResponseEntity(banners, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
