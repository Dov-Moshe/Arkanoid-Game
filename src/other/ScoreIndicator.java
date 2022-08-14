// ID: 205694441

package other;
import interfaces.Sprite;
import animation.GameLevel;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class is in charge of drawing the 'score display' on top of the window.
 */
public class ScoreIndicator implements Sprite {
    // The ScoreIndicator's field
    private GUI gui;
    private Counter scoreCounter;
    private String nameOfLevel;
    private final int sizeFont = 14;
    private final int locationOfTextY = 7;
    private final int locationOfTextScoreX = Constants.WINDOW_W / 2 - 200;
    private final int locationOfTextNaneX = Constants.WINDOW_W / 2 + 100;

    /**
     * A constructor method.
     * @param gui the width of the display.
     * @param scoreCounter a counter of the score.
     */
    public ScoreIndicator(GUI gui, Counter scoreCounter , String nameOfLevel) {
        this.gui = gui;
        this.scoreCounter = scoreCounter;
        this.nameOfLevel = nameOfLevel;
    }

    /**
     * The method drawing the 'score display' on top of the window.
     * @param d the DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // drawing a score display
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, Constants.WINDOW_W, Constants.BORDER_W);
        d.setColor(Color.BLACK);
        // drawing a black outline
        d.drawRectangle(0, 0, Constants.WINDOW_W, Constants.BORDER_W);
        // writing the score
        d.drawText(locationOfTextScoreX, Constants.BORDER_W - locationOfTextY,
                "Score: " + scoreCounter.getValue(), sizeFont);
        // writing the name of the game.
        d.drawText(locationOfTextNaneX, Constants.BORDER_W - locationOfTextY,
                "Level Name: " + this.nameOfLevel, sizeFont);
    }

    /**
     * This method not realized for now.
     */
    @Override
    public void timePassed() {

    }
}
