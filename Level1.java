import mayflower.*;

public class Level1 extends World {
    private final Axion axion;
    private final String[][] tiles;

    public Level1() {
        setBackground("img/BG/level1.png");

        // Creating a new Axion object. We tried using a 2D array but changing worlds would be broken
        // since we can't access properties of Axion through the 2D array.
        axion = new Axion();
        addObject(axion, 20, 350);
        tiles = new String[6][8];

        initializeCourse();
        addIslands();
        addGems();
        addLadders();
        addGhosts();
        buildWorld();
    }

    public void act() {
        if (axion.getLives() == 0) {
            MyMayflower.changeWorld(new YouLose());
        }
        // Proceed to Level 2 if Axion reaches the top of the screen
        if (axion.getY() - 10 <= 0) {
            MyMayflower.changeWorld(new Level2());
        }
    }

    private void initializeCourse() {
        // Initialize all tiles to empty strings
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[0].length; j++)
                tiles[i][j] = "";
        // Set the bottom row to "ground" tiles
        for (int i = 0; i < tiles[0].length; i++)
            tiles[5][i] = "ground";
    }

    private void addGems() {
        // Randomly place gems on the 5th row (index 4)
        for (int r = 4; r < 5; r++) {
            for (int c = 0; c < tiles[r].length - 2; c++) {
                int rand = 0;
                rand = (int) (Math.random() * 9) + 1;
                if (rand <= 3 && tiles[r][c].equals("")) {
                    tiles[r][c] = "Gem";
                }
            }
        }
        // Place a gem at the last column of the bottom row to make gameplay more interesting
        tiles[4][5] = "Gem";
    }

    // Add islands at specific locations using tiles array
    private void addIslands() {
        tiles[3][1] = "island";
        tiles[2][3] = "island";
        tiles[3][5] = "island";
    }

    private void addLadders() {
        tiles[0][7] = "ladder";
        tiles[3][0] = "ladder";
        tiles[2][5] = "ladder";
    }

    private void addGhosts() {
        tiles[2][2] = "ghost";
    }

    // Build the world by adding objects based on the tiles array
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
                    addObject(new Ladder(), c * 64, r * 95);
                else if (tiles[r][c].equals("ghost"))
                    addObject(new Ghost(), c * 128 + 110, r * 95 - 65);
            }
        }
    }
}
