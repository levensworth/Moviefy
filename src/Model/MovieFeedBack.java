package Model;

public class MovieFeedBack {

    private Movie movie;
    private int rating;

    public MovieFeedBack(Movie movie, int rating) {
        this.movie = movie;
        this.rating = rating;
    }


    public Movie getMovie() {
        return movie;
    }

    public int getRating() {
        return rating;
    }
}
