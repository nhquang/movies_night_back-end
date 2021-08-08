package nhquang.a2.Services;

import nhquang.a2.Models.TVShow;
import nhquang.a2.Models.TVShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class TVShowService {
    @Autowired
    private TVShowRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TVShow> getAllTVShows(){
        List<TVShow> tvShows = repository.findAll();
        return tvShows;
    }

    public Optional<TVShow> getATVShow(String id) throws Exception{
        Optional<TVShow> tvShow = repository.findById(id);
        if(tvShow.isEmpty())
            throw new Exception("Not Found!");
        return tvShow;
    }

    public List<TVShow> getTVShowsByName(String name) {
        String patternStr = "[a-zA-Z0-9\s]*" + name + "[a-zA-Z0-9\s]*";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(pattern));
        List<TVShow> tvShows = mongoTemplate.find(query,TVShow.class);
        return tvShows;
    }
    public List<TVShow> getFeaturedTVShows() {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured").is(true));
        List<TVShow> tvShows = mongoTemplate.find(query,TVShow.class);
        return tvShows;
    }

    public void addTVShow(TVShow tvShow) throws Exception{
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(tvShow.getName()));
        List<TVShow> movies = mongoTemplate.find(query, TVShow.class);
        if(movies.size() == 0)
            repository.insert(tvShow);
        else
            throw new Exception("TVShow already exists!");
    }

    public TVShow updateTVShow(String id, TVShow updatedTVShow) throws Exception{
        Optional<TVShow> tvShow = repository.findById(id);
        if(tvShow.isEmpty())
            throw new Exception("Not Found!");
        tvShow.get().setName(updatedTVShow.getName());
        tvShow.get().setUrl(updatedTVShow.getUrl());
        tvShow.get().setGenres(updatedTVShow.getGenres());
        tvShow.get().setBuy(updatedTVShow.getBuy());
        tvShow.get().setRent(updatedTVShow.getRent());
        tvShow.get().setReleaseDate(updatedTVShow.getReleaseDate());
        tvShow.get().setOverview(updatedTVShow.getOverview());
        tvShow.get().setEpisodes(updatedTVShow.getEpisodes());
        tvShow.get().setSeasons(updatedTVShow.getSeasons());
        tvShow.get().setPoster(updatedTVShow.getPoster());
        tvShow.get().setTrailer(updatedTVShow.getTrailer());

        return repository.save(tvShow.get());
    }

    public void deleteATVShow(String id) throws Exception{
        Optional<TVShow> tvShow = repository.findById(id);
        if(tvShow.isEmpty())
            throw new Exception("Not Found!");
        repository.deleteById(id);
    }
}
