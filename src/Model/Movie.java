package Model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import Model.imdbScrapper.*;

    /** The {@code Movie} class represents a Movie in this system.
     *
     * @author Aquili, Alejo Ezequiel
     * @version 1.0
     */

public class Movie {

    private String title;
    private Long directorID;
    private String synopsis;
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

    /**This extract specified information of a specified URL. This parameter provides an URL of a poster and String of
     * the movie synopsis get from a specified URL*/
    private MovieScrapper scrapper;
    private String language;

    /** Holds the referenece to the class {@code Application} which contains the collections of {@code People}
     * and {@code Movie} .
     */
    private Application application;

    /**Creates a {@code Movie} with the specified title, a id of a specified director, a piece of the cast with id's
     * of a group of actors, a specified year, lenguage, country and duration of the movie, the IMDb score and link,
     * the number of reviews, the PEGI ratings, the genres and tags of the movie. Finally the specified
     * {@code Application} and {@code MovieScrapper}.
     * @param title The movie title.
     * @param directorID The id of the movie director.
     * @param actorsID The ids of the actors.
     * @param year The year of the movie.
     * @param lenguage The lenguage of the movie.
     * @param country The contry of the movie.
     * @param IMDbScore The Score of the movie in IMDb.
     * @param tags The tags of the movie.
     * @param IMDbLink The link of the movie in IMDb.
     * @param reviewQty The number of review of the movie.
     * @param duration The duration of the movie.
     * @param contentRating The PEGI ratings of the movie.
     * @param genre The genres of the movie.
     * @param scrapper The Scrapper of the movie.
     * @param application The Application of the movie.
     */

    public Movie(String title, Long directorID, Collection<Long> actorsID, Integer year,
                 String lenguage, String country, Double IMDbScore, Collection<String> tags, URL IMDbLink,
                 Long reviewQty, Long duration, String contentRating, Collection<String> genre,
                 MovieScrapper scrapper, Application application) {
        this.title = title;
        this.directorID = directorID;
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
        this.language = language;
        this.synopsis = null;
        this.application = application;
    }

    public String getTitle(){
        return title;
    }

    public Long getDirectorID(){
        return directorID;
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

    public URL getPosterURL() throws IOException{
        if(!scrapper.hasLink()) scrapper.setIMDbLink(getIMDbLink().toString());
        return new URL(scrapper.scrapPosterURL());
    }

    public String getSynopsis() throws IOException{
        if(!scrapper.hasLink()) scrapper.setIMDbLink(getIMDbLink().toString());
        if(synopsis == null)    synopsis = scrapper.scrapSynopsis();
        return synopsis;
    }

    public MovieScrapper getScrapper() {
        return scrapper;
    }

    public String getLanguage() {

        return language;
    }

    public Actor getActor(Long id) {
        return application.getActor(id);
    }


    public Director getDirector(Long id) {
        return application.getDirector(id);
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(this.getClass() == obj.getClass()){
            Movie aux = (Movie)obj;

            return (title.equals(aux.getTitle()) && year.equals(aux.getYear()) && directorID.equals(aux.getDirectorID()));
        }
        return false;
    }

    @Override
    public String toString(){
        return title + " (" + year + "), " + lenguage + ", " + IMDbScore + ", " + IMDbLink;
    }
}
