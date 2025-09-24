import mayflower.*;

public class MovableAnimatedActor extends AnimatedActor{

    private Animation walkRight;
    private Animation walkLeft;
    private Animation idleRight;
    private Animation idleLeft;
    private Animation falling;
    private Animation fleft;
    private String currentAction;
    private String direction;
    private boolean isFalling;
    private boolean isJumping;
    private boolean isBlocking;
    public MovableAnimatedActor(){
        walkRight = null;
        direction = "right";
        walkLeft = null;
        idleRight = null;
        falling = null;
        fleft = null;
        currentAction = null;
    }

    public void act(){
        String newAction = null;
        if(currentAction == null)
            newAction = "idleRight";
        if(isTouching(Block.class))
        {
            
        }
        if((Mayflower.isKeyDown(Keyboard.KEY_UP)))
        {
            isJumping = true;
            if(isFalling == false && isJumping == true)
            {
                 for(int i =10 ; i>0; i-=2)
                {
                    if (getY()-i > 0) {
                        setLocation(getX(), getY()-i);
                    }
                }   
            }
                    }
        else if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT)&& getX()+getWidth()<800)
        {
            newAction = "walkRight";
            direction="right";

            if(isBlocked()){
                setLocation(getX()-1, getY());
            }
            else
                setLocation(getX()+1, getY());
        }
        else if(Mayflower.isKeyDown(Keyboard.KEY_LEFT)&& getX()>0)
        {
            newAction = "walkLeft";
            direction="left";
            setLocation(getX()-1, getY());       
            if(isBlocked()){                
                setLocation(getX()+1, getY());
            }
        }
        else if((direction!=null) && !direction.equals("left"))
        {
            newAction="idle";
        } 
        else
            newAction= "idleLeft";

        if(isFalling() && direction.equals("left"))
            newAction="fleft";
        else if(isFalling() && direction.equals("right"))
            newAction="falling";

        if(!(newAction==null) && !newAction.equals(currentAction)){
            if(newAction.equals("idle"))
                setAnimation(idleRight);
            if(newAction.equals("walkRight"))
                setAnimation(walkRight);
            if(newAction.equals("walkLeft"))
                setAnimation(walkLeft);
            if(newAction.equals("falling"))
                setAnimation(falling);
            if(newAction.equals("fleft"))
                setAnimation(fleft);
            if(newAction.equals("idleLeft"))
                setAnimation(idleLeft);
            currentAction = newAction;
        }

        super.act();
    }

    public void setWalkRightAnimation(Animation ani){
        walkRight = ani;
    }

    public void setWalkLeftAnimation(Animation ani){
        walkLeft = ani;
    } 

    public void setIdleAnimation(Animation ani){
        idleRight= ani;
    }

    public void setIdleLeftAni(Animation ani){
        idleLeft = ani;
    }

    public void setFallingAnimation(Animation ani){
        falling = ani;
    }

    public void setFallingLeft(Animation ani){
        fleft = ani;
    }

    public void setAnimation(Animation a){
        super.setAnimation(a);
    }
}
