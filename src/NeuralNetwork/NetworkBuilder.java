package NeuralNetwork;
import java.util.ArrayList;
import java.util.List;

import static NeuralNetwork.Activation.ACTIVATION.SIGMOID;
import static NeuralNetwork.Cost.COST.NSE;
/*
* this class will reacieve and adapt the hyperparams
* for a neural network , leting the user configure just enough
* for it to be simple
*
* params:
*   learning rate;
*   momentum
*   weight decay
*   activation function (limited functionality for the moment)
*   loss function   (limited functionality )
* */

public class NetworkBuilder{

    private double learningRate;
    private double momentum;
    private double weightDecay;
    private Activation.ACTIVATION activation;
    private Cost.COST cost;
    private List<Layer> layersList;


    public NetworkBuilder(){
        learningRate = 0.01;
        momentum = 0.9;
        weightDecay = 0.0;
        activation = SIGMOID;
        cost = NSE;
        layersList = new ArrayList<Layer>();
    }

    public NetworkBuilder setLearningRate(double x){
        if( x  < 0 ){
            throw  new IllegalArgumentException();
        }

        this.learningRate = learningRate;
        return this;
    }

    public NetworkBuilder setMomentum(double x){
        if( x  < 0 ){
            throw  new IllegalArgumentException();
        }

        this.momentum= x;
        return this;
    }

    public NetworkBuilder setWeightDecay(double x){
        if( x < 0)
            throw  new IllegalArgumentException();

        this.weightDecay = x;
        return this;
    }

    public NetworkBuilder setActivation(Activation.ACTIVATION fun){
        this.activation = fun;
        return this;
    }

    public NetworkBuilder setCost(Cost.COST cost){
        this.cost = cost;
        return this;
    }

    public NetworkBuilder addLayer(int index , Layer l){
        if(index < 0 )
            throw  new IllegalArgumentException();
        if(index > layersList.size())
            layersList.add(l);
        else
            layersList.add(index, l);
        return this;
    }

    public Network build(){
        if(layersList.isEmpty())
            throw  new RuntimeException("there must be at leat two layers specified");
        return new Network(learningRate, momentum, weightDecay, activation,cost, layersList);
    }


}