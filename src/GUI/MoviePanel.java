package GUI;
import Model.Movie;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.io.IOException;

public class MoviePanel extends JPanel{
    private Movie actualMovie;
    private Poster poster;
    private int width;
    private int height;
    private PosterLabel posterLabel;

    public MoviePanel(Movie movie,int width,int height) throws IOException{
        setLayout(null);
        this.width = width;
        this.height = height;
        this.actualMovie = movie;
        initialize();
    }

    public void setActualMovie(Movie actualMovie) throws IOException{
        this.actualMovie = actualMovie;
        poster = new Poster(actualMovie.getPosterURL());
    }

    private void initialize()throws IOException{
        poster = new Poster(actualMovie.getPosterURL());
        posterLabel = new PosterLabel(poster,(int)(width*0.6),50,width/3,(int)((width/3)*1.42));
        add(posterLabel);
    }

//    public void refresh(){
//
//    }


}
