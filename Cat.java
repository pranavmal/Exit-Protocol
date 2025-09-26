import mayflower.*;
import java.util.ArrayList;
public class Cat extends MovableAnimatedActor
{
    private Animation walkRight;
    private Animation walkLeft;
    private Animation idleRight;
    private Animation fallRight;
    private Animation fallLeft;
    private Animation idleLeft;
    private String[] walkRightFrames;
    private String[] idleLeftFrames;
    private String[] fallRightFrames;
    private String[] fallLeftFrames;
    private String[] idleRightFrames;
    private String[] walkLeftFrames;
    private int x;
    private int score;
    private int lives;
    private ArrayList<Heart> hearts;
    private Timer climbTimer;
    public Cat() 
    {
        x = 0;
        hearts = new ArrayList<Heart>();
        climbTimer = new Timer(1000000);
        score = 0;
        lives = 3;
        walkRightFrames = new String[10];
        for( int i =0; i<10; i++)
            walkRightFrames[i] = new String("img/cat/Walk (" + (i+1)+ ").png");
        walkRight = new Animation(walkRightFrames);
        walkRight.scale(100,87);
        walkRight.setBounds(18,5,54,80);
        
        idleRightFrames = new String[10];
        for(int i = 0; i < 10; i++)
            idleRightFrames[i] = new String("img/cat/Idle (" + (i+1) + ").png");
        idleRight = new Animation(idleRightFrames);
        idleRight.scale(100,87);
        idleRight.setBounds(18,5,54,80);
        
        walkLeftFrames = new String[10];
        for(int i = 0; i < 10; i++)
            walkLeftFrames[i] = new String("img/cat/Walk (" + (i+1) + ").png");
        walkLeft = new Animation(walkLeftFrames);
        walkLeft.scale(100,87);
        walkLeft.mirrorHorizontally();
        walkLeft.setBounds(28,5,54,80);

        fallRightFrames = new String[8];
        for(int i = 0; i < 8; i++)
            fallRightFrames[i] = new String("img/cat/Fall (" + (i+1) + ").png");
        fallRight = new Animation(fallRightFrames);
        fallRight.scale(100,87);
        fallRight.setBounds(18,5,54,80);
        fallLeftFrames = new String[8];
        for(int i = 0; i < 8; i++)
        {
            fallLeftFrames[i] = new String("img/cat/Fall (" + (i+1) + ").png");
        }
        fallLeft = new Animation(fallLeftFrames);
        fallLeft.scale(100,87);
        fallLeft.mirrorHorizontally();
        fallLeft.setBounds(28,5,54,80);
        idleLeftFrames = new String[10];
        for(int i = 0; i < 10; i++)
            idleLeftFrames[i] = new String("img/cat/Idle (" + (i+1) + ").png");
        idleLeft = new Animation(idleLeftFrames);
        idleLeft.scale(100,87);
        idleLeft.mirrorHorizontally();
        idleLeft.setBounds(28,5,54,80);
        
        setWalkRightAnimation(walkRight);
        setIdleAnimation(idleRight);
        setIdleLeftAni(idleLeft);
        setFallingAnimation(fallRight);
        setFallingLeft(fallLeft);
        setWalkLeftAnimation(walkLeft);
    }
    public int getScore(){ return score;}
    public int getLives(){return lives;}
    private void updateText()
    {
        World w = getWorld();
        w.removeText(10,30);
        w.showText("Score: " + score + " lives: " + lives, 10, 30, Color.BLACK);
        for (int j = hearts.size() - 1; j > 0; j--) {
                w.removeObject(hearts.get(j));
            }
        for (int i = 0; i < lives; i++) {
            
            hearts.add(new Heart());
            
            w.addObject(hearts.get(i), 50*(i+1), 50);
        }
        
    }
    public void increaseScore(int amount)
    {
        score += amount;
    }
    public void decreaseLives(int amount1)
    {
        lives -= amount1;
        setLocation(400,300);
    }
    
    public void act()
    {
        super.act();
        updateText();
        World wo = getWorld();
        
        if(lives == 0)
        {
         wo.removeObject(this);
         setLocation(400,300);
        }
        
        // System.out.println("Touching ladder: " + isTouching(Ladder.class) + " Key Up: " + Mayflower.isKeyDown(Keyboard.KEY_UP));
        super.setTouchingLadder(isTouching(Ladder.class));
        super.setTouchingLayerBlock(isTouching(LayerBlock.class));
        
        while (climbTimer.isDone() && isTouching(Ladder.class) && Mayflower.isKeyDown(Keyboard.KEY_UP)) {
            climbTimer.reset();
            setLocation(getX(), getY() - 1.1*getStep());
            System.out.println("Going up by one");
        }
        
        if (isTouching(Spike.class)) {
            decreaseLives(1);
            setLocation(250, 70);
        }
    }
}
