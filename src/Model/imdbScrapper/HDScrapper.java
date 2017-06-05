package Model.imdbScrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HDScrapper extends MovieScrapper {
    private String imdbLink;
    private Document doc;

    public HDScrapper(String IMDbLink) throws IOException{
        this.imdbLink = IMDbLink;
        if(imdbLink!=null)parse();
    }

    public HDScrapper() throws IOException{
        this(null);
    }

    public void setIMDbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }
    /*
        All imdb MOVIEs web pages in HTML format have a tag named poster, this tag have an URL of
        the poster in low resolution, this method gets the HTML code and search for the poster tag, then
        gets the URL from the src attribute.
        Completed this process tries to form the high resolution poster URL, if it works return it, else
        return the low res. poster.
     */
    public String scrapPosterURL() throws IOException{
        if(doc == null) parse();
        Elements posterNode = doc.getElementsByClass("poster");
        String posterLowResURL = posterNode.select("img").first().absUrl("src");
        String posterHiResURL = posterLowResURL.substring(0,posterLowResURL.indexOf("@")+1) + "._V1_.jpg";
        if(checkURL(posterHiResURL)){
            posterHiResURL = posterLowResURL.substring(0,posterLowResURL.indexOf("@")+1) + "@._V1_.jpg";
            if (checkURL(posterHiResURL)){
                return posterLowResURL;
            }
        }
        return posterHiResURL;
    }

    public boolean hasLink(){
        return imdbLink != null;
    }

    public String scrapSinapsis() throws IOException{
        if(doc == null) parse();
        Element plots = doc.getElementsByClass("summary_text").first();
        return plots.text();
    }

    private void parse() throws IOException{
        doc = Jsoup.connect(imdbLink).get();
    }

    private boolean checkURL(String url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        return urlConnection.getResponseCode() == 404;
    }
}
