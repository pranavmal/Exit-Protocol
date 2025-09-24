import mayflower.*;    

public class YouWin extends World
{
    
    public YouWin()
    {
        setBackground("img/BG/YouWin.png");
    }
    
    public void act() 
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE))
            {
                MyMayflower.changeWorld(new StartScreen());
            }
    }
}