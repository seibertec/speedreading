package speedreading;

import java.io.BufferedReader;
import java.io.IOException;

public class StatisticsReader {

    public static class StatisticsResult {

        private int charCount;
        private int wordCount;
        private int sentenceCount;

        public StatisticsResult(int charCount, int wordCount, int sentenceCount) {
            this.charCount = charCount;
            this.wordCount = wordCount;
            this.sentenceCount = sentenceCount;
        }

        public int getCharCount() {
            return charCount;
        }

        public int getWordCount() {
            return wordCount;
        }

        public int getSentenceCount() {
            return sentenceCount;
        }

        @Override
        public String toString() {
            return "StatisticsResult{" +
                    "charCount=" + charCount +
                    ", wordCount=" + wordCount +
                    ", sentenceCount=" + sentenceCount +
                    '}';
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            System.out.println("Usage: genau ein Argument für den Filename angeben, Du Dubbel!");
        } else {
            StatisticsReader.readStatistics(args[0]);
        }
    }

    public static StatisticsResult readStatistics(String filePath) {

        BufferedReader reader = null;                               //Statistik wird erstellt

        //Initializing charCount, wordCount and lineCount to 0
        int charCount = 0;

        int wordCount = 0;

        String line;
        String delimiters = "?!.";
        int sentenceCount = 0;
        try {
            //Creating BufferedReader object

            reader = new BufferedReader(new java.io.FileReader(filePath));

            while ((line = reader.readLine()) != null) { // Continue reading until end of file is reached
                for (int i = 0; i < line.length(); i++) {
                    if (delimiters.indexOf(line.charAt(i)) != -1) { // If the delimiters string contains the character
                        sentenceCount++;
                    }
                }
            }
            reader.close();
            reader = new BufferedReader(new java.io.FileReader(filePath));
            //Reading the first line into currentLine
            String currentLine = reader.readLine();

            while (currentLine != null) {

                //Getting number of words in currentLine
                String[] words = currentLine.split(" ");

                //Updating the wordCount
                wordCount = wordCount + words.length;

                //Iterating each word
                for (String word : words) {
                    //Updating the charCount

                    charCount = charCount + word.length();
                }

                //Reading next line into currentLine
                currentLine = reader.readLine();
            }
            // Reading successful, return our result
            return new StatisticsResult(charCount, wordCount, sentenceCount);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) {
                    reader.close();           //Closing the reader - if available
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null; // error case, better propagate the exception
    }
}
