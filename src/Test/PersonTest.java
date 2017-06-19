package Test;
import Model.Application;
import Model.Movie;
import Model.Person;
import Model.imdbScrapper.HDScrapper;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dn on 16/06/17.
 */
public class PersonTest {
    @Test
    public void IllegalArgumentOnPersonConstructorTest() {
        try {
            Person person = new Person(10, "Alan", null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CheckingPersonEqualityTest() {
        Person person1 = null;
        Person person2 = null;
        try {
            person1 = new Person(1, "Alan", new Application("db/movieDB.csv", "db/personDB.csv"));
            person2 = new Person(1, "Carlitos", new Application("db/movieDB.csv", "db/personDB.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(true, person1.equals(person2));
    }

    @Test
    public void ToStringMethodTest() {
        Person person1 = null;
        try {
            person1 = new Person(1, "Alan", new Application("db/movieDB.csv", "db/personDB.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals("Alan", person1.toString());
    }

    @Test
    public void GettingMoviesByPersonTest() {
        Person person = null;
        List<Movie> movies = new ArrayList<Movie>();
        try {
            Collection<Long> actorIds = new ArrayList<Long>();
            actorIds.add(8L);
            actorIds.add(9L);
            actorIds.add(10L);
            Collection<String> tags = new ArrayList<String>();
            tags.add("bomb");
            tags.add("espionage");
            tags.add("sequel");
            tags.add("spy");
            tags.add("terrorist");
            Collection<String> genres = new ArrayList<String>();
            genres.add("Action");
            genres.add("Adventure");
            genres.add("Thriller");
            Application app = new Application("db/movieDB.csv", "db/personDB.csv");
            person = new Person(10, "Stephanie Sigman", app);
            movies.add(new Movie("Spectre", 11L, actorIds, 2015, "English", "UK", 6.8,
                    tags, new URL("http://www.imdb.com/title/tt2379713/?ref_=fn_tt_tt_1"), 602L, 148L, "PG-13",
                    genres, null, app));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(true, movies.equals(person.getMovies()));
    }}
