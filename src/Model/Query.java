package Model;

import java.util.ArrayList;
import java.util.List;

/** The {@code Query} class represents the criteria pass by the user to filter {@code Movie} of a database.
 *
 * @author Bassani, Santiago
 * @version 1.0
 */

public class Query {

    private int minYear;
    private int maxYear;
    private List<String> genres;
    private List<Actor> actors;

    /**Creates a {@code Query} with specified range of year, genres and specified actors.
     * @param minYear The Query's bottom of the years range.
     * @param maxYear The Query's top of the years range.
     * @param genres The Query's genres.
     * @param actors The Query's actors.
     */

    public Query(int minYear, int maxYear, ArrayList<String> genres, ArrayList<Actor> actors) {

        this.minYear = minYear;
        this.maxYear = maxYear;
        this.genres = genres;
        this.actors = actors;
    }

    /**Creates a {@code Query} with the range of year, genres and actors initialized with default values.
     */

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

    public int getMinYear() {
        return minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    /** Returns true if the movie fulfill the specified requirements of the {@code Query}
     * @param  movie a specified {@code Movie}.
     *@return a boolean that represents if the criteria evaluated is true or false.
     */

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
