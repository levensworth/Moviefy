package Model;

import java.util.List;

public class Actor extends Person {
    private List<Movie> movies;

    public Actor(long id, String name) {
        super(id, name);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
