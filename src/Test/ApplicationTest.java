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
            app = new Application("db/movieDB.csv", "db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("James Cameron", app.getDirector((long) 3).getName());
    }



    @Test
    public void getMoviesSizeTest() {
        Application app = null;
        try {
            app = new Application("db/movieDB.csv", "db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Person testPerson = new Person(1, "Joel David Moore", app);

        assertEquals(7, app.getMovies(testPerson).size());

    }

    @Test
    public void getAllMoviesSizeTest() {
        Application app = null;
        try {
            app = new Application("db/movieDB.csv", "db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(5043, app.getAllMovies().size());
    }

    @Test

    public void getAllMoviesQueryTest() {
        Application app = null;
        try {
            app = new Application("db/movieDB.csv", "db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Actor a = app.getActor((long) 24);
        ArrayList<Actor> arr = new ArrayList<Actor>();
        arr.add(a);
        Query q = new Query().setMinYear(0).setMaxYear(Calendar.getInstance().get(Calendar.YEAR))
                .setActor(arr);
        assertEquals(a.getMovies(), app.getAllMovies(q));
    }


}


