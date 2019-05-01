package speedreading;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Reader implements Runnable {

    private String fileName;
    private int pauseInMillis;

    public Reader(String fileName, int pauseInMillis) {
        this.fileName = fileName;
        this.pauseInMillis = pauseInMillis;
    }

    /*
        Run the reader on the provided filename
     */
    public void run() {

        try {
            InputStreamReader r = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
            Scanner in = new Scanner(r); //Scanner to read the file at chosen location
            while (in.hasNextLine() && in.hasNext()) {        //read every line of file
                String nextWord = in.next();      //move to next word
                readout(nextWord);        // call readout method to actually read the word
                sleep(pauseInMillis);     // call sleep method to add a pause after the word.
            }
            in.close();     //Close Scanner after words have been read
        } catch (Exception e) {   //ignore exception
            e.printStackTrace();    //used to identify problems
        }
    }

    /*
        Read out the passed word. Default is writing it to System.out
     */
    protected void readout(String word) {
        System.out.println(word);
    }

    private void sleep(int sleepTimeInMillis) {
        try {
            Thread.sleep(sleepTimeInMillis);        //sleep thread for sleepTimeInMillis milliseconds
        } catch (InterruptedException e) {   //exception needed for sleep
            //do nothing
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            System.out.println("Usage: genau ein Argument für den Filename angeben, Du Dubbel!");
        } else {
            String fileName = args[0];
            int milisec = 500;
            System.out.println("Ich lese " + fileName + " mit " + milisec + "ms Pause");
            new Reader(fileName, milisec).run();
        }
    }
}
