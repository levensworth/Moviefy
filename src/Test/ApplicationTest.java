package Test;

import Model.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by SB on 12/06/2017.
 */
public class ApplicationTest {


    @Test
    public void getDirectorTest() {
        Application app = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(app.getDirector((long) 3).getName(), "James Cameron");
    }

    @Test

    public void testAdd() {
        String str = "Junit is working fine";
        assertEquals("Junit is working fine", str);
    }


    @Test
    public void getMoviesSizeTest() {
        Application app = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Person testPerson = new Person(1, "Joel David Moore", app);

        assertEquals(app.getMovies(testPerson).size(), 7);

    }

    @Test
    public void getAllMoviesSizeTest() {
        Application app = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(app.getAllMovies().size(), 5043);
    }

    @Test

    public void getAllMoviesQueryTest() {
        Application app = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Actor a = app.getActor((long) 24);
        ArrayList<Actor> arr = new ArrayList<Actor>();
        arr.add(a);
        Query q = new Query().setMinYear(0).setMaxYear(Calendar.getInstance().get(Calendar.YEAR))
                .setActor(arr);
        assertEquals(app.getAllMovies(q), a.getMovies());
    }


}


