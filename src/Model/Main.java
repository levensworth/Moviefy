package Model;

import Model.NeuralNetwork.API;

import java.util.*;

public class Main {

    public static  void main(String[] args){

//
//       //Layer input = new Layer(2, 30, Activation.ACTIVATION.TANH);
//       Layer l = new Layer(30, 30, Activation.ACTIVATION.TANH);
//       Layer output = new OutpuLayer(30,1, Activation.ACTIVATION.TANH);
//
//
//        int input = 2;
//
//
//
//        NetworkBuilder config = new NetworkBuilder()
//                .setCost(Cost.COST.MSE)
//                .addLayer(0, new Layer(input, 2, Activation.ACTIVATION.LEAKY_RELU))
//                .addLayer(1, new Layer(2, 3, Activation.ACTIVATION.LEAKY_RELU))
//                .addLayer(2, new Layer(3, 4, Activation.ACTIVATION.LEAKY_RELU))
//                .addLayer(3, new OutpuLayer(4, 1, Activation.ACTIVATION.TANH))
//                .setEpoch(1000)
//                .setLearningRate(0.1)
//                .setWeightDecay(0.95)
//                .setMomentum(0.2);
//
//        Network net = config.build();
//
//        INDArray example = Nd4j.create(new double[] {1,1,0,0,1,0,1,0}, new int[] {4,2});
//        System.out.println(example);
//
//        INDArray expected = Nd4j.create(new double[] {0,0,1,1}, new int[] {4,1});
//        System.out.println(" expected");
//        System.out.printf(String.valueOf(expected) + "\n");
//
//        INDArray test = Nd4j.create(new double[]{1,1}, new int[]{1,2});
//
//        INDArray testOut = Nd4j.create(new double[]{0}, new int[]{1,1});
//
//       // System.out.println("\n\n"+net.predict(example) + "\n predicted\n\n\n\n");
//
//        net.train(example,expected);
//
//        System.out.println(net.predict(example));
//        System.out.println("this neural net must implement a momentum and an api");
//

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
            System.out.println(m.getTitle());
        }

    }


}
