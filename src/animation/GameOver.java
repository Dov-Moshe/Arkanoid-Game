// ID: 205694441

package animation;
import interfaces.Sprite;
import other.Constants;
import biuoop.DrawSurface;
import java.awt.Color;

public class GameOver implements Sprite {
    private int scoreFinal;
    public GameOver(int scoreFinal) {
        this.scoreFinal = scoreFinal;
    }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0,
                Constants.WINDOW_W, Constants.WINDOW_H);
        d.setColor(Color.RED);
        d.drawText(d.getWidth() / 2 - 250, d.getHeight() / 2, "Game Over.", 100);
        d.drawText(d.getWidth() / 2 - 200, d.getHeight() / 2 + 60, "Your score is " + this.scoreFinal, 50);
    }
    public void timePassed() {

    }
}
