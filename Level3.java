import mayflower.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Level3 extends World {
    private final Axion axion;
    private final String[][] tiles;

    public Level3() {
        setBackground("img/BG/level3.png");

        // Creating a new Axion object. We tried using a 2D array but changing worlds would be broken
        // since we can't access properties of Axion through the 2D array.
        axion = new Axion();
        addObject(axion, 80, 300);
        tiles = new String[6][8];

        initializeCourse();
        addIslands();
        addLadders();
        addGems();
        addGhosts();
        buildWorld();
    }

    // World changing conditions
    public void act() {
        if (axion.getLives() == 0) {
            MyMayflower.changeWorld(new YouLose());
        }
        if (axion.getY() - 10 <= 0) {
            try {
                saveTime();
                MyMayflower.changeWorld(new YouWin());
            } catch (IOException e) {
                System.out.println("Failed to save time: " + e.getMessage());
            }
        }
    }

    public void saveTime() throws IOException {
//        try {
        // Get player's name and add their time to the text file
        Path filePath = Path.of("timeLog.txt");
        String fileContent = Files.readString(filePath);
        String[] lines = fileContent.split("\n");
        String time = StartScreen.name + ": " + String.format("%02d", Axion.stopTime / 60) + " " + String.format("%02d", Axion.stopTime % 60);
//            Files.writeString(filePath, time + "\n", java.nio.file.StandardOpenOption.APPEND);
        FileWriter writer = new FileWriter("timeLog.txt", true);
        writer.write(time + "\n");
        writer.close();
//            System.out.println("Time saved: " + time);
//        }
//        catch (IOException e) {
//            System.out.println("An error occurred while reading or writing the file: " + e.getMessage());
//        }
    }

    // Set 2D array to empty string and add ground tiles
    private void initializeCourse() {
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
                tiles[i][j] = "";

        for (int i = 0; i < tiles[0].length; i++)
            tiles[5][i] = "ground";
    }

    // Randomly place gems on the 5th row (index 4)
    private void addGems() {
        for (int r = 4; r < 5; r++) {
            for (int c = 0; c < tiles[r].length - 2; c++) {
                int rand = 0;
                rand = (int) (Math.random() * 9) + 1;
                if (rand <= 2 && tiles[r][c].equals("")) {
                    tiles[r][c] = "Gem";
                }
            }
        }
        tiles[4][7] = "Gem";
    }

    private void addLadders() {
        tiles[3][3] = "ladder";
        tiles[0][1] = "ladder";
    }

    private void addIslands() {

        tiles[3][4] = "island";
        tiles[2][2] = "island";
    }

    private void addGhosts() {
        tiles[1][2] = "ghost";
        tiles[4][3] = "ghost";
    }

    // Iterate through the 2D array and add objects to the screen based on the string values
    public void buildWorld() {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (tiles[r][c].equals("ground"))
                    addObject(new Block(), c * 128, r * 95);
                else if (tiles[r][c].equals("island"))
                    addObject(new Island(), c * 128, r * 95);
                else if (tiles[r][c].equals("Gem"))
                    addObject(new Gem(), c * 128, r * 95);
                else if (tiles[r][c].equals("ladder"))
                    addObject(new Ladder(), c * 128 + 64, r * 95 + 10);
                else if (tiles[r][c].equals("ghost"))
                    addObject(new Ghost(), c * 128 + 60, r * 50);
            }
        }
    }
}