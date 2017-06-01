import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;

public class CSVReader {
    private String path;
    private String splitChar;
    private RandomAccessFile fileReader;

    public CSVReader(String path, String splitChar) throws FileNotFoundException {
        this.path = path;
        this.fileReader = new RandomAccessFile(path, "r");
        this.splitChar = splitChar;
    }

    public CSVReader(String path) throws FileNotFoundException {
        this(path, ",");
    }

    /*
     * Puts the file reader pointer to the beginning of the file.
     */
    public void resetFilePointer() throws IOException {
        fileReader.seek(0L);
    }

    /*
     * Return a collection with the values readed from the line where file reader is pointing to.
     */
    public Collection<String> getLine() throws IOException {
        Collection<String> values = new ArrayList<>();
        String line = fileReader.readLine();

        String[] valuesArray = line.split(splitChar);

        for (int i = 0; i < valuesArray.length; i++) {
            values.add(valuesArray[i]);
        }

        return values;
    }

    /*
     * Return a collection with the values readed from the specified line of the file.
     * The line is counted from the beginning of the file, number zero stands for the first line.
     */
    public Collection<String> getLine(long lineNumber) throws IOException {
        Collection<String> values = new ArrayList<>();

        for (long i = 0; i < lineNumber; i++) {
            fileReader.readLine();
        }

        String line = fileReader.readLine();

        String[] valuesArray = line.split(splitChar);

        for (int i = 0; i < valuesArray.length; i++) {
            values.add(valuesArray[i]);
        }

        resetFilePointer();

        return values;
    }

}
