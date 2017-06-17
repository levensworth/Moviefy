package GUI;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by nacho on 04/06/2017.
 */
public class pruebaFrame {
    public static void main(String[] args)throws IOException{
        JFrame appframe = new AppFrame("prueba", 1400);//i adjusted the values for a macbook pro 15 inch
        appframe.setVisible(true);
    }
}
