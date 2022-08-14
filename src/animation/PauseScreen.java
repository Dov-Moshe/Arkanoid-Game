// ID: 205694441

package animation;
import interfaces.Animation;
import biuoop.DrawSurface;

/**
 * A class of animation that running when the screen is paused.
 */
public class PauseScreen implements Animation {
    private int fontSize1 = 70;
    private int fontSize2 = 30;

    /**
     * A constructor method.
     */
    public PauseScreen() {

    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getHeight() / 2 - fontSize2, d.getHeight() / 2 , "paused", this.fontSize1);
        d.drawText(d.getHeight() / 2 - fontSize1, d.getHeight() / 2  + fontSize1,
                "press space to continue", this.fontSize2);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}