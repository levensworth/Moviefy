package Model.NeuralNetwork;

import Model.Application;
import Model.Movie;
import Model.MovieFeedBack;
import Model.Query;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;

    /** The {@code API} class is the interface between end user and the neural net
     * it represent's the AI sytem with which the application can interact.
     *
     * @author Bassani, Santiago
     * @version 1.0
     */

public class API {

    private Network net;
    private boolean trained;
    private Application moviedb;
    private ArrayList<String> genres;
    private int maxRecomendaiton = 5;
    private double minRating = 7;

    /**Creates a default neural net which cannot be modify.
     * @param DB a specified {@code Application} Object with the movie data base.
     * @param maxRecomendaiton number of recomendations.
     * @param minRating minimum raiting for recomendations.
     */

    public API(Application DB, int maxRecomendaiton, double minRating) {
        if (DB == null)
            throw new IllegalArgumentException("the API needs a movie data base from where to read");

        moviedb = DB;
        createGenres();
        int input = genres.size();// the input vector created from the DB

        net = new NetworkBuilder()
                .setCost(Cost.COST.MSE)
                .addLayer(0, new Layer(input, 15, Activation.ACTIVATION.LEAKY_RELU))
                .addLayer(1, new Layer(15, 5, Activation.ACTIVATION.LEAKY_RELU))
                .addLayer(2, new Layer(5, 10, Activation.ACTIVATION.LEAKY_RELU))
                .addLayer(3, new OutpuLayer(10, 1, Activation.ACTIVATION.TANH))
                .setEpoch(500)
                .setLearningRate(0.001)
                .setWeightDecay(0.95)
                .setMomentum(0.2)
                .build();


        this.maxRecomendaiton = maxRecomendaiton;
        this.minRating = minRating;

        //train the network
        trained = false;


    }

    /**Returns a {@code Collection<Movie>} with random movies.
     * @param amount {@code Movie} quantity.
     * @param q a specified {@code Query}
     * @return a {@code Collection<Movie>} with the random movies from the recomendation based on the query.
     */

    private Collection<Movie> getRandomMovies(int amount, Query q) {
        Random rand = new Random();
        ArrayList<Movie> list = new ArrayList<>();
        List<Movie> movies = moviedb.getAllMovies(q);
        for (int i = 0; i < amount; i++) {
            list.add(movies.get(abs(rand.nextInt() % movies.size())));
        }

        return list;
    }

    /**Returns {@code Collection<Movie>} with the recomendation and in case of being the first time this is called,
     * returns each movies that have IMDb Score higher than 7. If there where no matching movies with good ratings,
     * this will return a random {@code Collection<Movie>}.
     * @param query a specified {@code Query} object.
     * @return a {@code Collection<Movie>} with the recomendations.
     */

    public Collection<Movie> getRecommendation(Query query) {

        if (trained == false) {
            return getRandomMovies(maxRecomendaiton, query);
        }
        ArrayList<Movie> recomendation = new ArrayList<Movie>();

        for (Movie mov : moviedb.getAllMovies(query)) {
            //neural results are between (-1:1)
            if (prediction(mov) * 10 >= minRating) {
                recomendation.add(mov);
            }
        }
        if (recomendation.size() != 0) {
            Collections.sort(recomendation, new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return (int) (prediction(o1) - prediction(o2));
                }
            });
            //returns the best of the list
            return recomendation.subList(0, maxRecomendaiton);
        }

        return getRandomMovies(maxRecomendaiton, query);

    }

    /** Returns the estimated rating of a movie for the user.
     * @param movie a {@code Movie} to predict the rating for user.
     * @return a Built-in double type with the rating.
     */

    private double prediction(Movie movie) {
        // creates a vector out of the movie's genres and feeds it to the net
        // it's a dummy boolean vector
        INDArray mov = Nd4j.zeros(1, genres.size());

        for (String gen : movie.getGenre()) {
            mov.putScalar(new int[]{0, genres.indexOf(gen.toLowerCase())}, 1);
        }

        return net.predict(mov).getDouble(0, 0);
    }

    /**Creates a vector of all genres in the data base.
     */

    private void createGenres() {
        ArrayList<String> gen = new ArrayList<>();
        //
        for (Movie mov : moviedb.getAllMovies()) {
            for (String genre : mov.getGenre()) {
                if (!gen.contains(genre.toLowerCase()))
                    gen.add(genre.toLowerCase());
            }
        }

        Collections.sort(gen);

        genres = gen;
    }

    /**This method will have to get the movies rated by the user for updating the weights to train the neural net.
     * @param feedbBack a {@code Collection<MovieFeedBack} with the user feedback of a recomendation.
     */

    public void sendFeedBack(Collection<MovieFeedBack> feedbBack) {

        INDArray movies_rated = Nd4j.zeros(feedbBack.size(), genres.size());

        INDArray user_ratings = Nd4j.zeros(feedbBack.size(), 1);
        int index = 0;
        for (MovieFeedBack rates : feedbBack) {
            for (String genre : rates.getMovie().getGenre()) {
                movies_rated.putScalar(new int[]{index, genres.indexOf(genre.toLowerCase())}, 1);
            }

            user_ratings.putScalar(new int[]{index, 0}, rates.getRating());
            index++;
        }

        net.train(movies_rated, user_ratings);
        trained = true;

    }


}
