package speedreading;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsReaderTest {

    @Test
    public void countWordsSuccess() {
        // get location of test file
        String pathToTestFile = getClass().getResource("/textToRead-success.txt").getFile();
        StatisticsReader.StatisticsResult statisticsResult = StatisticsReader.readStatistics(pathToTestFile);
        assertEquals(713, statisticsResult.getCharCount());
        assertEquals(120, statisticsResult.getWordCount());
        assertEquals(10, statisticsResult.getSentenceCount());
    }

    @Test
    public void countWordsNonExistingFile() {
        StatisticsReader.StatisticsResult statisticsResult = StatisticsReader.readStatistics("does-not-exists");
        assertNull(statisticsResult);
    }
}