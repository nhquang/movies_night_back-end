package nhquang.a2.Models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "TVShow")
public class TVShow extends Item{

    @Id
    private String _id;
    private String seasons;
    private String episodes;

    public TVShow(String _id, String url, String name, String overview, String[] genres, String episodes, String seasons, String buy, String rent, String releaseDate, String trailer, String poster) {
        super(url, name, overview, genres, buy, rent, releaseDate, trailer, poster);
        this.episodes = episodes;
        this.seasons = seasons;
        this._id = _id;
    }


    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getSeasons() {
        return this.seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {

        return "{" +
                "id:" + _id +
                ", url:'" + url + '\'' +
                ", name:'" + name + '\'' +
                ", overview:'" + overview + '\'' +
                ", genres:" + Arrays.toString(genres) +
                ", rent:'" + rent + '\'' +
                ", buy:'" + buy + '\'' +
                ", releaseDate:'" + releaseDate + '\'' +
                ", seasons:'" + seasons + '\'' +
                ", episodes:'" + episodes + '\'' +
                ", trailer:'" + trailer + '\'' +
                ", poster:'" + poster + '\'' +
                '}';
    }
}
