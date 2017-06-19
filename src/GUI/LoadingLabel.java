package GUI;

import javax.swing.*;
import java.awt.*;


public class LoadingLabel extends JLabel {
    public LoadingLabel(int width){
        setVisible(false);
        setHorizontalAlignment(JLabel.CENTER);
        setAlignmentX(LEFT_ALIGNMENT);
        setBounds(0,0,width,(width*17)/23);
        setIcon(new ImageIcon("db/Loading_icon.gif"));
        updateUI();
    }
}
