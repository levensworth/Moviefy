package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** The {@code Director} class represents an director in this system.
 *
 * @author Donoso Naumczuk, Alan Nicolas
 * @version 1.0
 */

public class Director extends Person{

    /** Creates an {@code Director} with a specified ID number name and {@code Application}.
     * @param id The director ID.
     * @param name The director name.
     * @param app The specified {@code Application} for the {@code Director}.
     */

    public Director(long id, String name, Application app) {
        super(id, name, app);
    }

    //i belive this method should not be implemented in actor nor director
    //cause , the way  i define the parameters of get movie makes it return the right information
    //as the argument @this will change depending of the object from which it was fired

    /** This method ask the director's specified {@code Application} to get all movies in which the person is director.
      * @return a {@code Collection} of movies directed by this.
     */

    public Collection<Movie> getMovies() {
        return getApp().getMovies(this);
    }




}
