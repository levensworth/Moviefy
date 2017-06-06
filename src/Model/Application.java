package Model;

import org.omg.PortableInterceptor.INACTIVE;

import javax.management.relation.RoleUnresolved;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;
import java.net.CookieHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

    /** The {@code Application} class represents the container of all {@code Collection} used through the entire system.
     *
     * @author Bassani, Santiago
     * @author Aquili, Alejo Ezequiel
     * @version 1.0
     */

public class Application {
    private List<Movie> movies;
    private Map<Long, Person> persons;

    /**Creates an {@code Application} with a specified movie and person path of its respective databases.
     * @param moviePath The Application's movie database file path.
     * @param personPath The Application's person database file path.
     */

    public Application(String moviePath, String personPath) throws FileNotFoundException {
        CSVReader movieReader;
        CSVReader personReader;
        movieReader = new CSVReader(moviePath);
        personReader = new CSVReader(personPath);
        createPersonCollection(personReader);
        createMovieColletion(movieReader);


    }

    /** Read the csv file of movie database and creates a {@code Collection} of {@code Movie}.
     * @param movieReader the CSVReader to read movies.
     */

    private void createMovieColletion(CSVReader movieReader) {
        movies = new ArrayList<Movie>();
        for (Collection<String> line : movieReader) {
            Object[] vector = line.toArray();
            movies.add(createMovie(vector));
        }
    }

    /** Read the csv file of person database and creates a {@code Map}  of {@code Person}.
     * @param personReader the CSVReader to read persons.
     */

    private void createPersonCollection(CSVReader personReader) {
        persons = new HashMap<Long, Person>();
        for (Collection<String> line : personReader) {
            Object[] vector = line.toArray();
            persons.put(Long.valueOf((String) vector[0]), new Person(Long.valueOf((String) vector[0]), (String) vector[1], this));
        }

    }

    /** Returns a new {@code Collection} of movies in which the person appears either as actor or a director.
     * @param person a {@code Person} to filter movies.
     * @return a new {@code ArrayList<Movie>} with the specified movies.
     */

    public Collection<Movie> getMovies(Person person) {
        ArrayList<Movie> result = new ArrayList<Movie>();
        for (Movie m : movies) {
            if (m.getActorsID().contains(person.getId()) || m.getDirectorID() == person.getId())
                result.add(m);
        }
        return result;
    }

    /**Returns a new {@code Collection} of movies in which the actor appears.
     * @param actor a {@code Actor} to filter movies.
     * @return a new {@code ArrayList<Movie>} with the specified movies.
     */

    public Collection<Movie> getMovies(Actor actor) {
        ArrayList<Movie> result = new ArrayList<Movie>();
        for (Movie m : movies) {
            if (m.getActorsID().contains(actor.getId()))
                result.add(m);
        }

        return result;
    }

    /**Returns a new {@code Collection} of movies in which the director appears.
     * @param director a {@code Director} to filter movies.
     * @return a new {@code ArrayList<Movie>} with the specified movies.
     */

    public Collection<Movie> getMovies(Director director) {
        ArrayList<Movie> result = new ArrayList<Movie>();
        for (Movie m : movies) {
            if (m.getDirectorID() == director.getId())
                result.add(m);
        }

        return result;
    }

    /** Creates a new {@code Movie} with the specified parameters in the input array.
     * @param vector an array of {@code Object}.
     * @return a new {@code Movie} constructed with the specified parameters in the input array.
     */

    private Movie createMovie(Object[] vector) {
        Movie m;
        try {
            m = new MovieBuilder().setTitle(((String) vector[1]))
                    .setDirectorID((!vector[2].toString().equals("")) ? Long.valueOf((String) vector[2]) : 0)
                    .setActorsID(searchActors(vector))
                    .setDuration((!vector[6].toString().equals("")) ? Long.valueOf((String) vector[6]) : 0)
                    .setYear((!vector[7].toString().equals("")) ? Integer.valueOf((String) vector[7]) : 0)
                    .setGenre(searchGenres(vector))
                    .setIMDbScore((!vector[9].toString().equals("")) ? Double.valueOf((String) vector[9]) : 0)
                    .setCountry((String) vector[13])
                    .setTags(searchTags(vector))
                    .setIMDbLink(new URL((String) vector[15]))
                    .setLenguage((String) vector[12])
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

    /** Returns a {@code List} with the ID's of the actors in a input array.
     * @param vector an array of {@code Object}.
     * @return a new {@code ArrayList<Long>} with the specified ID's of the actors.
     */

    private List<Long> searchActors(Object[] vector) {
        ArrayList<Long> actors = new ArrayList<Long>();
        actors.add(Long.valueOf((String) vector[3]));
        actors.add(Long.valueOf((String) vector[4]));
        actors.add(Long.valueOf((String) vector[5]));
        return actors;
    }

    /** Returns a {@code List} with the genres of a movie in the input array.
     * @param vector an array of {@code Object}.
     * @return a new {@code ArrayList<String>} with the genres.
     */

    private List<String> searchGenres(Object[] vector) {
        ArrayList<String> genre = new ArrayList<String>();
        String[] genres = ((String) vector[8]).split("\\|");
        for (int i = 0; i < genres.length; i++) {
            genre.add(genres[i].toLowerCase());
        }
        return genre;
    }

    /** Returns a {@code List} with the tags of a movie in the input array.
     * @param vector an array of {@code Object}.
     * @return a new {@code ArrayList<String>} with the tags.
     */

    private List<String> searchTags(Object[] vector) {
        ArrayList<String> tags = new ArrayList<String>();

        String[] tag = vector[14].toString().split("\\|");
        for (int i = 0; i < tag.length; i++) {
            tags.add(tag[i]);
        }

        return tags;
    }

    /** Returns a {@code Collection} of {@code Movie}, with the movies that validate the specified {@code Query}.
     * @param query a {@code Query} object.
     * @return a new {@code ArrayList<Movie>} with the movies.
     */

    public Collection<Movie> getAllMovies(Query query) {

        ArrayList<Movie> result = new ArrayList<Movie>();
        for (Movie mov : movies) {
            if (query.validate(mov))
                result.add(mov);
        }

        return result;
    }

    /** Returns a {@code Collection} of the movies loaded in this system.
     * @return a unmodifiable {@code Collection} with the movies in the system.
     */

    public Collection<Movie> getAllMovies() {
        return Collections.unmodifiableList(movies);
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
