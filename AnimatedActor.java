import mayflower.*;
/**
 * Write a description of class AnimatedActor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AnimatedActor extends GravityActor
{
    // instance variables - replace the example below with your own
    private Timer time;
    private Animation animation;
    /**
     * Constructor for objects of class AnimatedActor
     */
    public AnimatedActor()
    {
        time = new Timer(100000000);

    }

    public void setAnimation(Animation a)
    {
        animation = a;
    }

    public void act(){
        if(time.isDone()){
            time.reset();
            MayflowerImage img = animation.getNextFrame();
            setImage(img);
        }
        super.act();

    } 
}