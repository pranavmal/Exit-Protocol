import mayflower.*;

public class Jack extends AnimatedActor
{
    private Animation walk;
    private String[] frames;

    public Jack() 
    {
        frames = new String[7];
        for( int i =0; i<7; i++)
        {
            
            frames[i] = new String("img/jack/Walk (" + (i+1)+ ").png");

        }
        walk = new Animation(frames);
        setAnimation(walk);
        walk.scale(100,87);
        
    }
    public void act()
    {
        super.act();
        if(isTouching(Cat.class))
        {
            Object a = getOneIntersectingObject(Cat.class);
            Cat c = (Cat) a;
            World w = getWorld();
            c.decreaseLives(1);
        }
    }
}
