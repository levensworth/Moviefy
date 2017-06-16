package Test;

import Model.Actor;
import Model.Application;
import Model.Query;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by SB on 15/06/2017.
 */
public class QueryTest {


    @Test
    public void QueryCreationTest() {
        Query q = new Query()
                .setMaxYear(2000)
                .setMinYear(300);
        assertEquals(q.getMaxYear(), 2000);
        assertEquals(q.getMinYear(), 300);
    }

    @Test
    public void QueryEvalTest() {
        Application app = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Actor a = app.getActor((long) 24);
        ArrayList<Actor> arr = new ArrayList<Actor>();
        arr.add(a);


        Query q = new Query()
                .setMaxYear(2017)
                .setMinYear(300)
                .setActor(arr);

        assertEquals(app.getAllMovies(q), a.getMovies());
    }

}
