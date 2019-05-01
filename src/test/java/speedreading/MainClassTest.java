package speedreading;

import org.junit.Test;
import sun.applet.Main;

import static org.junit.Assert.*;

public class MainClassTest {

    @Test
    public void countWordsSuccess() {
        // get location of test file
        String pathToTestFile = getClass().getResource("/textToRead-success.txt").getFile();
        MainClass.StatisticsResult statisticsResult = MainClass.countWords(pathToTestFile);
        assertEquals(713, statisticsResult.getCharCount());
        assertEquals(120, statisticsResult.getWordCount());
        assertEquals(10, statisticsResult.getSentenceCount());
    }

    @Test
    public void countWordsWithParserFailure() {
        // get location of test file
        String pathToTestFile = getClass().getResource("/textToRead-failure.txt").getFile();
        MainClass.StatisticsResult statisticsResult = MainClass.countWords(pathToTestFile);
        System.out.println(statisticsResult);
    }

    @Test
    public void countWordsNonExistingFile() {
        MainClass.StatisticsResult statisticsResult = MainClass.countWords("does-not-exists");
        assertNull(statisticsResult);
    }
}