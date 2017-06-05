package GUI;


import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

public class Poster {
    private ImageIcon actualImage;

    private Image originalImage;


    public Poster(String posterURL) throws MalformedURLException{
        this.actualImage = new ImageIcon(new URL(posterURL));
        this.originalImage = actualImage.getImage();
    }

    public Poster(URL url){
        this.actualImage = new ImageIcon(url);
        this.originalImage = actualImage.getImage();
    }

    public void resize(int width,int height){
        actualImage.setImage(originalImage.getScaledInstance(width,height,Image.SCALE_SMOOTH));
    }

    public void resetImage(){
        actualImage.setImage(originalImage);
    }

    public ImageIcon getImageIcon() {
        return actualImage;
    }

    public ImageIcon getImageIcon(int width,int height){
        resize(width,height);
        return actualImage;
    }
}
