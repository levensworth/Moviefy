package Model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import Model.imdbScrapper.*;

public class Movie {

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

    public Movie(String title, Long directorID, String sinopsis, Collection<Long> actorsID, Integer year,
                 String lenguage, String country, Double IMDbScore, Collection<String> tags, URL IMDbLink,
                 Long reviewQty, Long duration, String contentRating, Collection<String> genre,
                 PosterScrapper scrapper){
        this.title = title;
        this.directorID = directorID;
        this.sinopsis = sinopsis;
        this.actorsID = new ArrayList<Long>(actorsID);
        this.year = year;
        this.lenguage = lenguage;
        this.country = country;
        this.IMDbScore = IMDbScore;
        this.tags = new ArrayList<String>(tags);
        this.IMDbLink = IMDbLink;
        this.reviewQty = reviewQty;
        this.duration = duration;
        this.contentRating = contentRating;
        this.genre = new ArrayList<String>(genre);
        this.scrapper = scrapper;
    }

    public String getTitle(){
        return title;
    }

    public Long getDirectorID(){
        return directorID;
    }

    public String getSinopsis(){
        return sinopsis;
    }

    public Collection<Long> getActorsID(){
        return actorsID;
    }

    public Integer getYear(){
        return year;
    }

    public String getLenguage(){
        return lenguage;
    }

    public String getCountry(){
        return country;
    }

    public Double getIMDbScore(){
        return IMDbScore;
    }

    public Collection<String> getTags(){
        return tags;
    }

    public URL getIMDbLink(){
        return IMDbLink;
    }

    public Long getReviewQty(){
        return reviewQty;
    }

    public Long getDuration(){
        return duration;
    }

    public String getContentRating(){
        return  contentRating;
    }

    public Collection<String> getGenre(){
        return genre;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(this.getClass() == obj.getClass()){
            Movie aux = (Movie)obj;
            return (title.equals(aux.getTitle()) && year == aux.getYear() && directorID == aux.getDirectorID());
        }
        return false;
    }
}
