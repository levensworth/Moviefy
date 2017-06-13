package Test;

import Model.Application;
import org.junit.Test;

import java.io.FileNotFoundException;

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
}
