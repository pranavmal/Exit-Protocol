import mayflower.*;

public class GravityActor extends Actor
{
    private final int step;
    private boolean axionTouchingLayerBlock;

    // Set gravity and ladder climb speed rate
    public GravityActor()
    {
        step = 2;
    }

    // Set constructor value if Axion is touching the ground
    public void setTouchingLayerBlock(boolean value) {
        axionTouchingLayerBlock = value;
    }

    // Get constructor the value if Axion is touching the ground
    public boolean getTouchingLayerBlock() {
        return axionTouchingLayerBlock;
    }

    // Increased y position of player until player is blocked when there is no gravity acting on them
    public void act()
    {
        setLocation(getX(),getY()+step);
        if(isBlocked())
        {
            setLocation(getX(),getY()-step);
        }
    }

    public int getStep() {
        return step;
    }

    // Actor is blocked if they are touching a block, layer block, or island
    public boolean isBlocked()
    {
        return isTouching(Block.class) || isTouching(LayerBlock.class) || isTouching(Island.class);
    }

    // Actor is falling if they are not touching a block, layer block, or island on the below step
    public boolean isFalling()
    {
        boolean ret;
        setLocation(getX(), getY()+step);
        ret = isTouching(Block.class) || isTouching(LayerBlock.class) || isTouching(Island.class);
        setLocation(getX(), getY() - step);
        return !ret;
    }
}
