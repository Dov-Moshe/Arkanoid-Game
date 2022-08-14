// ID: 205694441

package animation;
import interfaces.Animation;
import other.SpriteCollection;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private long startTime;
    private long usedTime;
    private int fontSize;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        this.fontSize = 100;
    }

    private Boolean countTime(DrawSurface d) {
        d.setColor(Color.RED);
        d.fillCircle(d.getWidth() / 2, 150, 70);
        if (countFrom > 0) {
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2 - 25,
                    185, String.valueOf(countFrom), fontSize);
        }
        if (countFrom == 0) {
            return true;
        }
        if (countFewSecond()) {
            countFrom --;
            startTime = System.currentTimeMillis();
        }
        return false;
    }

    private Boolean countFewSecond() {
        usedTime = System.currentTimeMillis() - startTime;
        if (usedTime >= 1000 * this.numOfSeconds) {
            return true;
        }
        return false;
    }

    public void doOneFrame(DrawSurface d) {
        // drawing all the objects
        this.gameScreen.drawAllOn(d);
        if (countTime(d)) {
            this.stop = true;
        }
    }

    public boolean shouldStop() {
        return this.stop;
    }
}
