import mayflower.*;

public class Level1 extends World
{
    private Cat cat;
    private int level;
    private String[][] tiles;
    private Jack jack;
    private int screenIndex;
    private Spike spike;

    public Level1()
    {
        setBackground("img/BG/BG.png");
        screenIndex = 0;
        cat = new Cat();
        addObject(cat,400, 10);
        
        jack = new Jack();
        addObject(jack, 80, 70);
        
        tiles = new String[6][8];
        
        initializeCourse();
        addYarn();
        buildWorld();
        spike = new Spike();
        addObject(spike, 450, 450);
        
    }
    
    public void act()
    {
        if (cat.getLives() == 0)
        {
            MyMayflower.changeWorld(new YouLose());
        }
        if(cat.getY()-10 <= 0)
        {
            nextScreen();
        }
    }
    public void nextScreen()
    {
        screenIndex++;
        if(screenIndex == 1)
        {
            cat.setLocation(400,100);
            jack.setLocation(100, 100);
            spike.setLocation(450,450);
            removeObject(spike);
            addObject(spike, 350 , 250);
            
            addYarn();
            buildWorld();
            
            
        }
        else if(screenIndex == 2)
        {
             cat.setLocation(400,200);
            jack.setLocation(200,100);
            spike.setLocation(450,450);
            initializeCourse();
            addYarn();
            buildWorld();
            level = 2;
        }
        else if(level == 2)
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
