package Model;

import java.util.ArrayList;
import java.util.List;

public class Director extends Person{
    public Director(long id, String name, Application app) {
        super(id, name, app);
    }

    public List<Movie> getMovies() {
        //this method ask the app to get all movies in which the person is a director

        return new ArrayList<Movie>();

    }


}
