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

        Query q = new Query().setMaxYear(Calendar.getInstance().get(Calendar.YEAR)).setMinYear(2000);
        Collection<Movie> result = api.getRecommendation(q);
//        for (Movie m : result) {
//            System.out.println(m.getTitle());
//        }

        Actor a = app.getActor((long) 24);
        ArrayList<Actor> arr = new ArrayList<Actor>();
        arr.add(a);
        Query qe = new Query().setActor(arr).setMaxYear(2017);
        System.out.println(app.getAllMovies(qe));
        System.out.println(a.getMovies());

        Person testPerson = new Person(1, "Joel David Moore", app);
        System.out.println(app.getMovies(testPerson).size());
        for (int tries = 0; tries < 5; tries++) {
            //trying the neural
            for (int i = 0; i < 3; i++) {
                ArrayList<MovieFeedBack> feedBacks = new ArrayList<>();
                List<Movie> db = app.getAllMovies(q);
                Scanner s = new Scanner(System.in);
                int score = 0;
                int count = 5;
                Random rnd = new Random();
                for (int j = 0; count != 0; i++) {
                    Movie m = db.get(Math.floorMod(rnd.nextInt(), db.size()));
                    System.out.println(m);
                    score = s.nextInt();
                    if (score < 11) {
                        feedBacks.add(new MovieFeedBack(m, score));
                        count--;
                    }
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


}
