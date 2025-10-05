import mayflower.*;

public class Level1 extends World
{
    private Axion axion;
    private String[][] tiles;
    private Ghost ghost1;
    private Spike spike;
    private Island island2;
    private Ladder ladder;
    private Island island1;
    private Island island3;
    private Ladder ladder1;
    private Ladder ladder2;

    public Level1()
    {
        setBackground("img/BG/level1.png");
        axion = new Axion();
        addObject(axion,20, 350);

        island1 = new Island();
        island2 = new Island();
        island3 = new Island();
        ghost1 = new Ghost();
        addObject(ghost1, 355, 100);
        addObject(island3, 350, 175);
        addObject(island2, 500, 270);
        addObject(island1, 200, 300);
        
        tiles = new String[6][8];

        ladder = new Ladder();
        addObject(ladder, 150, 290);
//        ladder1 = new Ladder();
//        addObject(ladder1 , 360, 20);
//        ladder2 = new Ladder();
//        addObject(ladder2 , 220,170);

        initializeCourse();
        addYarn();
        buildWorld();
//        spike = new Spike();
//        addObject(spike, 450, 450);
        
    }
    
    public void act()
    {
        if (axion.getLives() == 0)
        {
            MyMayflower.changeWorld(new YouLose());
        }
        if(axion.getY()-10 <= 0)
        {
            MyMayflower.changeWorld(new Level2());
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
