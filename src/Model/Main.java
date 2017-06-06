package Model;

import Model.NeuralNetwork.API;

import java.util.*;

public class Main {

    public static  void main(String[] args){


        Application app = new Application("./db/movieDB.csv", "./db/personDB.csv");

        API api = new API(app);

        Query q = new Query().setMaxYear(Calendar.getInstance().get(Calendar.YEAR));

        Collection<Movie> result = api.getRecommendation(q);
//        for (Movie m : result) {
//            System.out.println(m.getTitle());
//        }


        //trying the neural

        ArrayList<MovieFeedBack> feedBacks = new ArrayList<>();
        Collection<Movie> db = app.getAllMovies();
        Scanner s = new Scanner(System.in);
        int index = 0;
        int score = 0;
        Random rnd = new Random();
        for (Movie m : db) {
            if (rnd.nextInt() % 13 == 0) {
                System.out.println(m.getTitle());
                score = s.nextInt();
                index++;
                feedBacks.add(new MovieFeedBack(m, score));
            }
            if (index > 3)
                break;

        }

        System.out.println("tryining the nerual");
        api.sendFeedBack(feedBacks);

        System.out.println("the movies recommended");
        result = api.getRecommendation(q);
        for (Movie m : result) {
            System.out.println(m);
        }

    }


}
