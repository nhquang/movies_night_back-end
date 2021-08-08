package nhquang.a2.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;


@Document(collection = "Movie")
public class Movie extends Item{
    //POJO// bean// entity
    @Id
    private String _id;
    private String length;


    public Movie(String _id, String url, String name, String overview, String[] genres,String buy, String rent, String releaseDate, String length, String trailer, String poster) {
        super(url, name, overview, genres, buy, rent, releaseDate, trailer, poster);
        this.length = length;
        this._id = _id;
    }

    public String getId() {
        return this._id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getLength() {
        return this.length;
    }

    public void setLength(String length) {
        this.length = length;
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
                ", length:'" + length + '\'' +
                ", trailer:'" + trailer + '\'' +
                ", poster:'" + poster + '\'' +
                '}';
    }
}