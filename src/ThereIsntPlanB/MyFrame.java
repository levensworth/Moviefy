package ThereIsntPlanB;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private MyPanel panel;

    public MyFrame(String name,int width){
        super(name);

        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,(int)(width*0.8));
        setResizable(false);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        panel = new MyPanel(width);
        add(panel);
    }

    public void Ten() {
        System.out.println("entro al loop");
        int n = panel.getCurrent();
        while (n <= 10) {
            //System.out.println(n);
            n = panel.getCurrent(); //SI ACA ADENTRO PRINTEAN N ANDA PERFECTO SINO NO!!!!
        }
        System.out.println("llego a 10");
    }
}
