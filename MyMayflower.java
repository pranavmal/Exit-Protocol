import mayflower.*;


public class MyMayflower extends Mayflower {

    // Window settings
    public MyMayflower() {
        //Create a window with 800x600 resolution
        super("Exit Protocol", 800, 600);
    }

    // Set starting world screen
    public void init() {
        Mayflower.setFullScreen(false);
        World w = new StartScreen();
        changeWorld(w);
    }

    public static void changeWorld(World w) {
        Mayflower.setWorld(w);
    }
}
