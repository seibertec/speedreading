package speedreading;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReaderTest {

    @Test
    public void testReadSuccessFile() {
        // get location of test file
        String pathToTestFile = getClass().getResource("/textToRead-success.txt").getFile();
        // Create specific test reader
        TestReader r = new TestReader(pathToTestFile, 10);
        // Run it
        r.run();
        // 120 words are present in the file and must be read out
        assertEquals(120, r.wordsReadFromFile);
    }

    @Test
    public void testHandleFailureWhenReadingFile() {
        // get location of test file
        String pathToTestFile = getClass().getResource("/textToRead-failureWithEmptyLastLine.txt").getFile();
        TestReader r = new TestReader(pathToTestFile, 10);
        r.run();
        // Only 1 word is present in the file and must be readout
        assertEquals(1, r.wordsReadFromFile);
    }

    @Test
    public void testHandleFailureWhenReadingNonexistingFile() {
        TestReader r = new TestReader("NON-EXISTING-FILE", 10);
        r.run();
        // File does not exists => no words should be read
        assertEquals(0, r.wordsReadFromFile);
    }

    /*
        Special extension of the Reader to allow verification of the words read out
     */
    class TestReader extends Reader {
        int wordsReadFromFile;
        public TestReader(String fileName, int pauseInMillis) {
            super(fileName, pauseInMillis);
        }

        @Override
        protected void readout(String word) {
            wordsReadFromFile = wordsReadFromFile + 1;
        }
    }
}