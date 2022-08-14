// ID: 205694441

package animation;
import interfaces.Animation;
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * A class for running animation.
 */
public class AnimationRunner {
    private int framesPerSecond;
    biuoop.Sleeper sleeper;

    /**
     * A constructor method.
     * @param framesPerSecond number of frames per second.
     */
    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * This method running the animation.
     * @param animation the animation.
     * @param gui the window.
     */
    public void run(Animation animation, GUI gui) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            // drawing the animation.
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
    }
}
