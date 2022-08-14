package levels;
import biuoop.GUI;
import biuoop.DrawSurface;
import interfaces.Sprite;
import other.Constants;
import java.awt.Color;

/**
 * A class of background for level WideEasy.
 */
public class BackgroundWideEasy implements Sprite {
    // The BackgroundWideEasy's fields
    // few yellow's color
    private Color yellow1 = new Color(255,250,205);
    private Color yellow2 = new Color(252,247,94);
    private Color yellow3 = new Color(255,255,49);
    // numbers for drawing
    private int centerOfCirclesX = 151;
    private int centerOfCirclesY = 147;
    private int radius1 = 60;
    private int radius2 = 50;
    private int radius3 = 40;
    private int numberOfLines = 705;
    private int endlinesY = 250;
    private int distanceBetweenLines = 7;

    /**
     * A constructor method.
     */
    public BackgroundWideEasy() {

    }

    @Override
    public void drawOn(DrawSurface d) {
        // drawing a sun
        d.setColor(Color.WHITE);
        d.fillRectangle(Constants.BORDER_W, Constants.BORDER_W * 2,
                Constants.WINDOW_W - Constants.BORDER_W * 2, Constants.WINDOW_H);
        d.setColor(yellow1);
        int i = Constants.BORDER_W;
        while (i <= numberOfLines) {
            d.drawLine(centerOfCirclesX, centerOfCirclesY, i, endlinesY);
            i += distanceBetweenLines;
        }
        d.fillCircle(centerOfCirclesX, centerOfCirclesY, radius1);
        d.setColor(yellow2);
        d.fillCircle(centerOfCirclesX, centerOfCirclesY, radius2);
        d.setColor(yellow3);
        d.fillCircle(centerOfCirclesX, centerOfCirclesY, radius3);
    }

    @Override
    public void timePassed() {

    }
}
