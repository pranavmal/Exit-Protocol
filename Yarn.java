
/**
 * Write a description of class Yarn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import mayflower.*;
public class Yarn extends Actor
{
    public Yarn()
    {
        setImage("img/yarn.png");
    }
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
