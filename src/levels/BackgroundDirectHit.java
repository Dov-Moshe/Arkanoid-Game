// ID: 205694441

package levels;
import biuoop.GUI;
import biuoop.DrawSurface;
import interfaces.Sprite;
import other.Constants;
import java.awt.Color;

/**
 * A class of background for level DirectHit.
 */
public class BackgroundDirectHit implements Sprite {
    // The BackgroundDirectHit's fields
    // a sky blue color
    private Color sky = new Color(135,206,235);
    // numbers for drawing
    private int centerOfCirclesY = 164;
    private int radius1 = 60;
    private int radius2 = 90;
    private int radius3 = 120;
    private int distanceLinesAndBlock = 20;
    private int linesLength = 120;

    /**
     * A constructor method.
     */
    public BackgroundDirectHit() {

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(Constants.BORDER_W, Constants.BORDER_W * 2,
                Constants.WINDOW_W - Constants.BORDER_W * 2, Constants.WINDOW_H);
        d.setColor(sky);
        d.drawLine(Constants.WINDOW_W / 2 - distanceLinesAndBlock - linesLength, centerOfCirclesY,
                Constants.WINDOW_W / 2  - distanceLinesAndBlock, centerOfCirclesY);
        d.drawLine(Constants.WINDOW_W / 2 + distanceLinesAndBlock, centerOfCirclesY,
                Constants.WINDOW_W / 2 + distanceLinesAndBlock + linesLength, centerOfCirclesY);
        d.drawLine(Constants.WINDOW_W / 2, centerOfCirclesY - distanceLinesAndBlock - linesLength,
                Constants.WINDOW_W / 2, centerOfCirclesY - distanceLinesAndBlock);
        d.drawLine(Constants.WINDOW_W / 2, centerOfCirclesY + distanceLinesAndBlock,
                Constants.WINDOW_W / 2, centerOfCirclesY + distanceLinesAndBlock + linesLength);
        d.drawCircle(Constants.WINDOW_W / 2, centerOfCirclesY, radius1);
        d.drawCircle(Constants.WINDOW_W / 2, centerOfCirclesY, radius2);
        d.drawCircle(Constants.WINDOW_W / 2, centerOfCirclesY, radius3);
    }

    @Override
    public void timePassed() {

    }
}
