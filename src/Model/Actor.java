package Model;

import java.util.Collection;
import java.util.List;

public class Actor extends Person {


    public Actor(long id, String name, Application app) {
        super(id, name, app);
    }

    public Collection<Movie> getMovies() {
        return getApp().getMovies(this);
    }

}
