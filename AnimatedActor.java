import mayflower.*;

public class AnimatedActor extends GravityActor {
    private final Timer time;
    private Animation animation;

    public AnimatedActor() {
        time = new Timer(100000000);
    }

    public void setAnimation(Animation a) {
        animation = a;
    }

    // When timer is over get and set the next frame
    public void act() {
        if (time.isDone()) {
            time.reset();
            MayflowerImage img = animation.getNextFrame();
            setImage(img);
        }
        super.act();
    }
}