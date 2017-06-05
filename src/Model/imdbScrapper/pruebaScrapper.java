package Model.imdbScrapper;

import Model.Movie;

import java.io.IOException;

/**
 * Created by nacho on 05/06/2017.
 */
public class pruebaScrapper {
    public static void main(String[] args) throws IOException{
        MovieScrapper scrapper = new HDScrapper("http://www.imdb.com/title/tt0120338/?ref_=nv_sr_1");
        System.out.println(scrapper.scrapSinapsis());
    }
}
