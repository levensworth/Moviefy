package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CSVReader implements Iterable<Collection<String>> {
    private String path;
    private String splitChar;
    private RandomAccessFile fileReader;

    public CSVReader(String path, String splitChar) throws FileNotFoundException {
        this.path = path;
        this.fileReader = new RandomAccessFile(path, "r");
        this.splitChar = splitChar;
    }

    public CSVReader(String path) throws FileNotFoundException {
        this(path, ";");
    }

    /*
     * Puts the file reader pointer to the beginning of the file.
     */
    public void resetFilePointer() throws IOException {
        fileReader.seek(0L);
    }

    /*
     * Returns a collection with the values read from the line where file reader is pointing to.
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
     * Returns a collection with the values read from the specified line of the file.
     * The line is counted from the beginning of the file, number zero stands for the first line.
     */
    public Collection<String> getLine(long lineNumber) throws IOException {
        Collection<String> values = new ArrayList<>();

        fileReader.seek(lineNumber);

        String line = fileReader.readLine();

        String[] valuesArray = line.split(splitChar);

        for (int i = 0; i < valuesArray.length; i++) {
            values.add(valuesArray[i]);
        }

        resetFilePointer();

        return values;
    }

    /*
     * Returns false if there are more lines to read. Otherwise return true.
     */
    public boolean feof() throws IOException {
        boolean isEof = true;
        long lastPointer = fileReader.getFilePointer();

        if(fileReader.readLine() != null)
            isEof = false;

        fileReader.seek(lastPointer);

        return isEof;
    }

    /*
     * Returns our CSV Custom Iterator
     */
    @Override
    public Iterator<Collection<String>> iterator() {
        return new CSVIterator();
    }

    class CSVIterator implements Iterator<Collection<String>> {
        protected CSVIterator() {
            try {
                resetFilePointer();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override
        public boolean hasNext() {
            try {
                boolean hasNext = !feof();

                if(!hasNext)
                    resetFilePointer();

                return hasNext;
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override
        public Collection<String> next() {
            try {
                return getLine();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
