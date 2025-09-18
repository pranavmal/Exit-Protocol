import mayflower.*;
/**
 * Write a description of class Animation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Animation
{
    // instance variables - replace the example below with your own
    private MayflowerImage[] frames;
    private int currentFrame;

    /**
     * Constructor for objects of class Animation
     */
    public Animation(String[] arr)
    {
        frames= new MayflowerImage[arr.length];
        for(int i = 0; i < frames.length; i++)
            frames[i] = new MayflowerImage(arr[i]);
        currentFrame = 0;
    }

    public MayflowerImage getNextFrame(){
        if(currentFrame>=frames.length)
            currentFrame %= frames.length;
        MayflowerImage image = frames[currentFrame];
        currentFrame++;
        return image;

    }

    public void scale(int w, int h){
        for(int i = 0; i < frames.length; i++)
            frames[i].scale(w,h);
    }

    public void setTransparency(int percent)
    {
        for(int i = 0; i < frames.length; i++)
            frames[i].setTransparency(percent);
    }

    public void mirrorHorizontally()
    {
        for(int i = 0; i < frames.length; i++)
            frames[i].mirrorHorizontally();        
    }

    public void setBounds(int x, int y, int w, int h)
    {
        for(int i = 0; i < frames.length; i++)
            frames[i].crop(x,y,w,h);
        
    }
}