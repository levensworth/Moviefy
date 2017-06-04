package Model;

import org.omg.PortableInterceptor.INACTIVE;

import javax.management.relation.RoleUnresolved;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by SB on 04/06/2017.
 */
public class Application {
    private List<Movie> movies;
    private Map<Long, Person> persons;


    public Application(String moviePath, String personPath) {
        CSVReader movieReader;
        CSVReader personReader;
        try {
            movieReader = new CSVReader(moviePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("the file %s was not found", moviePath));
        }

        try {
            personReader = new CSVReader(personPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("the file %s was not found", moviePath));
        }

        createPersonCollection(personReader);
        createMovieColletion(movieReader);


    }

    private void createMovieColletion(CSVReader movieReader) {
        // read the csv file and creates a collection of movies

        movies = new ArrayList<Movie>();

        for (Collection<String> line : movieReader) {
            Object[] vector = line.toArray();
            movies.add(createMovie(vector));
        }
    }


    private void createPersonCollection(CSVReader personReader) {
        //creates a map of people, which uses an id as an identifier
        persons = new HashMap<Long, Person>();
        for (Collection<String> line : personReader) {
            Object[] vector = line.toArray();
            persons.put(Long.valueOf((String) vector[0]), new Person(Long.valueOf((String) vector[0]), (String) vector[1], this));
        }

    }


    public Collection<Movie> getMovies(Person person) {
        //should return the collection of movies in which the person appears either as actor or a director
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getActorsID().contains(person.getId()) || m.getDirectorID() == person.getId())
                result.add(m);
        }

        return result;
    }


    public Collection<Movie> getMovies(Actor actor) {
        //returns the collection of movies in which the actor appears
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getActorsID().contains(actor.getId()))
                result.add(m);
        }

        return result;
    }

    public Collection<Movie> getMovies(Director director) {
        //returns the collection of movies that this director have
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getDirectorID() == director.getId())
                result.add(m);
        }

        return result;
    }

    private Movie createMovie(Object[] vector) {
        Movie m;
        try {
            m = new MovieBuilder().setTitle(((String) vector[1]).toLowerCase())
                    .setDirectorID((!vector[2].toString().equals("")) ? Long.valueOf((String) vector[2]) : 0)
                    .setActorsID(searchActors(vector))
                    .setDuration((!vector[6].toString().equals("")) ? Long.valueOf((String) vector[6]) : 0)
                    .setYear((!vector[7].toString().equals("")) ? Integer.valueOf((String) vector[7]) : 0)
                    .setGenre(searchGenres(vector))
                    .setIMDbScore((!vector[9].toString().equals("")) ? Double.valueOf((String) vector[9]) : 0)
                    .setCountry((String) vector[13])
                    .setTags(searchTags(vector))
                    .setIMDbLink(new URL((String) vector[15]))
                    .setLanguage((String) vector[12])
                    .builder();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("the movie %s couldn't be created", vector[0].toString()));
        } catch (NumberFormatException e) {
            for (int i = 0; i < vector.length; i++) {
                System.out.println(vector[i]);
            }
            throw new RuntimeException(String.format("the movie %s couldn't be created", vector[0].toString()));
        }

        return m;

    }

    private List<Long> searchActors(Object[] vector) {
        ArrayList<Long> actors = new ArrayList<>();
        actors.add(Long.valueOf((String) vector[3]));
        actors.add(Long.valueOf((String) vector[4]));
        actors.add(Long.valueOf((String) vector[5]));
        return actors;
    }

    private List<String> searchGenres(Object[] vector) {
        ArrayList<String> genre = new ArrayList<>();
        String[] genres = ((String) vector[8]).split("\\|");
        for (int i = 0; i < genres.length; i++) {
            genre.add(genres[i].toLowerCase());
        }
        return genre;
    }

    private List<String> searchTags(Object[] vector) {
        ArrayList<String> tags = new ArrayList<>();

        String[] tag = vector[14].toString().split("\\|");
        for (int i = 0; i < tag.length; i++) {
            tags.add(tag[i]);
        }

        return tags;
    }

    public Actor getActor(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("the id must be a positive integer");
        }

        if (persons.containsKey(id))
            return (Actor) persons.get(id);

        throw new RuntimeException(String.format("the id %l was not found", id));
    }

    public Director getDirector(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("the id must be a positive integer");
        }

        if (persons.containsKey(id))
            return (Director) persons.get(id);

        throw new RuntimeException(String.format("the id %l was not found", id));
    }

}
