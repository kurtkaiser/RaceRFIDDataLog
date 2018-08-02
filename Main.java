/*
Kurt Kaiser
CTIM 168
07.28.2018
Homework: C10PP10
RFID Race Data Text File Log
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    // Demonstrating the program
    public static void main(String[] args) {
        String fileName =
                "raceTextData.txt";
        int[] runnerTimes; // = new int[3];
        String splits;
        int place; // = 0;
        String[] overallTimes; // = new String[2];
        try {
            RaceDataLog recent = new RaceDataLog(fileName);
            // User input
            System.out.print("Enter a racer's number: ");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            // Calling class methods
            runnerTimes = recent.getTimes(input);
            place = recent.getPlace(input);
            System.out.println("Place: " + place);
            splits = recent.getSplits(runnerTimes, 0);
            System.out.println("Split 1-2: " + splits);
            splits = recent.getSplits(runnerTimes, 1);
            System.out.println("Split 2-3: " + splits);
            overallTimes = recent.getOverallTimes(runnerTimes);
            System.out.println("Overall Time: " + overallTimes[0]);
            System.out.println("Overall Pace: " + overallTimes[1]);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file " + fileName);
        }
    }
}