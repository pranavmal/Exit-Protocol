import mayflower.*;

public class MovableAnimatedActor extends AnimatedActor {

    private Animation walkRight;
    private Animation walkLeft;
    private Animation idleRight;
    private Animation idleLeft;
    private Animation falling;
    private Animation fleft;
    private String currentAction;
    private static String direction;
    private boolean isJumping;
    private boolean isBlocking;
    private boolean catTouchingLadder;
    private boolean catTouchingLayerBlock;
    private final Timer jumpTimer;
    private int yVelocity;
    private boolean upReleased;
    private int jumpLeft;
    private int maxJump;

    public MovableAnimatedActor() {
        jumpTimer = new Timer(100000000);
        walkRight = null;
        direction = "right";
        walkLeft = null;
        idleRight = null;
        falling = null;
        fleft = null;
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
        String newAction = null;
        if (currentAction == null) {
            newAction = "idleRight";
        }

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
            if (!isBlocked() && Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && getX() + getWidth() < 800)
                setLocation(getX() + 2, getY() - 10);
            else if (!isBlocked() && Mayflower.isKeyDown(Keyboard.KEY_LEFT) && getX() > 0)
                setLocation(getX() - 2, getY() - 10);
            else
            setLocation(getX(), getY() - 10);
            jumpLeft -= 10;
            if (isTouching(Island.class))
            {
                setLocation(getX(), getY() + 10);
                jumpLeft = maxJump;
                isJumping = false;
            }
            if (jumpLeft <= 0) {
                isJumping = false;
                jumpLeft = maxJump;
            }
        }
        else if (Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && getX() + getWidth() < 800) {
            newAction = "walkRight";
            direction = "right";

            if (isBlocked()) {
                setLocation(getX() - 2, getY());
            } else
                setLocation(getX() + 2, getY());
        } else if (Mayflower.isKeyDown(Keyboard.KEY_LEFT) && getX() > 0) {
            newAction = "walkLeft";
            direction = "left";
            setLocation(getX() - 2, getY());
            if (isBlocked()) {
                setLocation(getX() + 2, getY());
            }
        } else if ((direction != null) && !direction.equals("left")) {
            newAction = "idle";
        } else
            newAction = "idleLeft";

        if (isFalling() && direction.equals("left"))
            newAction = "fleft";
        else if (isFalling() && direction.equals("right"))
            newAction = "falling";

        if (!(newAction == null) && !newAction.equals(currentAction)) {
            if (newAction.equals("idle"))
                setAnimation(idleRight);
            if (newAction.equals("walkRight"))
                setAnimation(walkRight);
            if (newAction.equals("walkLeft"))
                setAnimation(walkLeft);
            if (newAction.equals("falling"))
                setAnimation(falling);
            if (newAction.equals("fleft"))
                setAnimation(fleft);
            if (newAction.equals("idleLeft"))
                setAnimation(idleLeft);
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

    public void setAnimation(Animation a) {
        super.setAnimation(a);
    }
}
