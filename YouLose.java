import mayflower.*;    

public class YouLose extends World
{
    
    public YouLose()
    {
        setBackground("img/BG/YouLose.png");
    }
    
    public void act() 
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE))
            {
                MyMayflower.changeWorld(new StartScreen());
            }
    }
}
