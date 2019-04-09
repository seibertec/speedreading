/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedreading;

import java.io.BufferedReader;
import java.io.IOException;

public class MainClass {

    public static class StatisticsResult {
        private int charCount ;
        private int wordCount;
        private int lineCount;

        public StatisticsResult(int charCount, int wordCount, int lineCount) {
            this.charCount = charCount;
            this.wordCount = wordCount;
            this.lineCount = lineCount;
        }

        public int getCharCount() {
            return charCount;
        }

        public int getWordCount() {
            return wordCount;
        }

        public int getLineCount() {
            return lineCount;
        }
    }

    public static void main(String[] args) {
        if(args == null || args.length != 1) {
            System.out.println("Usage: genau ein Argument für den Filename angeben, Du Dubbel!");
        } else {
            MainClass.countWords(args[0]);
        }
    }


    public static StatisticsResult countWords(String filePath) {

        BufferedReader reader = null;                               //Statistik wird erstellt

        //Initializing charCount, wordCount and lineCount to 0

        int charCount = 0;

        int wordCount = 0;

        int lineCount = 0;

        try
        {
            //Creating BufferedReader object

            reader = new BufferedReader(new java.io.FileReader(filePath));

            //Reading the first line into currentLine

            String currentLine = reader.readLine();

            while (currentLine != null)
            {
                //Updating the lineCount

                lineCount++;

                //Getting number of words in currentLine

                String[] words = currentLine.split(" ");

                //Updating the wordCount

                wordCount = wordCount + words.length;

                //Iterating each word

                for (String word : words)
                {
                    //Updating the charCount

                    charCount = charCount + word.length();
                }

                //Reading next line into currentLine

                currentLine = reader.readLine();
            }

            //Printing charCount, wordCount and lineCount

            System.out.println("Number Of Chars In A File : "+charCount);

            System.out.println("Number Of Words In A File : "+wordCount);

            System.out.println("Number Of Lines In A File : "+lineCount);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();           //Closing the reader
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return new StatisticsResult(charCount, wordCount, lineCount);
    }
}