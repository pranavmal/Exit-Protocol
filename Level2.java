import mayflower.*;

public class Level2 extends World
{
    private Axion axion;
    private String[][] tiles;
    private Ghost ghost1;
    private Ghost ghost2;
    private Island island2;
    private Ladder ladder1;
    private Island island1;
    private Ladder ladder3;
    private Ladder ladder2;

    public Level2()
    {
        setBackground("img/BG/level2.png");
        axion = new Axion();
        addObject(axion,30, 350);

        island1 = new Island();
        island2 = new Island();
        ladder1 = new Ladder();
        ladder3 = new Ladder();
        ladder2 = new Ladder();
        addObject(ladder1, 240,290);
        addObject(ladder3 , 720, 0);
        addObject(ladder2 , 520,110);
        addObject(island2, 585, 120);
        addObject(island1, 300, 300);
        
        ghost1 = new Ghost();
        addObject(ghost1, 315, 200);
        ghost2 = new Ghost();
        addObject(ghost2, 600, 20);
        
        tiles = new String[6][8];
        initializeCourse();
        addYarn();
        buildWorld();
    }
    
    public void act()
    {
        if (axion.getLives() == 0)
        {
            MyMayflower.changeWorld(new YouLose());
        }
        if(axion.getY()-10 <= 0)
        {
            MyMayflower.changeWorld(new Level3());
        }
    }

    private void initializeCourse()
    {
        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; j < tiles[0].length; j++)
                tiles[i][j]="";

        for(int i = 0; i < tiles[0].length; i++)
            tiles[5][i]+="ground";
    }

    private void addYarn()
    {
        for (int r = 0; r < tiles.length; r++)
        {
            for (int c = 0; c < tiles[r].length; c++)
            {
                int rand = 0;
                rand = (int) (Math.random() * 9) + 1;
                if (rand <= 2 && tiles[r][c].equals(""))
                {
                    tiles[r][c] = "Yarn";
                }
            }
        }
    }

    public void buildWorld()
    {  

        for (int r = 0; r < tiles.length; r++)
        {
            for (int c = 0; c < tiles[r].length; c++)
            {
                if(tiles[r][c].equals("ground"))
                    addObject (new Block(), c*128, r*95);
                if (tiles[r][c].equals("Yarn"))
                    addObject(new Yarn(), c * 128, r * 95);

            }
        }
    }
}
