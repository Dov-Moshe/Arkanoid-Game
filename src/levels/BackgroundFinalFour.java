package levels;
import biuoop.GUI;
import biuoop.DrawSurface;
import interfaces.Sprite;
import other.Constants;
import java.awt.Color;

/**
 * A class of background for level FinalFour.
 */
public class BackgroundFinalFour implements Sprite {
    // The BackgroundFinalFour's fields
    // a blue color
    private Color backgroundColor = new Color(23, 136, 208);
    private Color gray1 = new Color(204, 204, 204);
    private Color gray2 = new Color(170, 170, 170);
    private Color gray3 = new Color(187, 187, 187);
    private int distanceBetweenLines = 9;
    private int rectWidth = 103;
    // first cloud
    private int cloud1FirstLineX1 = 103;
    private int cloud1FirstLineX2 = cloud1FirstLineX1 - 23;
    private int cloud1FirstLineY1 = 418;
    private int cloud1FirstLineY2 = Constants.WINDOW_H;
    private int radius1 = 21;
    private int radius2 = 23;
    private int radius3 = 36;
    private int rect1X = 96;
    private int rect1Y = 399;
    private int circle1X = rect1X + 26;
    private int circle2X = rect1X + 36 * 2;
    // second cloud
    private int cloud2FirstLineX1 = 595;
    private int cloud2FirstLineX2 = cloud2FirstLineX1 - 10;
    private int cloud2FirstLineY1 = 548;
    private int cloud2FirstLineY2 = Constants.WINDOW_H;
    private int radius4 = 26;
    private int radius5 = 36;
    private int rect2X = 584;
    private int rect2Y = 506;
    private int circle3X = rect2X + 26;
    private int circle4X = rect2X + 36 * 2;

    /**
     * A constructor method.
     */
    public BackgroundFinalFour() {

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(backgroundColor);
        d.fillRectangle(Constants.BORDER_W, Constants.BORDER_W * 2,
                Constants.WINDOW_W - Constants.BORDER_W * 2, Constants.WINDOW_H);
        // first cloud
        d.setColor(Color.WHITE);
        int x1 = cloud1FirstLineX1;
        int x2 = cloud1FirstLineX2;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, cloud1FirstLineY1, x2, cloud1FirstLineY2);
            x1 += distanceBetweenLines;
            x2 += distanceBetweenLines;
        }
        d.setColor(gray1);
        d.fillCircle(rect1X , rect1Y + radius1, radius1);
        d.fillCircle(rect1X + rectWidth, rect1Y + radius1, radius1);
        d.fillRectangle(rect1X, rect1Y, rectWidth, radius1 * 2);
        d.setColor(gray2);
        d.fillCircle(circle1X, rect1Y, radius2);
        d.setColor(gray3);
        d.fillCircle(circle2X, rect1Y, radius3);
        // second cloud
        d.setColor(Color.WHITE);
        int x3 = cloud2FirstLineX1;
        int x4 = cloud2FirstLineX2;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x3, cloud2FirstLineY1, x4, cloud2FirstLineY2);
            x3 += distanceBetweenLines;
            x4 += distanceBetweenLines;
        }
        d.setColor(gray1);
        d.fillCircle(rect2X , rect2Y + radius1, radius1);
        d.fillCircle(rect2X + rectWidth, rect2Y + radius1, radius1);
        d.fillRectangle(rect2X, rect2Y, rectWidth, radius1 * 2);
        d.setColor(gray3);
        d.fillCircle(circle3X, rect2Y, radius4);
        d.setColor(gray2);
        d.fillCircle(circle4X, rect2Y, radius5);
    }

    @Override
    public void timePassed() {

    }
}
