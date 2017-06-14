package Model;

import java.util.Collection;
import java.util.List;

    /** The {@code Actor} class represents an actor in this system.
     *
     * @author Donoso Naumczuk, Alan Nicolas
     * @version 1.0
     */

public class Actor extends Person {

    /** Creates an {@code Actor} with a specified ID number name and {@code Application}.
     * @param id The actor ID.
     * @param name The actor name.
     * @param app The specified {@code Application} for the {@code Actor}.
     */

    public Actor(long id, String name, Application app) {
        super(id, name, app);
    }

    /** This method ask the actor's specified {@code Application} to get all movies in which the person is actor.
     * @return a {@code Collection} of movies acted by this.
     */

    public Collection<Movie> getMovies() {
        return getApp().getMovies(this);
    }

}
