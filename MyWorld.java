import mayflower.*;

public class MyWorld extends World {
    private Cat cat;
    private Block block;
    private Dog dog;    
    private Jack jack;
    private Ninja ninja;
        private String[][] tiles;
    public MyWorld(){
        setBackground("img/BG/BG.png");
        cat = new Cat();
        addObject(cat,400, 10);
        
     
        // dog = new Dog();
        // addObject(dog, 200, 122);

        jack = new Jack();
        addObject(jack, 80, 70);

        //ninja = new Ninja();
        //addObject(ninja, 320, 86);
        tiles = new String[6][8];
        buildWorld();
        
    }
    
    public void act(){
        if (cat.getLives() == 0) {
            MyMayflower.changeWorld(new YouLose());
        }
    }
    
    public void addRandomObjects()
    {
        int rand=0;
        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; j < tiles[0].length-3; j++)
            {
                rand= (int)(Math.random()*9);
                if(rand<3)
                {
                    if(tiles[i][j]==""){
                        tiles[i][j]+="yarn";
                        //addObject(new Yarn(), i*128,j*94);
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
        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; j < tiles[0].length; j++)
                tiles[i][j]="";

        for(int i = 0; i < tiles[0].length; i++)
            tiles[5][i]+="ground";

        for(int i = 0; i < tiles.length; i++)
            for(int j = 0; j < tiles[0].length; j++)
                if(tiles[i][j].equals("ground"))
                    addObject (new Block(), j*128, i*95);

    }
}
