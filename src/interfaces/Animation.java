// ID: 205694441

package interfaces;
import biuoop.DrawSurface;

/**
 * A interface of a animation.
 */
public interface Animation {
    /**
     * This is a signature of method that runs one frame of the animation.
     * @param d the DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This is a signature of method that returns boolean value if the animation should stop.
     * @return a boolean value.
     */
    boolean shouldStop();
}