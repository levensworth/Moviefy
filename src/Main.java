import NeuralNetwork.*;
import org.nd4j.linalg.api.buffer.DoubleBuffer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.rng.Random;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by SB on 28/05/2017.
 */
public class Main {

    public static  void main(String[] args){


       Layer input = new Layer(2, 3, Activation.ACTIVATION.TANH);
       Layer output = new OutpuLayer(3,1, Activation.ACTIVATION.TANH);





        NetworkBuilder config = new NetworkBuilder()
                .addLayer(0,input)
                .addLayer(1,output)
                .setActivation(Activation.ACTIVATION.TANH)
                .setCost(Cost.COST.MSE)
                .setEpoch(1000)
                .setLearningRate(0.1);

        Network net = config.build();

        INDArray example = Nd4j.create(new double[] {1,1,0,0,1,0,1,0}, new int[] {4,2});
        System.out.println(example);

        INDArray expected = Nd4j.create(new double[] {0,0,1,1}, new int[] {4,1});
        System.out.println(" expected");
        System.out.printf(String.valueOf(expected) + "\n");

        INDArray test = Nd4j.create(new double[]{1,1}, new int[]{1,2});

        INDArray testOut = Nd4j.create(new double[]{0}, new int[]{1,1});

       // System.out.println("\n\n"+net.predict(example) + "\n predicted\n\n\n\n");

        net.train(example,expected);

        System.out.println(net.predict(example));
        System.out.println("this neural net must implement a momentum and an api");

    }


}
