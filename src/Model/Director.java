package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Director extends Person{
    public Director(long id, String name, Application app) {
        super(id, name, app);
    }

    //i belive this method should not be implemented in actor nor director
    //cause , the way  i define the parameters of get movie makes it return the right information
    //as the argument @this will change depending of the object from which it was fired
    public Collection<Movie> getMovies() {
        //this method ask the app to get all movies in which the person is a director
        return getApp().getMovies(this);
    }




}
