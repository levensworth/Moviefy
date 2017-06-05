package GUI;

import javax.swing.*;

public class PosterLabel extends JLabel {
    private int width;
    private int height;
    private Poster poster;

    public PosterLabel(Poster poster,int x,int y,int width,int height){
        this.height = height;
        this.width = width;
        this.poster = poster;
        this.poster.resize(width,height);
        setIcon(poster.getImageIcon());
        setBounds(x,y,width,height);
        setHorizontalAlignment(JLabel.CENTER);
        updateUI();
        setAlignmentX(LEFT_ALIGNMENT);
    }

    public void setPoster(Poster poster){
        this.poster = poster;
        setIcon(poster.getImageIcon(width,height));
    }
}
