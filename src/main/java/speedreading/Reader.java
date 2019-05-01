package speedreading;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
            Scanner in = new Scanner(new FileReader(fileName)); //Scanner to read the file at chosen location
            while (in.hasNextLine() && in.hasNext()) {        //read every line of file
                String nextWord = in.next();      //move to next word
                readout(nextWord);        // call readout method to actually read the word
                sleep(pauseInMillis);     // call sleep method to add a pause after the word.
            }
            in.close();     //Close Scanner after words have been read
        } catch (FileNotFoundException e) {   //ignore exception
            e.printStackTrace();    //used to identify problems
        }

//        todo: Remove
//        Scanner in = null;     //Scanner to read the file at chosen location
//        try {
//            in = new Scanner(new FileReader(fileName));
//        } catch (FileNotFoundException e) {   //ignore exception
//            e.printStackTrace();    //used to identify problems
//        }
//        int i = 0;       //Number of words in document
//        while (in.hasNextLine()) {        //read every line of file
//            in.next();      //move to next word
//            i++;            //increment word counter
//        }
//        in.close();     //Close Scanner after words have been counted
//
//        try {
//            in = new Scanner(new FileReader(fileName));     //New Scanner to start at top of file
//        } catch (FileNotFoundException e) {   //ignore exception
//            e.printStackTrace();    //used to identify problems
//        }
//
//        // carsten: do need to store in an array, only the last word is required
//        //            String[] word = new String[i];      //Array stores each word
//        int pause = milisec;
//        for (int k = 0; k < i; k++) { //loop cycles through every word in file
//            String nextWord = in.next();
//            readout(nextWord);
//            sleep(pause);     //call sleep method to add a pause after the word.
//        }
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
