package imdbScrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by nacho on 31/05/2017.
 */
public final class HDScrapper extends PosterScrapper {
    private String imdbLink;

    public HDScrapper(){
        this.imdbLink = null;
    }

    public HDScrapper(String imdbLink){
        this.imdbLink = imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }
    /*
        All imdb MOVIEs web pages in HTML format have a tag named poster, this tag have an URL of
        the poster in low resolution, this method gets the HTML code and search for the poster tag, then
        gets the URL from the src attribute.
        Complete this process tries to form the high resolution poster URL, if it works return it, else
        return the low res. poster.
     */
    public String scrapPosterURL() throws IOException{
        Document doc = Jsoup.connect(imdbLink).get();
        Elements posterNode = doc.getElementsByClass("poster");
        String posterLowResURL = posterNode.select("img").first().absUrl("src").toString();
        String posterHiResURL = posterLowResURL.substring(0,posterLowResURL.indexOf("@")+1) + "._V1_.jpg";
        if(checkURL(posterHiResURL)){
            posterHiResURL = posterLowResURL.substring(0,posterLowResURL.indexOf("@")+1) + "@._V1_.jpg";
            if (checkURL(posterHiResURL)){
                return posterLowResURL;
            }
        }
        return posterHiResURL;
    }

    private boolean checkURL(String url) throws MalformedURLException, IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        return urlConnection.getResponseCode() == 404;
    }
}
