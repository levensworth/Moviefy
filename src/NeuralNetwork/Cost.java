package NeuralNetwork;


import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.ops.transforms.Transforms;

public class Cost {

    public static enum COST{
        MSE, NSE
    }

    private COST function;

    public  Cost(COST cost){
        function = cost;
    }

    private COST getFunction(){
        return function;
    }

    private double mse(NDArray expected , NDArray output ){
        // a mean square error function

        double total = nse(expected, output);

        return total/ (expected.rows()*2);
    }


    private double nse(NDArray expected , NDArray output ){
        // a net square error function



        INDArray error = output.add(expected.mul(-1));
        error = Transforms.pow(error, 2);
        return error.sumNumber().doubleValue();

    }


    public double  getCost (NDArray expected , NDArray output){
        switch (getFunction()){
            case MSE:
                return mse(expected, output);

            case NSE:
                return nse(expected,output);

            default:
                throw  new RuntimeException("the cost function wasn't properly set up");
        }

    }
}