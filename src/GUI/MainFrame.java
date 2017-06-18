package GUI;

import Model.*;
import Model.NeuralNetwork.API;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class MainFrame extends JFrame{
    //main

    //MainFrame
    private int width;
    private ArrayList<Movie>  movies;
    private Movie   actualMovie;
    private MoviePanel mPanel;
    private JLabel  title;
    private ArrayList<MovieFeedBack> feedBack;
    private Query query;
    private API api;

    public static void main(String[] args) {
        Application app = null;
        MainFrame movify = null;

        try {
            if(System.getProperty("os.name").contains("Windows")){
                app = new Application(".\\db\\movieDB.csv", ".\\db\\personDB.csv");
            }else {
                app = new Application("./db/movieDB.csv", "./db/personDB.csv");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        API api = new API(app, 5, 7);

        try {
            movify = new MainFrame("Movify",2000,api);
        }catch (IOException e){
            e.printStackTrace();
        }

        try{movify.start();} catch (IOException e){e.printStackTrace();}
    }


    public MainFrame(String name, int width,API api) throws IOException{
        super(name);
        this.width = width;
        this.api = api;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(width,(width*4)/5);
        setResizable(false);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        initialize();
    }

    private void initialize() throws IOException{
        mPanel = new MoviePanel(0,0,width);
        mPanel.setVisible(false);
        add(mPanel);
    }

    public void start() throws IOException{
        setVisible(true);
        //        welcome();
        userMakeQuery();
        showAndRateMovies();
    }

    /* will be changed when the query maker panel exist (?*/
    private void userMakeQuery(){
        query = new Query().setMaxYear(Calendar.getInstance().get(Calendar.YEAR)).setMinYear(2000);
    }

    private void showAndRateMovies() throws IOException{
        //set all visibilities off but no mPanel
        movies = new ArrayList<>(api.getRecommendation(query));
        feedBack = new ArrayList<>();
        mPanel.setMovie(movies.get(0));
        mPanel.setVisible(true);
    }

    private class MoviePanel extends JPanel {
        static final int MAX_RATE = 10;
        static final int MIN_RATE = 0;

        private Movie actualMovie;
        private Poster poster;
        private int width;
        private PosterLabel posterLabel;
        private JSlider ratingSlider;
        private int index;
        private JButton nextButton;
        private JLabel title;
        private JButton neverSawItButton;

        private MoviePanel(int x,int y,int width){
            this.width = width;
            index = 0;
            setBounds(x,y,width,(width*17)/23);
            setLayout(null);
            initialize();
        }

        private void initialize(){
            posterLabel = new PosterLabel((((2*width)/3)-(width/48)),width/16,width/3);
            add(posterLabel);

            ratingSlider = new JSlider(JSlider.HORIZONTAL,MAX_RATE,MIN_RATE);
            ratingSlider.setValue((MAX_RATE-MIN_RATE)/2);
            ratingSlider.setMinorTickSpacing(1);
            ratingSlider.setBounds((((2*width)/3)-(width/48)),((7*width)/12),width/3,width/16);
            ratingSlider.setPaintLabels(true);
            ratingSlider.setPaintTicks(true);
            ratingSlider.setPaintTrack(true);
            add(ratingSlider);

            nextButton = new JButton("Rate");
            nextButton.setBounds((((2*width)/3)-(width/48)),((width*125)/184),width/3,width/24);
            nextButton.addActionListener(new nextMoviesAction());
            nextButton.setFont(new Font(nextButton.getFont().getName(),Font.BOLD,(int)(nextButton.getHeight()*0.9)));
            add(nextButton);

            neverSawItButton = new JButton("Never saw it");
            neverSawItButton.setBounds((width*7)/24,((width*125)/184),width/3,width/24);
            neverSawItButton.addActionListener(new seenItMovie());
            neverSawItButton.setFont(new Font(neverSawItButton.getFont().getName(),Font.BOLD,(int)(neverSawItButton.getHeight()*0.9)));
            add(neverSawItButton);

            title = new JLabel("");
            title.setBounds(width/48,width/48,width-(width/48),width/24);
            title.setFont(new Font(title.getFont().getName(),Font.BOLD,(int)(title.getHeight()*0.9)));
            add(title);


        }

        public int getRating(){
            return ratingSlider.getValue();
        }

        public void setMovie(Movie m){
            if(m == null) throw new IllegalArgumentException();
            title.setText(m.getTitle());
            try{
                posterLabel.setIcon(new Poster(m.getPosterURL()));
            }catch (IOException e){
                posterLabel.setIcon(new Poster(new ImageIcon("db/2000px-No_image_available.svg.png")));
            }

        }

        private void nextMovie(){
            index++;
            if(index >= movies.size()){
                api.sendFeedBack(feedBack);
                movies = new ArrayList<>(api.getRecommendation(query));
                feedBack = new ArrayList<>();
                index = 0;
            }
            setMovie(movies.get(index));
        }

        private class nextMoviesAction implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rating : " + getRating());
                feedBack.add(new MovieFeedBack(movies.get(index),getRating()));
                nextMovie();
            }
        }

        private class seenItMovie implements ActionListener{
            public void actionPerformed(ActionEvent e){
                nextMovie();
            }
        }
    }



}
