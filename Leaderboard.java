import mayflower.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Leaderboard extends World
{

    public Leaderboard()
    {
        setBackground("img/BG/leaderboard.png");

        // Read file contents
        Path filePath = Path.of("timeLog.txt");
        String fileContent = null;
        try {
            fileContent = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = fileContent.split("\n");
        String[][] results = topThree(lines);

        // Place text on screen
        showText(results[0][0] + " | " + results[1][0], 300, 280, Color.WHITE);
        showText(results[0][1] + " | " + results[1][1], 300, 330, Color.WHITE);
        showText(results[0][2] + " | " + results[1][2], 300, 380, Color.WHITE);
    }

    public void act() {
    }

    // Get timestamps and names with order maintained.
    // Order the times from least to greatest and return top three times and associated names
    public String[][] topThree(String[] fileData) {
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<Float> secondsList = new ArrayList<Float>();

        for (int i = 0; i < fileData.length; i++) {
            String[] parts = fileData[i].split(": ");
            String name = parts[0];
            nameList.add(name);
            String[] timeParts = parts[1].split(" ");
            // Combine seconds and milliseconds into a float: seconds.milliseconds
            float time = Float.parseFloat(timeParts[0] + "." + timeParts[1]);
            secondsList.add(time);
        }

        for (int i = 0; i < secondsList.size() - 1; i++) {
            for (int j = i + 1; j < secondsList.size(); j++) {
                if (secondsList.get(i) > secondsList.get(j)) {
                    // Swap times
                    float tempTime = secondsList.get(i);
                    secondsList.set(i, secondsList.get(j));
                    secondsList.set(j, tempTime);
                    // Swap names
                    String tempName = nameList.get(i);
                    nameList.set(i, nameList.get(j));
                    nameList.set(j, tempName);
                }
            }
        }

        String[][] topArray = new String[2][3];
        for (int i = 0; i < topArray.length; i++) {
            for (int j = 0; j < topArray[0].length; j++) {
                if (i == 0) topArray[i][j] = nameList.get(j);
                else if (i == 1) topArray[i][j] = String.valueOf(secondsList.get(j));
            }
        }
        return topArray;
    }
}
