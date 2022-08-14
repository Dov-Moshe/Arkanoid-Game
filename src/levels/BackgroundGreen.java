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
public class BackgroundGreen implements Sprite {
    // The BackgroundDirectHit's fields
    // gray color
    private Color gray1 = new Color(62,58,57);
    private Color gray2 = new Color(78,74,73);
    // yellow color
    private Color yellow = new Color(216,172,102);
    // orange color
    private Color orange = new Color(246,77,54);
    // first rect
    private int rect1X = 65;
    private int rect1Y = 450;
    private int rect1Width = 102;
    private int rect1Height = 150;
    private int firstWindowX = rect1X + 10;
    private int firstWindowY = rect1Y + 10;
    private int windowWidth = 10;
    private int windowHeight = 25;
    private int distanceBetweenWindows = 8;
    private int numberOfColumns = 5;
    private int numberOfRows = 5;
    // second rect
    private int rect2Width = 30;
    private int rect2Height = 57;
    // third rect
    private int rect3Width = 10;
    private int rect3Height = 193;
    // circles
    private int radius1 = 12;
    private int radius2 = 8;
    private int radius3 = 4;


    /**
     * A constructor method.
     */
    public BackgroundGreen() {

    }

    @Override
    public void drawOn(DrawSurface d) {
        // building
        d.setColor(Color.GREEN.darker().darker());
        d.fillRectangle(Constants.BORDER_W, Constants.BORDER_W * 2,
                Constants.WINDOW_W - Constants.BORDER_W * 2, Constants.WINDOW_H);
        d.setColor(Color.DARK_GRAY.darker());
        d.fillRectangle(rect1X, rect1Y, rect1Width, rect1Height);
        d.setColor(Color.WHITE);
        int x = firstWindowX;
        int y = firstWindowY;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                d.fillRectangle(x, y, windowWidth, windowHeight);
                x = x + windowWidth + distanceBetweenWindows;
            }
            x = firstWindowX;
            y = y + windowHeight + distanceBetweenWindows;
        }
        d.setColor(gray1);
        d.fillRectangle((rect1X * 2 + rect1Width) / 2 - rect2Width / 2,
                rect1Y - rect2Height, rect2Width, rect2Height);
        d.setColor(gray2);
        d.fillRectangle((rect1X * 2 + rect1Width) / 2 - rect3Width / 2,
                rect1Y - rect2Height - rect3Height, rect3Width, rect3Height);
        // lamp
        d.setColor(yellow);
        d.fillCircle((rect1X * 2 + rect1Width) / 2 , rect1Y - rect2Height - rect3Height, radius1);
        d.setColor(orange);
        d.fillCircle((rect1X * 2 + rect1Width) / 2 , rect1Y - rect2Height - rect3Height, radius2);
        d.setColor(Color.WHITE);
        d.fillCircle((rect1X * 2 + rect1Width) / 2 , rect1Y - rect2Height - rect3Height, radius3);
    }

    @Override
    public void timePassed() {

    }
}
