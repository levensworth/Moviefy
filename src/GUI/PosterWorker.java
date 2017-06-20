package GUI;

import Model.Movie;
import javax.swing.SwingWorker;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.ArrayList;

//entre los pasters usando el scrapper de las peliculas.
public class PosterWorker extends SwingWorker<ArrayList<Poster>,ArrayList<Movie>> {
    private ArrayList<Poster> posters;
    private ArrayList<Movie> movies;
    private MainFrame mainFrame;

    public PosterWorker(ArrayList<Movie> movies,ArrayList<Poster> posters,MainFrame mainFrame){
    this.movies = movies;
    this.posters = posters;
    this.mainFrame = mainFrame;
    }

    protected ArrayList<Poster> doInBackground() throws Exception {
        mainFrame.loading(true);
        for (Movie m : movies) {
            try {
                posters.add(new Poster(m.getPosterURL()));
            } catch (IOException e) {
                posters.add(new Poster(new ImageIcon("db/2000px-No_image_available.svg.png")));
            }
        }
        mainFrame.loading(false);
        return null;
    }
}
