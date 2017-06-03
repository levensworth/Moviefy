package Model.imdbScrapper;
import java.io.IOException;


public abstract class PosterScrapper {
    public abstract String scrapPosterURL() throws IOException;
}
