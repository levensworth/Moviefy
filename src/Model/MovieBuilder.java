package Model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class MovieBuilder {

    private String title;
    private Long directorID;
    private String sinopsis;
    private Collection<Long> actorsID;
    private Integer year;
    private String lenguage;
    private String country;
    private Double IMDbScore;
    private Collection<String> tags;
    private URL IMDbLink;
    private Long reviewQty;
    private Long duration;
    private String contentRating;
    private Collection<String> genre;
    private PosterScrapper scrapper;

    public MovieBuilder(){
        this.title = null;
        this.directorID = 0L;
        this.sinopsis = null;
        this.actorsID = new ArrayList<Long>();
        this.year = 0;
        this.lenguage = null;
        this.country = null;
        this.IMDbScore = 0.0;
        this.tags = new ArrayList<String>();
        this.IMDbLink = null;
        this.reviewQty = 0L;
        this.duration = 0L;
        this.contentRating = null;
        this.genre = new ArrayList<String>();
        this.scrapper = null;
    }

    public MovieBuilder setTitle(String title) {
        if(title == null) throw new IllegalArgumentException("title cannot be null");
        this.title = title;
        return this;
    }

    public MovieBuilder setDirectorID(Long directorID) {
        if(directorID == null) throw new IllegalArgumentException("directorID cannot be null");
        this.directorID = directorID;
        return this;
    }

    public MovieBuilder setSinopsis(String sinopsis) {
        if(sinopsis == null) throw new IllegalArgumentException("sinopsis cannot be null");
        this.sinopsis = sinopsis;
        return this;
    }

    public MovieBuilder setActorsID(Collection<Long> actorsID) {
        if(actorsID == null) throw new IllegalArgumentException("actorsID cannot be null");
        this.actorsID = new ArrayList<Long>(actorsID);
        return this;
    }

    public MovieBuilder setYear(Integer year) {
        if(year < 0) throw new IllegalArgumentException("year cannot be negative");
        this.year = year;
        return  this;
    }

    public MovieBuilder setLenguage(String lenguage){
        if(lenguage == null) throw  new IllegalArgumentException("lenguage cannot be null");
        this.lenguage = lenguage;
        return this;
    }

    public MovieBuilder setCountry(String country){
        if(country == null) throw  new IllegalArgumentException("country cannot be null");
        this.country = country;
        return this;
    }

    public MovieBuilder setIMDbScore(Double IMDbScore){
        if(IMDbScore == null) throw new IllegalArgumentException("IMDbScore cannot be null");
        this.IMDbScore = IMDbScore;
        return this;
    }

    public MovieBuilder setTags(Collection<String> tags) {
        if(tags == null) throw new IllegalArgumentException("tags cannot be null");
        this.tags = new ArrayList<String>(tags);
        return this;
    }

    public MovieBuilder setIMDbLink(URL IMDbLink) {
        if(IMDbLink == null) throw new IllegalArgumentException("IMDbLink cannot be null");
        this.IMDbLink = IMDbLink;
        return this;
    }

    public MovieBuilder setReviewQty(Long reviewQty) {
        if(reviewQty < 0) throw new IllegalArgumentException("reviewQty cannot be negative");
        this.reviewQty = reviewQty;
        return this;
    }

    public MovieBuilder setDuration(Long duration) {
        if(duration < 0) throw  new IllegalArgumentException("duration cannot be negative");
        this.duration = duration;
        return this;
    }

    public MovieBuilder setContentRating(String contentRating){
        if(contentRating == null) throw new IllegalArgumentException("contentRating cannot be null");
        this.contentRating = contentRating;
        return this;
    }

    public MovieBuilder setGenre(Collection<String> genre) {
        if(genre == null) throw  new IllegalArgumentException("genre cannot be null");
        this.genre = new ArrayList<String>(genre);
        return this;
    }

    public MovieBuilder setScrapper(PosterScrapper scrapper) {
        if(scrapper == null) throw  new IllegalArgumentException("scrapper cannot be null");
        this.scrapper = scrapper;
        return this;
    }

    public Movie builder(){
        return new Movie(title, directorID, sinopsis, actorsID, year, lenguage, country, IMDbScore, tags,
                IMDbLink, reviewQty, duration, contentRating, genre, scrapper);
    }
}
