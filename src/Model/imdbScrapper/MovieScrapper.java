package Model.imdbScrapper;
import java.io.IOException;


public abstract class MovieScrapper {
    public abstract String scrapPosterURL() throws IOException;
    public abstract boolean hasLink();
    public abstract void setIMDbLink(String imdbLink);
    public abstract String scrapSinapsis() throws IOException;
}
