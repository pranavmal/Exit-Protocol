import mayflower.*;
import java.util.*;

public class MyWorld extends World {
    private Cat cat;
    private Block block;
    private Dog dog;    
    private Jack jack;
    private Ninja ninja;
    private Ladder l1;
    private ArrayList<Integer> ladderX;
    private ArrayList<Integer> ladderY;
    private String[][] tiles;
    public MyWorld(){
        ladderX = new ArrayList<Integer>();
        ladderY = new ArrayList<Integer>();
        
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
        
        
        l1 = new Ladder();
        addLadder(l1, 256, 325);
        
        addObject(new LayerBlock(), 430, 120);
    }
    
    private void addLadder(Ladder l, int x, int y) {
        addObject(l, x, y);
        ladderX.add(x);
        ladderY.add(y);
    }
    
    public void act(){
        if (cat.getLives() == 0) {
            MyMayflower.changeWorld(new YouLose());
        }
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
