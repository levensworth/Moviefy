package Model;

import java.util.List;

public class Director extends Person{
    private List<Movie> movies;

    public Director(long id, String name) {
        super(id, name);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
