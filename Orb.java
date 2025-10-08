import mayflower.*;

public class Orb extends Actor
{
    public enum Direction {
        LEFT, RIGHT
    }
    private Direction direction;
    public Orb(String dir) {
        setImage("img/axion/orb.png");
        if (dir.equals("left")) {
            direction = Direction.LEFT;
        }
        else {
            direction = Direction.RIGHT;
        }
    }
    
    public boolean onScreen() {
        return getX() < 800;
    }

    public Orb setDirection(String dir) {
        if (dir.equals("left")) {
            direction = Direction.LEFT;
        }
        else {
            direction = Direction.RIGHT;
        }
        return this;
    }

    public void act()
    {
        for(int i = 0; i < 5; i++) {
            if (direction == Direction.LEFT) {
                setLocation(getX() - 1, getY());
            }
            else {
                setLocation(getX() + 1, getY());
            }
        }
    }
}
