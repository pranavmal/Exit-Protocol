import mayflower.*;

public class MovableAnimatedActor extends AnimatedActor {

    private Animation walkRight;
    private Animation walkLeft;
    private Animation idleRight;
    private Animation idleLeft;
    private Animation falling;
    private Animation fleft;
    private Animation climbingR;
    private final Animation climbingL;
    private AnimationState currentAction;
    private static String direction;
    private boolean isJumping;
    private boolean isBlocking;
    private boolean catTouchingLadder;
    private boolean catTouchingLayerBlock;
    private final Timer jumpTimer;
    private int yVelocity;
    private boolean upReleased;
    private int jumpLeft;
    private final int maxJump;

    public enum AnimationState {
        WALKR, WALKL, IDLER, IDLEL, FALLR, FALLL, CLIMBL, CLIMBR
    }

    public MovableAnimatedActor() {
        jumpTimer = new Timer(100000000);
        walkRight = null;
        direction = "right";
        walkLeft = null;
        idleRight = null;
        falling = null;
        fleft = null;
        climbingR = null;
        climbingL = null;
        currentAction = null;
        catTouchingLadder = false;
        upReleased = true;
        maxJump = 200;
        isJumping = false;
    }

    public void setTouchingLadder(boolean value) {
        catTouchingLadder = value;
    }

    public static String getDirection() {
        return direction;
    }

    public void act() {
        catTouchingLayerBlock = getTouchingLayerBlock();
        AnimationState newAction = null;
        // Default animation is idling
        if (currentAction == null) {
            newAction = AnimationState.IDLER;
        }

        // Jump logic
        boolean upPressed = Mayflower.isKeyDown(Keyboard.KEY_UP);
        if (!upPressed) {
            upReleased = true;
            isJumping = false;
            jumpLeft = maxJump;
        }
        if (upPressed && !isFalling() && !isJumping && !catTouchingLadder) {
            isJumping = true;
        }
        else if (isJumping && jumpLeft > 0) {
            // Move to the left or right depending on arrow key input
            if (!isBlocked() && Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && getX() + getWidth() < 800)
                setLocation(getX() + 2, getY() - 10);
            else if (!isBlocked() && Mayflower.isKeyDown(Keyboard.KEY_LEFT) && getX() > 0)
                setLocation(getX() - 2, getY() - 10);
            else
            setLocation(getX(), getY() - 10);
            jumpLeft -= 10;

            // Net zero movement when touching island while jumping
            if (isTouching(Island.class))
            {
                setLocation(getX(), getY() + 10);
                jumpLeft = maxJump;
                isJumping = false;
            }
            // Resetting the jump pixel bank
            if (jumpLeft <= 0) {
                isJumping = false;
                jumpLeft = maxJump;
            }
        }
        // Right movement logic
        else if (Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && getX() + getWidth() < 800) {
            newAction = AnimationState.WALKR;
            direction = "right";

            // Net zero movement if blocked
            if (isBlocked()) {
                setLocation(getX() - 2, getY());
            }
            else
                setLocation(getX() + 2, getY());
            }
        // Left movement logic
        else if (Mayflower.isKeyDown(Keyboard.KEY_LEFT) && getX() > 0) {
            newAction = AnimationState.WALKL;
            direction = "left";

            // Net zero movement if blocked
            setLocation(getX() - 2, getY());
            if (isBlocked()) {
                setLocation(getX() + 2, getY());
            }
        }
        // Set right idle animation if facing right
        else if ((direction != null) && !direction.equals("left")) {
            newAction = AnimationState.IDLER;
        }
        else
            newAction = AnimationState.IDLEL;

        // Set falling animation based on direction of fall
        if (isFalling() && direction.equals("left"))
            newAction = AnimationState.FALLL;
        else if (isFalling() && direction.equals("right"))
            newAction = AnimationState.FALLR;

        // Set animation for player based on newAction AnimationState value
        if (newAction != null && newAction != currentAction) {
            if (newAction == AnimationState.IDLER)
                setAnimation(idleRight);
            else if (newAction == AnimationState.WALKR)
                setAnimation(walkRight);
            else if (newAction == AnimationState.WALKL)
                setAnimation(walkLeft);
            else if (newAction == AnimationState.FALLR)
                setAnimation(falling);
            else if (newAction == AnimationState.FALLL)
                setAnimation(fleft);
            else if (newAction == AnimationState.IDLEL)
                setAnimation(idleLeft);
            else if (newAction == AnimationState.CLIMBL)
                setAnimation(climbingL);
            else if (newAction == AnimationState.CLIMBR)
                setAnimation(climbingR);
            currentAction = newAction;
        }

        super.act();
    }

    public void setWalkRightAnimation(Animation ani) {
        walkRight = ani;
    }

    public void setWalkLeftAnimation(Animation ani) {
        walkLeft = ani;
    }

    public void setIdleAnimation(Animation ani) {
        idleRight = ani;
    }

    public void setIdleLeftAni(Animation ani) {
        idleLeft = ani;
    }

    public void setFallingAnimation(Animation ani) {
        falling = ani;
    }

    public void setFallingLeft(Animation ani) {
        fleft = ani;
    }

    public void setClimbingAnimation(Animation ani) {
        climbingR = ani;
    }

    public void setAnimation(Animation a) {
        super.setAnimation(a);
    }
}