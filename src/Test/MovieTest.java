package Test;

/**
 * Created by SB on 15/06/2017.
 */

import Model.Application;
import Model.Movie;
import Model.MovieBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void MovieBuilder() {

        Application app = null;
        Movie mb = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");

            mb = new MovieBuilder()
                    .setYear(2009)
                    .setApplication(app)
                    .setContentRating("7.9")
                    .setCountry("USA")
                    .setDuration((long) 178)
                    .setIMDbLink(new URL("http://www.imdb.com/title/tt0499549/?ref_=fn_tt_tt_1"))
                    .setLenguage("English")
                    .setTitle("Avatar")
                    .builder();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        }

        assertEquals(mb.getContentRating(), "7.9");
        assertEquals(mb.getCountry(), "USA");
        assertEquals((long) mb.getDuration(), (long) 178);
        assertEquals(mb.getIMDbLink().toString(), "http://www.imdb.com/title/tt0499549/?ref_=fn_tt_tt_1");

    }


    @Test
    public void EqulasMovieTest() {
        Application app = null;
        Movie mb = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");

            mb = new MovieBuilder()
                    .setYear(2009)
                    .setApplication(app)
                    .setContentRating("7.9")
                    .setCountry("USA")
                    .setDuration((long) 178)
                    .setIMDbLink(new URL("http://www.imdb.com/title/tt0499549/?ref_=fn_tt_tt_1"))
                    .setLenguage("English")
                    .setTitle("Avatar")
                    .builder();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        assertEquals(mb.equals(mb), true);
        assertEquals(mb.equals(app.getAllMovies().get(3)), false);
    }

}
