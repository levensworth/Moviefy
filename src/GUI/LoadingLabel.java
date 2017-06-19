package GUI;

import javax.swing.*;

public class LoadingLabel extends JLabel {
    public LoadingLabel(int width){
        setHorizontalAlignment(JLabel.CENTER);
        setAlignmentX(LEFT_ALIGNMENT);
        setBounds(0,0,width,(int)(width*0.8));
        setIcon(new ImageIcon("db/Loading_icon.gif"));
        updateUI();
    }
}
