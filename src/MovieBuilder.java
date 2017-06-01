import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class MovieBuilder {

    private String title;
    private Person director;
    private String sinopsis;
    private Collection<Person> actors;
    private Integer year;
    private Collection<Double> raitings;
    private Collection<String> tags;
    private URL IMDbLink;
    private Long reviewQty;
    private Long duration;
    private Integer nrOfNominations;
    private Integer nrOfWins;
    private Collection<String> genre;
    private PosterScrapper scrapper;

    public MovieBuilder(){
        this.title = null;
        this.director = null;
        this.sinopsis = null;
        this.actors = new ArrayList<Person>();
        this.year = 0;
        this.raitings = new ArrayList<Double>();
        this.tags = new ArrayList<String>();
        this.IMDbLink = null;
        this.reviewQty = 0L;
        this.duration = 0L;
        this.nrOfNominations = 0;
        this.nrOfWins = 0;
        this.genre = new ArrayList<String>();
        this.scrapper = null;
    }

    public MovieBuilder setTitle(String title) {
        if(title == null) throw new IllegalArgumentException("title cannot be null");
        this.title = title;
        return this;
    }

    public MovieBuilder setDirector(Person director) {
        if(director == null) throw new IllegalArgumentException("director cannot be null");
        this.director = director;
        return this;
    }

    public MovieBuilder setSinopsis(String sinopsis) {
        if(sinopsis == null) throw new IllegalArgumentException("sinopsis cannot be null");
        this.sinopsis = sinopsis;
        return this;
    }

    public MovieBuilder setActors(Collection<Person> actors) {
        if(actors == null) throw new IllegalArgumentException("actors cannot be null");
        this.actors = new ArrayList<Person>(actors);
        return this;
    }

    public MovieBuilder setYear(Integer year) {
        if(year < 0) throw new IllegalArgumentException("year cannot be negative");
        this.year = year;
        return  this;
    }

    public MovieBuilder setRaitings(Collection<Double> raitings) {
        if(raitings == null) throw new IllegalArgumentException("raitings cannot be null");
        this.raitings = new ArrayList<Double>(raitings);
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

    public MovieBuilder setNrOfNominations(Integer nrOfNominations) {
        if(nrOfNominations < 0) throw  new IllegalArgumentException("nrOfNominations cannot be negative");
        this.nrOfNominations = nrOfNominations;
        return this;
    }

    public MovieBuilder setNrOfWins(Integer nrOfWins) {
        if(nrOfWins < 0) throw  new IllegalArgumentException("nrOfWins cannot be negative");
        this.nrOfWins = nrOfWins;
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
        return new Movie(title, director, sinopsis, actors, year, raitings, tags, IMDbLink, reviewQty, duration,
                nrOfNominations, nrOfWins, genre, scrapper);
    }
}
