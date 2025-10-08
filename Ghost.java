import mayflower.*;

public class Ghost extends AnimatedActor {
    private final String[] frames;
    private final Animation hover;

    public Ghost() {
        frames = new String[3];
        for( int i =0; i<3; i++)
        {
            frames[i] = "img/ghost/ghost_00" + i + ".png";
        }
        hover = new Animation(frames);
        setAnimation(hover);
        hover.scale(60,52);
    }

    // Decrease Axion's lives if touching him, remove self if touching Orb
    public void act() {
        super.act();
        World w = getWorld();
        if(isTouching(Axion.class))
        {
            Object a = getOneIntersectingObject(Axion.class);
            Axion c = (Axion) a;
            c.decreaseLives(1);
        }
        else if (isTouching(Orb.class)) {
            w.removeObject(this);
        }
    }
}
