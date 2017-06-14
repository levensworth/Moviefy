package Model;

    /** The {@code MovieFeedBack} class represents a set of values that broadcast the user feedback with a
     * specified {@code Movie}.
     *
     * @author Bassani, Santiago
     * @version 1.0
     */

public class MovieFeedBack {

    private Movie movie;
    /**The raiting is an instance variable of type Built-in integer that represents the user punctuation*/
    private int rating;

    /**Creates a {@code MovieFeedBack}, with a specified {@code Movie} and a specified punctuation for this movie.
     * @param movie The MovieFeedBack {@code Movie}
     * @param  rating The MovieFeedBack raiting of the movie.
     */

    public MovieFeedBack(Movie movie, int rating) {
        this.movie = movie;
        this.rating = rating;
    }


    public Movie getMovie() {
        return movie;
    }

    public int getRating() {
        return rating / 10;
    }
}
