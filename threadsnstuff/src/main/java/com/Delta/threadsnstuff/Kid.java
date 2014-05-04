package com.Delta.threadsnstuff;

import android.util.Log;

/**
 * Created by mollyrand on 2/19/14.
 */
public class Kid implements Runnable {

    public static String kidAssembledString = "";
    public static String inputString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    private String name;
    private Thread t;
    public static int groupCounter = 0;
    public static int numKids = 0;
    private int kidID;
    private final long MILLIS = 1000;//not actually used in final version
    private static final int maxLettersPerKidPerRound = 3;
    private static int  lettersInInputString = inputString.length();

    public void start(){
        if (t == null){
            t = new Thread(this);
            Log.e(name, "Ready!");
            kidID = numKids;
            numKids++;
            t.start();
        }
    }

    public void stop(){
        if (t != null){
            Log.e(name, "naptime");
            t = null;
        }

    }

    public Kid(String kidName){
        this.name = kidName;
    }

    @Override
    public void run() {
//        int counter = 0;
//        int lettersInInputString = inputString.length();
        while (kidAssembledString.length() < lettersInInputString){
            int lettersPerRound = numKids * maxLettersPerKidPerRound;
            int position = kidAssembledString.length() % lettersPerRound;
            int startingPos = kidID * maxLettersPerKidPerRound;
            int endingPos = kidID * maxLettersPerKidPerRound + maxLettersPerKidPerRound;
//            if((kidID*3)<=(kidAssembledString.length()%lettersPerRound) && (kidAssembledString.length()%lettersPerRound)<(kidID*3)+3 ){ //refactor
            if(startingPos <= position && position < endingPos ){
                addNextLetter();
//                counter++;
            }
         }
        stop();
    }
//
//    public synchronized int checkLetter(){
//        return kidAssembledString.length();
//    }

    public synchronized void addNextLetter(){
        int letter = kidAssembledString.length();
        if(letter < lettersInInputString){
            kidAssembledString = kidAssembledString.concat(inputString.substring(letter, letter + 1));
        }
        Log.e(name, kidAssembledString);
    }
}
