/*
Kurt Kaiser
CTIM 168
07.28.2018
Homework: C10PP10
RFID Race Data Text File Log
 */
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class RaceDataLog {

    private String[][] data = new String[9][];

    public String[][] getData() {
        return data;
    }

    // Constructor
    public RaceDataLog(String fileName) throws FileNotFoundException {
        Scanner inputStream = new Scanner(new File(fileName));
        String startTime = inputStream.nextLine();
        int count = 0;
        String breakUp = "[, ]";
        // Read the rest of the file line by line
        while (inputStream.hasNextLine()) {
            // Use split to create two dimensional array
            data[count] = inputStream.nextLine().split(breakUp);
            count++;
        }
        inputStream.close();
    }

    // Returns run time in seconds of requested number
    public int[] getTimes(String input) {
        int[] runnerTimes = new int[3];
        String[][] data = getData();
        int count = 0;
        int intTime;
        int place = 1;
        // Runners Place
        for (int j = 0; j < data.length; j++) {
            if (data[j][0].equals("2")) {
                if (data[j][1].equals(input)) {
                }
                place++;
            }
        }
        for (int i = 0; i < data.length; i++) {
            if (input.contentEquals(data[i][1])) {
                intTime = 3600 * Integer.parseInt(data[i][2]);
                intTime += 60 * Integer.parseInt(data[i][3]);
                intTime += Integer.parseInt(data[i][4]);
                runnerTimes[count] = intTime;
                count++;
            }
        }
        return runnerTimes;
    }

    // Returns place of runner
    public int getPlace(String input) {
        String[][] data = getData();
        int place = 1;
        // Runners Place
        for (int j = 0; j < data.length; j++) {
            if (data[j][0].equals("2")) {
                if (data[j][1].equals(input)) {
                    return place;
                }
                place++;
            }
        }
        return 0;
    }

    // Calculate runners pace between markers, output to console
    public String getSplits(int[] runnerTimes, int startSplit) {
        int dist = 1;
        if (startSplit == 0) {
            dist = 7;
        } else if (startSplit == 1) {
            dist = 6;
        } else {
            return "error";
        }
        int split = (runnerTimes[startSplit + 1] - runnerTimes[startSplit]) / dist;
        String result = getFormattedTime(split);
        return result;
    }

    public String getFormattedTime(int total){
        int[] timeArray = new int[3];
        String strTime;
        String result = "";
        timeArray[0] = total / 3600;
        timeArray[1] = (total - (timeArray[0] * 3600)) / 60;
        timeArray[2] = total - timeArray[0] * 3600 - timeArray[1] * 60;
        for (int i = 0; i < 3; i++) {
            strTime = String.valueOf(timeArray[i]);
            if (strTime.length() == 1) strTime = "0" + strTime;
            result += strTime;
            if (i < 2) result += ":";
        }
        return result;
    }

    // Calculate overall race time
    public String[] getOverallTimes(int[] runnerTimes) {
        String[] overallTimes = new String[2];
        int total = runnerTimes[2] - runnerTimes[0];
        for (int i = 0; i<2; i++) {
            overallTimes[i] = getFormattedTime(total);
            total = total / 13;
        }
        return overallTimes;
    }
}
