// ID: 205694441

package animation;
import biuoop.DrawSurface;
import interfaces.Sprite;
import other.Constants;
import java.awt.Color;

/**
 *
 */
public class Win implements Sprite {
    // The Win's fields
    private int scoreFinal;
    private int fontSize1;
    private int fontSize2;

    public Win(int scoreFinal) {
        this.scoreFinal = scoreFinal;
        this.fontSize1 = 100;
        this.fontSize2 = 50;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0,
                Constants.WINDOW_W, Constants.WINDOW_H);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2 - fontSize1 * 2, d.getHeight() / 2, "You Win!", fontSize1);
        d.drawText(d.getWidth() / 2 - fontSize1 * 2,
                d.getHeight() / 2 + 60, "Your score is " + this.scoreFinal, fontSize2);
    }

    @Override
    public void timePassed() {

    }
}
