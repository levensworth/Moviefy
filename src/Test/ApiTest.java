package Test;

import Model.Application;
import Model.Movie;
import Model.NeuralNetwork.API;
import Model.Query;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ApiTest {

    @Test
    public void evaluatesApiMax5And7MinRatingNullRecommendation() {
        Application app = null;
        try {
            app = new Application("/Users/SB/Moviefy/db/movieDB.csv", "/Users/SB/Moviefy/db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Query q = new Query().setMinYear(2000).setMaxYear(2017);
        API api = new API(app, 5, 7);
        assertEquals(5, api.getRecommendation(q).size());
    }


}
