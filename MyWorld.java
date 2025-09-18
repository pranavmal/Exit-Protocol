import mayflower.*;

public class MyWorld extends World {
    private Cat cat;
    private Block block;
    private Dog dog;    
    private Jack jack;
    private Ninja ninja;
    private int level;
    private String[][] tiles;
    public MyWorld(){
        setBackground("img/BG/BG.png");
        level = 1;
        cat = new Cat();
        addObject(cat,400, 10);

        // dog = new Dog();
        // addObject(dog, 200, 122);

        //jack = new Jack();
        //addObject(jack, 80, 70);

        //ninja = new Ninja();
        //addObject(ninja, 320, 86);
        tiles = new String[6][8];
        initializeGround();
        addYarn();
        buildWorld();

    }

    public void act()
    {
        if (cat.getLives() == 0)
        {
            MyMayflower.changeWorld(new YouLose());
        }
    }

    private void initializeGround()
    {
        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; j < tiles[0].length; j++)
                tiles[i][j]="";

        for(int i = 0; i < tiles[0].length; i++)
            tiles[5][i]+="ground";
    }

    private void addYarn()
    {

        if (level == 1)
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
        else if (level == 2)
        {
            for (int r = 0; r < tiles.length; r++)
            {
                for (int c = 0; c < tiles[r].length; c++)
                {
                    int rand = 0;
                    rand = (int) (Math.random() * 9) + 1;
                    if (rand <= 3 && tiles[r][c].equals(""))
                    {
                        tiles[r][c] = "Yarn";
                    }
                }
            }
        }
        else if (level == 3)
        {
            for (int r = 0; r < tiles.length; r++)
            {
                for (int c = 0; c < tiles[r].length; c++)
                {
                    int rand = 0;
                    rand = (int) (Math.random() * 9) + 1;
                    if (rand <= 4 && tiles[r][c].equals(""))
                    {
                        tiles[r][c] = "Yarn";
                    }
                }
            }
        }
        //boolean added = false;
        //  int row;
        //int col;
        /*  while(!added){
        row = (int) (Math.random()*tiles.length);
        col = (int) (Math.random()*(tiles[0].length-3));
        if(tiles[row][col]=="")
        {
        addObject (new Jack(), row*128, col*94);
        tiles[row][col]="jack";
        added=true;
        }*/
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
