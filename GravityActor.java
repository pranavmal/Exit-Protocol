import mayflower.*;

public class GravityActor extends Actor
{
    private int step;
    private boolean catTouchingLayerBlock;
    public GravityActor()
    {
        step = 2;
    }

    public void setTouchingLayerBlock(boolean value) {
        catTouchingLayerBlock = value;
    }

    public boolean getTouchingLayerBlock() {
        return catTouchingLayerBlock;
    }

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

    public boolean isBlocked()
    {
        if(isTouching(Block.class) || isTouching(LayerBlock.class) || isTouching(Island.class))
        {
            return true;
        }
        return false;
    }

    public boolean isFalling()
    {
        boolean ret;
        setLocation(getX(), getY()+step);
        ret = isTouching(Block.class) || isTouching(LayerBlock.class) || isTouching(Island.class);
        setLocation(getX(), getY() - step);
        return !ret;
    }
}
