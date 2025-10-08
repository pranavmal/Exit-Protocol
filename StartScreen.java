import mayflower.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class StartScreen extends World
{
    public static String name = getName();
    public StartScreen()
    {
        setBackground("img/BG/start.png");
    }

    // Change world when space is pressed
    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE))
        {
            MyMayflower.changeWorld(new Level1());
        }
    }

    public static String getName() {
        String name = "";
        try {
            System.out.println("What's your name? ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            name = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
