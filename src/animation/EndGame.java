// ID: 205694441

package animation;
import interfaces.Animation;
import biuoop.GUI;
import biuoop.DrawSurface;
import interfaces.Sprite;
import object.Ball;
import java.util.List;

/**
 * A class of animation that running when the game is done.
 */
public class EndGame implements Animation {
    // The EndGame's fields
    private AnimationRunner animationRunner;
    private Boolean isWin;
    private Sprite win;
    private Sprite gameOver;

    /**
     * A constructor method.
     * @param animationRunner the AnimationRunner.
     * @param scoreFinal the final score.
     * @param isWin a boolean value if it is a winning or not.
     */
    public EndGame(AnimationRunner animationRunner, int scoreFinal, Boolean isWin) {
        this.animationRunner = animationRunner;
        this.isWin = isWin;
        win = new Win(scoreFinal);
        gameOver = new GameOver(scoreFinal);
    }

    /**
     * This method runs the animation.
     * @param animation the AnimationRunner.
     * @param gui the window.
     */
    public void run(Animation animation, GUI gui) {
        this.animationRunner.run(animation, gui);
        gui.close();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (isWin) {
            // if it is a win then drawing the sprite win.
            win.drawOn(d);
        } else {
            // if it is a loss then drawing the sprite gameOver.
            gameOver.drawOn(d);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }


}
