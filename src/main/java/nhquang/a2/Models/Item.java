package nhquang.a2.Models;


public abstract class Item {

    protected String url;
    protected String name;
    protected String overview;
    protected String[] genres;
    protected String rent;
    protected String buy;
    protected String releaseDate;
    protected String trailer;
    protected String poster;

    public Item(String url, String name, String overview, String[] genres,String buy, String rent, String releaseDate, String trailer, String poster) {
        this.url = url;
        this.name = name;
        this.overview = overview;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.trailer = trailer;
        this.poster = poster;
        this.buy = buy;
        this.rent = rent;
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate(){
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public String[] getGenres() {
        return this.genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }
}
