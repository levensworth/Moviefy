import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CSVReader {
    private String path;
    private String splitBy;
    private BufferedReader bufferedReader;
    private FileReader fileReader;

    public CSVReader(String path, String splitBy) throws FileNotFoundException {
        this.fileReader = new FileReader(path); //check if file exist...
        this.bufferedReader = new BufferedReader(fileReader);
        this.splitBy = splitBy;
    }

    public CSVReader(String path) throws FileNotFoundException {
        this(path, ",");
    }

    public void resetCursor() throws IOException {
        bufferedReader.reset();
        fileReader.reset();
    }

    public Collection<String> getLine() throws IOException {
        Collection<String> values = new ArrayList<>();

        String line = bufferedReader.readLine(); //check if line is null...

        String[] valuesArray = line.split(splitBy);

        for (int i = 0; i < valuesArray.length; i++) {
            values.add(valuesArray[i]);
        }

        return values;
    }

    public Collection<String> getLine(long lineNumber) throws IOException {
        Collection<String> values = new ArrayList<>();

        for (long i = 0; i < lineNumber; i++) {
            bufferedReader.readLine();
        }

        String line = bufferedReader.readLine();

        String[] valuesArray = line.split(splitBy);

        for (int i = 0; i < valuesArray.length; i++) {
            values.add(valuesArray[i]);
        }

        return values;
    }
    
}
