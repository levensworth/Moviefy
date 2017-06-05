package Model;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Objects;

/** The {@code Person} class represents a person in this system.
 *
 * @author Aquili, Alejo Ezequiel
 * @author Donoso Naumczuk, Alan Nicolas
 * @version 1.0
 */

public class Person {
    private long id;
    private String name;
    /** Holds the referenece to the class {@code Application} which contains the collections of {@code People}
     * and {@code Movie} .
     */
    private Application app;

    /** Creates a {@code Person} with the specified id, name and {@code Application}.
     * @param id The person id.
     * @param name The person name.
     *@param app The specified {@code Application} for the person.
     */
    public Person(long id, String name, Application app) {
        if(id < 0) throw  new IllegalArgumentException("id cannot be negative");
        if(name == null) throw  new IllegalArgumentException("name cannot be null");
        if(app == null) throw  new IllegalArgumentException("app cannot be null");
        this.id = id;
        this.name = name;
        this.app = app;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Movie> getMovies() {
        return app.getMovies(this);
    }

    @Override
    public String toString() {
        return getName();
    }

    public Application getApp() {
        return app;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)

            return false;
        if(obj == this)
            return true;
        if(obj.getClass() == this.getClass()){
            return this.id == ((Person)obj).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}