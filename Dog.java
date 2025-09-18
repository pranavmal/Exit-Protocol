import mayflower.*;

public class Dog extends MovableAnimatedActor
{
    private Animation walk;
    private Animation idle;
    private String[] frames;
    private String[] idleF;

    public Dog() 
    {
        frames = new String[10];
        for( int i =0; i<10; i++)
        {

            frames[i] = new String("img/dog/Walk (" + (i+1)+ ").png");

        }
        walk = new Animation(frames);
        walk.scale(100,87);
        idleF = new String[10];
        for(int i = 0; i < 10; i++)
            idleF[i] = new String("img/cat/Idle (" + (i+1) + ").png");
        idle = new Animation(idleF);
        idle.scale(100,87);
        setWalkRightAnimation(walk);
        setIdleAnimation(idle);
    }

    public void act()
    {
        super.act();
        if(Mayflower.isKeyDown(Keyboard.KEY_UP) && getY()>0)
            setLocation(getX(), getY()-2);
        if(Mayflower.isKeyDown(Keyboard.KEY_DOWN)&& getY()<500)
            setLocation(getX(), getY()+2);
        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT)&& getX()<720)
        {
            //newAction = "walkRight";
            setLocation(getX()+2, getY());
        }
        if(Mayflower.isKeyDown(Keyboard.KEY_LEFT)&& getX()>-10)
        {
            //newAction = "walkLeft";
            setLocation(getX()-2, getY());        
        }
    }
}
