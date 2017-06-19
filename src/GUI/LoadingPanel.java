package GUI;

import javax.swing.*;
import java.awt.*;


public class LoadingPanel extends JPanel{
    private LoadingLabel label;

    public LoadingPanel(int width){
        setVisible(false);
        setLayout(null);
        label = new LoadingLabel(width);
        setBounds(0,0,label.getWidth(),label.getHeight());
        add(label);
        setBackground(Color.white);
    }
}
