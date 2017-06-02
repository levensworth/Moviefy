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
        Todas las páginas de PELICULAS en imdb tienen un tag de poster donde está el URL del poster
        en baja resolusion, este metodo lo que hace es obtener el codigo HTML de la página, buscar
        el tag de poster, obtener el URL del poster en baja resolusion y intentar obtener el URL
        del poster en alta resolusion, si se encontro correctamente lo retorna, sino, retorna el
        poster en baja resolusion.
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
