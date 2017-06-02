package Test;

import Model.NeuralNetwork.Activation;
import Model.NeuralNetwork.Layer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by SB on 31/05/2017.
 */
public class LayerTest {

    public boolean TestForward() {
        //it is a simulation of a 3 input by one output neural net
        INDArray input = Nd4j.ones(1, 3);
        Layer l = new Layer(3, 1, Activation.ACTIVATION.SIGMOID);

        INDArray output = l.propagate(input);

        int rows = 1;
        int columns = 1;
        return output.rows() == rows && output.columns() == columns;
    }


}
