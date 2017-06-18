package GUI;

import javax.swing.JLabel;

public class PosterLabel extends JLabel {
    private int width;

    public PosterLabel(int x,int y,int width){
        this.width = width;

        setBounds(x,y,width,(int)((7*width)/5));
        setHorizontalAlignment(JLabel.CENTER);
        setAlignmentX(LEFT_ALIGNMENT);
        updateUI();
    }

    public void setIcon(Poster poster){
        if(poster == null) throw new IllegalArgumentException();
        setIcon(poster.getImageIcon(width));
    }
}
