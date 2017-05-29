package NeuralNetwork;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.rng.Random;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import sun.reflect.LangReflectAccess;


/**
 * Created by SB on 28/05/2017.
 */
public class Layer {
    /*
    * a layer is the name of a weight matrix between two levels of the the net
    * */


    private int input;
    private int output;
    private INDArray weight;
    private INDArray bias;
    private INDArray delta;
    private INDArray activated;
    private INDArray nonActivated;
    private Activation.ACTIVATION function;
    public Layer(int input, int output, Activation.ACTIVATION function){
        if(input < 0 || output < 0 || function == null)
            throw  new IllegalArgumentException("input and output must be positive integers and ACTIVATION not null");

        this.function = function;
        this.input = input;
        this.output = output;
        initWeights();
        initBias();
        initDelta();
        initActivated();
        initNonActivated();

    }

    private void initWeights(){
        Random ran = Nd4j.getRandom();
        int[] shape = {input,output};
        weight = Nd4j.rand(shape , -1.0 , 1.0 ,  ran);
    }

    private  void initBias(){
        Random ran = Nd4j.getRandom();

        int[] shape = {1, output};
        bias = Nd4j.rand(shape,-1,1,ran);

    }

    private  void initDelta(){
        int[] shape = {input,output};
        delta = Nd4j.zeros(input, output);
    }


    public INDArray propagate(INDArray input){
        nonActivated = input.mmul(getWeight());
        nonActivated.addi(getBias());
        switch (function){
            case SIGMOID:
                activated = Transforms.sigmoid(nonActivated,true);
                break;
            case LEAKY_RELU:
                activated = Transforms.leakyRelu(nonActivated,true);
                break;
            case TANH:
                activated = Transforms.tanh(nonActivated, true);
                break;
        }

        return activated;
    }


    public INDArray backPropagate(INDArray delta){

        switch (function){
            case SIGMOID:
                delta = Transforms.sigmoid(nonActivated, true);
                break;
            case LEAKY_RELU:
                delta = Transforms.leakyRelu(nonActivated, true);
                break;
            case TANH:
                delta = Transforms.tanh(nonActivated, true);
                break;
        }
        INDArray derivative =

        return delta;
    }

    public INDArray getWeight() {
        return weight;
    }

    public INDArray getBias() {
        return bias;
    }

    public INDArray getDelta() {
        return delta;
    }

    public void initActivated(){
        activated = Nd4j.zeros(input, output);
    }

    public void initNonActivated(){

        nonActivated = Nd4j.zeros(input, output);

    }




}
