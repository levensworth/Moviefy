package Model;

import java.util.Collection;
import java.util.Objects;

public class Person {
    private long id;
    private String name;
    private Application app;

    public Person(long id, String name, Application app) {
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