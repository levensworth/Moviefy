package GUI;
import javax.swing.JSlider;
import java.awt.*;

//JSlider normal configurado para el puntaje de las pelis
class RatingSlider extends JSlider{
     private static final int MAX_RATE = 10;
     private static final int MIN_RATE = 0;

     protected RatingSlider(int x, int y, int width,int height){
        super(JSlider.HORIZONTAL,MAX_RATE,MIN_RATE);
        setValue((MAX_RATE-MIN_RATE)/2);
        setMinorTickSpacing(1);
        setBounds(x,y,width,height);
        setPaintLabels(true);
        setPaintTicks(true);
        setPaintTrack(true);
        setFont(new Font(getFont().getName(),Font.BOLD,getWidth()/20));
        setLabelTable(createStandardLabels(1));
        setBackground(Color.white);
     }
}
