package nhquang.a2.Services;

import com.mongodb.client.model.Filters;
import nhquang.a2.Models.Movie;
import nhquang.a2.Models.MovieRepository;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> getAllMovies(){
        List<Movie> movies = repository.findAll();
        return movies;
    }
    public Optional<Movie> getAMovie(String id) throws Exception{
        Optional<Movie> movie = repository.findById(id);
        if(movie.isEmpty())
            throw new Exception("Not Found!");
        return movie;
    }
    public List<Movie> getMoviesByName(String name) {

        String patternStr = "[a-zA-Z0-9\s]*" + name + "[a-zA-Z0-9\s]*";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(pattern));
        List<Movie> movies = mongoTemplate.find(query,Movie.class);
        return movies;
    }
    public List<Movie> getFeaturedMovies() {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured").is(true));
        List<Movie> movies = mongoTemplate.find(query,Movie.class);
        return movies;
    }

    public void addMovie(Movie movie) throws Exception{
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(movie.getName()));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        if(movies.size() == 0)
            repository.insert(movie);
        else
            throw new Exception("Movie already exists!");
    }

    public Movie updateMovie(String id, Movie updatedMovie) throws Exception{
        Optional<Movie> movie = repository.findById(id);
        if(movie.isEmpty())
            throw new Exception("Not Found!");
        movie.get().setName(updatedMovie.getName());
        movie.get().setUrl(updatedMovie.getUrl());
        movie.get().setGenres(updatedMovie.getGenres());
        movie.get().setBuy(updatedMovie.getBuy());
        movie.get().setRent(updatedMovie.getRent());
        movie.get().setReleaseDate(updatedMovie.getReleaseDate());
        movie.get().setOverview(updatedMovie.getOverview());
        movie.get().setLength(updatedMovie.getLength());
        movie.get().setPoster(updatedMovie.getPoster());
        movie.get().setTrailer(updatedMovie.getTrailer());
        return repository.save(movie.get());
    }

    public void deleteAMovie( String id) throws Exception
    {
        Optional<Movie> movie = repository.findById(id);
        if(movie.isEmpty())
            throw new Exception("Not Found!");
        repository.deleteById(id);
    }
}
