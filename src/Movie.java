import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class Movie {

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

    public Movie(String title, Person director, String sinopsis, Collection<Person> actors, Integer year,
                 Collection<Double> raitings, Collection<String> tags, URL IMDbLink, Long reviewQty, Long duration,
                 Integer nrOfNominations, Integer nrOfWins, Collection<String> genre, PosterScrapper scrapper){
        this.title = title;
        this.director = director;
        this.sinopsis = sinopsis;
        this.actors = new ArrayList<Person>(actors);
        this.year = year;
        this.raitings = new ArrayList<Double>(raitings);
        this.tags = new ArrayList<String>(tags);
        this.IMDbLink = IMDbLink;
        this.reviewQty = reviewQty;
        this.duration = duration;
        this.nrOfNominations = nrOfNominations;
        this.nrOfWins = nrOfWins;
        this.genre = new ArrayList<String>(genre);
        this.scrapper = scrapper;
    }

    public String getTitle(){
        return title;
    }

    public Person getDirector(){
        return director;
    }

    public String getSinopsis(){
        return sinopsis;
    }

    public Collection<Person> getActors(){
        return actors;
    }

    public Integer getYear(){
        return year;
    }

    public Collection<Double> getRaitings(){
        return raitings;
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

    public Integer getNrOfNominations(){
        return nrOfNominations;
    }

    public Integer getNrOfWins(){
        return nrOfWins;
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
            return (title.equals(aux.getTitle()) && year.equals((aux.getYear()) && director.equals(aux.getDirector()));
        }
        return false;
    }
}
