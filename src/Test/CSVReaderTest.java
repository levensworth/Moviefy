package Test;
import Model.CSVReader;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by dn on 14/06/17.
 */

public class CSVReaderTest {
    @Test
    public void NonExistentPathTest() {
        try {
            String nonExistentPath = "this/path/non/exist.txt";
            CSVReader csv = new CSVReader(nonExistentPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GettingLineTest() {
        Collection<String> expectedCollection = null;
        Collection<String> returnedCollection = null;
        try {
            expectedCollection = new LinkedList<>();
            expectedCollection.add("0");
            expectedCollection.add("Avatar");
            expectedCollection.add("3");
            expectedCollection.add("0");
            expectedCollection.add("1");
            expectedCollection.add("2");
            expectedCollection.add("178");
            expectedCollection.add("2009");
            expectedCollection.add("Action|Adventure|Fantasy|Sci-Fi");
            expectedCollection.add("7.9");
            expectedCollection.add("723");
            expectedCollection.add("PG-13");
            expectedCollection.add("English");
            expectedCollection.add("USA");
            expectedCollection.add("avatar|future|marine|native|paraplegic");
            expectedCollection.add("http://www.imdb.com/title/tt0499549/?ref_=fn_tt_tt_1");

            CSVReader csv = new CSVReader("db/movieDB.csv");
            returnedCollection = csv.getLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(true, expectedCollection.equals(returnedCollection));
    }

    @Test
    public void GettingSpecificLineTest() {
        Collection<String> expectedCollection = null;
        Collection<String> returnedCollection = null;
        try {

            expectedCollection = new LinkedList<>();
            expectedCollection.add("299");
            expectedCollection.add("Nicolas Cage");
            expectedCollection.add("104|224|241|243|282|300|455|528|565|668|696|732|831|854|950|1032|1051|1111|1130|1170|1257|1291|1516|1562|2005|2278|2353|2386|2399|2413|2568|3812|3894|3938");

            CSVReader csv = new CSVReader("db/personDB.csv");
            returnedCollection = csv.getLine(300);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(true, expectedCollection.equals(returnedCollection));
    }

    @Test
    public void CheckForFeofTest() {
        boolean returnedValue = true;
        try {
            CSVReader csv = new CSVReader("db/movieDB.csv");
            returnedValue = csv.feof();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(false, returnedValue);
    }

    @Test
    public void IteratingOverFileLinesTest() {
        long lineCounter = 0L;
        long expectedLineQty = 5043L;
        try {
            CSVReader csv = new CSVReader("db/movieDB.csv");
            for (Collection<String> line : csv) {
                lineCounter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(expectedLineQty, lineCounter);
    }

    @Test
    public void FilePointerResetsAfterReadSpecificLineTest() {
        Collection<String> expectedCollection = null;
        Collection<String> returnedCollection = null;
        try {
            expectedCollection = new LinkedList<>();
            expectedCollection.add("0");
            expectedCollection.add("Avatar");
            expectedCollection.add("3");
            expectedCollection.add("0");
            expectedCollection.add("1");
            expectedCollection.add("2");
            expectedCollection.add("178");
            expectedCollection.add("2009");
            expectedCollection.add("Action|Adventure|Fantasy|Sci-Fi");
            expectedCollection.add("7.9");
            expectedCollection.add("723");
            expectedCollection.add("PG-13");
            expectedCollection.add("English");
            expectedCollection.add("USA");
            expectedCollection.add("avatar|future|marine|native|paraplegic");
            expectedCollection.add("http://www.imdb.com/title/tt0499549/?ref_=fn_tt_tt_1");

            CSVReader csv = new CSVReader("db/movieDB.csv");
            csv.getLine(669);
            returnedCollection = csv.getLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(true, expectedCollection.equals(returnedCollection));
    }


}