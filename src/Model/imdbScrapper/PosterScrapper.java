package imdbScrapper;
import java.io.IOException;

/**
 * Created by nacho on 31/05/2017.
 */
public abstract class PosterScrapper {
    public abstract String scrapPosterURL() throws IOException;
}
