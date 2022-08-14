// ID: 205694441

package interfaces;
import biuoop.DrawSurface;

/**
 * This is interface of Sprite.
 */
public interface Sprite {
    /**
     * This is signature of method that draws object.
     * @param d the DrawSurface.
     */
    void drawOn(DrawSurface d);
    /**
     * This is signature of method that changes the location or the looking of object.
     */
    void timePassed();
}