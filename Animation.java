import mayflower.*;

public class Animation {
    private final MayflowerImage[] frames;
    private int currentFrame;

    // Convert String[] to MayflowerImage[]
    public Animation(String[] arr) {
        frames = new MayflowerImage[arr.length];
        for (int i = 0; i < frames.length; i++)
            frames[i] = new MayflowerImage(arr[i]);
        currentFrame = 0;
    }

    // Keep iterating through image (frames)
    public MayflowerImage getNextFrame() {
        if (currentFrame >= frames.length)
            currentFrame %= frames.length;
        MayflowerImage image = frames[currentFrame];
        currentFrame++;
        return image;
    }

    // Scales all images in the animation
    public void scale(int w, int h) {
        for (int i = 0; i < frames.length; i++)
            frames[i].scale(w, h);
    }

    // Mirrors all images in the animation horizontally
    public void mirrorHorizontally() {
        for (int i = 0; i < frames.length; i++)
            frames[i].mirrorHorizontally();
    }
}