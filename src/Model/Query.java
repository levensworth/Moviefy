package Model;

import java.util.ArrayList;

/**
 * Created by SB on 02/06/2017.
 */
public class Query {
    /*
    * this class contains the params
    * that the user wants in a movie
    *
    *
    * */

    private int minYear;
    private int maxYear;
    private ArrayList<String> genres;
    private ArrayList<Actor> actors;

    public int getMinYear() {
        return minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public Query(int minYear, int maxYear, ArrayList<String> genres, ArrayList<Actor> actors) {

        this.minYear = minYear;
        this.maxYear = maxYear;
        this.genres = genres;
        this.actors = actors;
    }

    public boolean validate(Movie movie) {
        if (!(movie.getYear() >= minYear && movie.getYear() <= maxYear)) {
            return false;
        }

        for (Actor act : actors) {
            if (!act.getMovies().contains(movie))
                return false;
        }

        for (String genre : movie.getGenre()) {
            if (!genres.contains(genre)) {
                return false;
            }
        }

        return true;
    }
}
