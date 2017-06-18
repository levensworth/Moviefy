package GUI;

import javax.swing.JTextArea;
import java.awt.Font;

public class SynopsisTextArea extends JTextArea {
    protected SynopsisTextArea(int x,int y,int width,int height){
        setBounds(x,y,width,height);
        setWrapStyleWord(true);
        setLineWrap(true);
        setFont(new Font(getFont().getName(),Font.PLAIN,getHeight()/5));
    }
}
