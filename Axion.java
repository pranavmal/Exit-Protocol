import mayflower.*;
import java.util.ArrayList;

public class Axion extends MovableAnimatedActor
{
    private final Animation walkRight;
    private final Animation walkLeft;
    private final Animation idleRight;
    private final Animation fallRight;
    private final Animation fallLeft;
    private final Animation idleLeft;
    private final Animation climbingR;
    private final Animation climbingL;
    private final String[] walkRightFrames;
    private final String[] idleLeftFrames;
    private final String[] fallRightFrames;
    private final String[] fallLeftFrames;
    private final String[] idleRightFrames;
    private final String[] walkLeftFrames;
    private final String[] climbingRFrames;
    private final String[] climbingLFrames;
    private final int x;
    private static int score = 0;
    private static int lives = 3;
    private final ArrayList<Heart> hearts;
    private final Timer climbTimer;
    private Axion.Direction lastArrowKey;
    private final Orb o;
    private int count;
    private static final Timer stopwatch = new Timer(1000);
    public static  int stopTime = 0;

    public enum Direction
    {
        LEFT, RIGHT
    }

    public Axion()
    {
        x = 0;
        if (MovableAnimatedActor.getDirection().equals("left")) {
            lastArrowKey = Axion.Direction.LEFT;
        }
        else {
            lastArrowKey = Axion.Direction.RIGHT;
        }
        hearts = new ArrayList<Heart>();
        climbTimer = new Timer(1000000);
        count = 0;
        o = new Orb("right");

        // Add image path files to a string and create animation object
        climbingRFrames = new String[3];
        for( int i =0; i<3; i++)
            climbingRFrames[i] = "img/axion/Climb/Climb_00" + i + ".png";
        climbingR = new Animation(climbingRFrames);
        climbingR.scale(108,76);

        climbingLFrames = new String[3];
        for( int i =0; i<3; i++)
            climbingLFrames[i] = "img/axion/Climb/Climb_00" + i + ".png";
        climbingL = new Animation(climbingLFrames);
        climbingL.scale(108,76);
        climbingL.mirrorHorizontally();

        walkRightFrames = new String[8];
        for( int i =0; i<8; i++)
            walkRightFrames[i] = "img/axion/Run/Run_00" + i + ".png";
        walkRight = new Animation(walkRightFrames);
        walkRight.scale(108,76);

        walkLeftFrames = new String[8];
        for(int i = 0; i < 8; i++)
            walkLeftFrames[i] = "img/axion/Run/Run_00" + i + ".png";
        walkLeft = new Animation(walkLeftFrames);
        walkLeft.scale(108,76);
        walkLeft.mirrorHorizontally();
        
        idleRightFrames = new String[7];
        for(int i = 0; i < 7; i++)
            idleRightFrames[i] = "img/axion/Idle/Idle_00" + i + ".png";
        idleRight = new Animation(idleRightFrames);
        idleRight.scale(96,84);

        idleLeftFrames = new String[7];
        for(int i = 0; i < 7; i++)
            idleLeftFrames[i] = "img/axion/Idle/Idle_00" + i + ".png";
        idleLeft = new Animation(idleLeftFrames);
        idleLeft.scale(96,84);
        idleLeft.mirrorHorizontally();

        fallRightFrames = new String[4];
        for(int i = 0; i < 4; i++)
            fallRightFrames[i] = "img/axion/Fall/Fall_00" + i + ".png";
        fallRight = new Animation(fallRightFrames);
        fallRight.scale(104,76);

        fallLeftFrames = new String[4];
        for(int i = 0; i < 4; i++)
        {
            fallLeftFrames[i] = "img/axion/Fall/Fall_00" + i + ".png";
        }
        fallLeft = new Animation(fallLeftFrames);
        fallLeft.scale(104,76);
        fallLeft.mirrorHorizontally();
        
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
        // Update score and lives text on screen
        World w = getWorld();
        w.removeText(10,30);
        w.removeText(675, 30);
        w.showText("Score: " + score + " lives: " + lives, 10, 30, Color.WHITE);
        w.showText(String.format("%02d", stopTime / 60) + ":" + String.format("%02d", stopTime % 60), 675, 30, Color.WHITE);

        // Delete and add new hearts to the screens depending on number of lives
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
        setLocation(20,350);
    }
    
    public void act()
    {
        super.act();
        if (MovableAnimatedActor.getDirection().equals("left")) {
            lastArrowKey = Axion.Direction.LEFT;
        }
        else {
            lastArrowKey = Axion.Direction.RIGHT;
        }
        updateText();
        World wo = getWorld();
        
        if(lives == 0)
        {
         wo.removeObject(this);
         setLocation(400,300);
        }

        if (stopwatch.isDone()) {
            stopTime++;
            stopwatch.reset();
        }

        super.setTouchingLadder(isTouching(Ladder.class));
        super.setTouchingLayerBlock(isTouching(LayerBlock.class));

        // When Axion is climbing the ladder, set climbing animation based on last direction faced and moves up by 1.1 by step from GravityActor
        while (climbTimer.isDone() && isTouching(Ladder.class) && Mayflower.isKeyDown(Keyboard.KEY_UP)) {
            climbTimer.reset();
            setLocation(getX(), getY() - 1.1*getStep());
            if (lastArrowKey == Axion.Direction.LEFT) {
                setClimbingAnimation(climbingL);
                setAnimation(climbingL);
            }
            else {
                setClimbingAnimation(climbingR);
                setAnimation(climbingR);
            }
        }

        // Create inital orb and offset location by different lengths depending on Axion's direction
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE)) {
            if ((o.getX() > 800 || o.getX() < 0)) {
                if (lastArrowKey == Axion.Direction.RIGHT) {
                    wo.addObject(o.setDirection("right"), getX() + 90, getY()+ (int) ((3.0/8)*getHeight()));
                }
                else {
                    wo.addObject(o.setDirection("left"), getX() - 15, getY()+ (int) ((3.0/8)*getHeight()));
                }
                count++;
            }
            else if (count == 0) {
                if (lastArrowKey == Axion.Direction.RIGHT) {
                    System.out.println((int) ((3.0/8)*getHeight()));
                    wo.addObject(o.setDirection("right"), getX() + 90, getY()+ (int) ((3.0/8)*getHeight()));
                }
                else {
                    wo.addObject(o.setDirection("left"), getX() - 15, getY()+ (int) ((3.0/8)*getHeight()));
                }
                count++;
            }
        }
    }
}