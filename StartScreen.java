import mayflower.*;

public class StartScreen extends World
{
    public StartScreen()
    {
        setBackground("img/BG/start.png");
    }

    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE))
        {
            MyMayflower.changeWorld(new Level1());
        }
    }
}
