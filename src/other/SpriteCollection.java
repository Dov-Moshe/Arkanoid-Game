package other;
import interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;

/**
 * Class of SpriteCollection.
 * This class gets object and draws and moves them by calling to there method.
 */
public class SpriteCollection {
    // The SpriteCollection's fields, a collection of objects
    private List<Sprite> sprites;
    /**
     * This is a constructor method.
     * The method creates new list of sprites.
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }
    /**
     * This method adds object to the Sprite's collection.
     * @param s the object.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * This method removes object from the Sprite's collection.
     * @param s the object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**
     * This method calls to methods "timePassed" from the class of the object that contained in the list.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> tempListeners = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : tempListeners) {
            s.timePassed();
        }
    }
    /**
     * This method calls to methods "drawOn" from the class of the object that contained in the list.
     * @param d the DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}