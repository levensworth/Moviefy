import NeuralNetwork.Activation;
import NeuralNetwork.Layer;

/**
 * Created by SB on 28/05/2017.
 */
public class Main {

    public static  void main(String[] args){


            Layer l = new Layer(2,4, Activation.ACTIVATION.SIGMOID);

            System.out.println(l.getWeight());

            System.out.println(l.getBias());
            System.out.println(l.getDelta());


    }


}
