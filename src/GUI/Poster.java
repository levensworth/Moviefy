package GUI;



import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

public class Poster {
    private ImageIcon actualIcon;
    private Image originalImage;

    public Poster(String posterURL) throws MalformedURLException{
        if(posterURL == null) throw new IllegalArgumentException();
        this.actualIcon = new ImageIcon(new URL(posterURL));
        this.originalImage = actualIcon.getImage();
    }

    public Poster(URL url){
        if(url == null) throw new IllegalArgumentException();
        this.actualIcon = new ImageIcon(url);
        this.originalImage = actualIcon.getImage();
    }

    public Poster(ImageIcon icon){
        if(icon == null) throw new IllegalArgumentException();
        this.originalImage = icon.getImage();
        this.actualIcon = icon;
    }

    private void resize(int width){
        actualIcon.setImage(originalImage.getScaledInstance(width,(int)((7*width)/5),Image.SCALE_SMOOTH));
    }

    public void resetImage(){
        actualIcon.setImage(originalImage);
    }

    public ImageIcon getImageIcon() {
        return actualIcon;
    }

    public ImageIcon getImageIcon(int width){
        resize(width);
        return actualIcon;
    }
}
