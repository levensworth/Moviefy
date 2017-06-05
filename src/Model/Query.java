package Model;

import java.util.ArrayList;
import java.util.List;

public class Query {
   /*
    * this class contains the params
    * that the user wants in a movie
    *
    *
    */

    private int minYear;
    private int maxYear;
    private List<String> genres;
    private List<Actor> actors;

    public int getMinYear() {
        return minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Query(int minYear, int maxYear, ArrayList<String> genres, ArrayList<Actor> actors) {

        this.minYear = minYear;
        this.maxYear = maxYear;
        this.genres = genres;
        this.actors = actors;
    }

    public Query() {
        minYear = 0;
        maxYear = 0;
        genres = null;
        actors = null;
    }

    public Query setMinYear(int year) {
        this.minYear = year;
        return this;
    }

    public Query setMaxYear(int year) {
        this.maxYear = year;
        return this;
    }

    public Query setActor(List<Actor> actors) {
        this.actors = actors;
        return this;
    }

    public Query setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }



    public boolean validate(Movie movie) {
        if (!(movie.getYear() >= minYear && movie.getYear() <= maxYear)) {

            return false;
        }

        boolean found = false;
        if (actors != null) {

            for (Actor act : actors) {
                if (act.getMovies().contains(movie))
                    found = true;
            }
            if (!found)
                return false;
        }

        if (genres != null) {
            found = false;
            for (String genre : movie.getGenre()) {
                if (genres.contains(genre)) {
                    found = true;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }
}
