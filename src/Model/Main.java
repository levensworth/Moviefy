package Model;

import Model.NeuralNetwork.API;

import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math.*;

public class Main {

    public static  void main(String[] args){


        Application app = null;
        try {
            app = new Application("./db/movieDB.csv", "./db/personDB.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        API api = new API(app, 5, 7);

        Query q = new Query().setMaxYear(Calendar.getInstance().get(Calendar.YEAR));

        Collection<Movie> result = api.getRecommendation(q);
//        for (Movie m : result) {
//            System.out.println(m.getTitle());
//        }


        //trying the neural
        for (int i = 0; i < 3; i++) {
            ArrayList<MovieFeedBack> feedBacks = new ArrayList<>();
            List<Movie> db = app.getAllMovies();
            Scanner s = new Scanner(System.in);
            int score = 0;
            Random rnd = new Random();
            for (int j = 0; i < 5; i++) {
                Movie m = db.get(Math.floorMod(rnd.nextInt(), db.size()));
                System.out.println(m.getTitle());
                score = s.nextInt();
                feedBacks.add(new MovieFeedBack(m, score));

            }

            System.out.println("training the neural ");
            api.sendFeedBack(feedBacks);

            System.out.println("the movies recommended");
            result = api.getRecommendation(q);
            for (Movie m : result) {
                System.out.println(m);
            }
            System.out.println("-------finish recommendation ------");
        }


    }


}
