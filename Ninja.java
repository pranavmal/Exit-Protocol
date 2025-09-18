import mayflower.*;

public class Ninja extends AnimatedActor
{
    private Animation walk;
    private String[] frames;

    public Ninja() 
    {
        frames = new String[10];
        for( int i =0; i<10; i++)
        {
            
            frames[i] = new String("img/ninjagirl/Jump_Attack__00" + (i)+ ".png");

        }
        walk = new Animation(frames);
        setAnimation(walk);
        walk.scale(380,87);
                walk.setTransparency(80);
    }
    public void act()
    {
        super.act();
    }
}
