package GUI;

import Model.Movie;
import Model.imdbScrapper.HDScrapper;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AppFrame extends JFrame{

    public AppFrame(String name,int wigth, int height) throws IOException{
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(wigth,height);
        setResizable(false);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        //FOR TESTING
        Movie m = new Movie(null,null,null,new ArrayList<>(),null,null,null,null,new ArrayList<>(),new URL("http://www.imdb.com/title/tt0120338/?ref_=nv_sr_1"),null,null,null,new ArrayList<>(),new HDScrapper(),null);
        add(new MoviePanel(m,wigth,height));
        //
    }
}
