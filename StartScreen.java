import mayflower.*;

public class StartScreen extends World
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class StartScreen
     */
    public StartScreen()
    {
        setBackground("img/BG/TitleScreen.png");
    }

    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE))
        {
            MyMayflower.changeWorld(new Level1());
        }
    }
}
