
import mayflower.*;
public class Gem extends Actor
{
    public Gem()
    {
        setImage("img/Gem.png");
    }

    // Increase Axion's score by 1 when touched and remove the gem from the world
    public void act()
    {
        if (isTouching(Axion.class))
        {
            Object a = getOneIntersectingObject(Axion.class);
            Axion c = (Axion) a;
            World w = getWorld();
            w.removeObject(this);
            c.increaseScore(1);
        }
    }
}
