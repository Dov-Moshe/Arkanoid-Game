// ID: 205694441

package object;
import interfaces.Sprite;
import interfaces.Collidable;
import interfaces.HitNotifier;
import interfaces.HitListener;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import animation.GameLevel;
import java.awt.Color;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of a objects.Block.
 * The class support Block operations - gets its rectangle,
 * change the velocity of something that hit with, draws the block and add it to te game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // The Block's fields
    private Rectangle rect;
    private Color fillColor;
    private Color scopeColor;

    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * This is a constructor method.
     * The method set the rectangle and the color of the block.
     * @param rect the rectangle of the block.
     * @param fillColor the color of filling.
     * @param scopeColor the color of the scope.
     */
    public Block(Rectangle rect, Color fillColor, Color scopeColor) {
        this.rect = new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
        this.fillColor = fillColor;
        this.scopeColor = scopeColor;
    }
    /**
     * This is a default constructor method.
     * The method set the rectangle (by upper-left point, width and height) and the color of the block.
     * @param upperLeft the upper-left point of the block.
     * @param width the width of the block.
     * @param height the height of the block.
     * @param fillColor the color of filling.
     * @param scopeColor the color of the scope.
     */
    public Block(Point upperLeft, double width, double height, Color fillColor, Color scopeColor) {
        this(new Rectangle(upperLeft, width, height), fillColor, scopeColor);
    }
    /**
     * This method return the "collision shape" of the block.
     * It's mean, the rectangle of the block.
     * @return rect the rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * This method notifies to listener that hitting happened.
     * @param hitter the ball that is the hitter.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * This method changes the direction of object that hits the black.
     * The direction change by updating the velocity of this object.
     * @param collisionPoint the collision point.
     * @param currentVelocity the velocity that need to be updating.
     * @param hitter the ball.
     * @return the updated velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //
        double upperLeftX = rect.getUpperLeft().getX();
        double upperRightX = rect.getUppreRight().getX();
        double upperLeftY = rect.getUpperLeft().getY();
        double lowerLeft = rect.getLowerLeft().getY();
        this.notifyHit(hitter);

        // checking where the hit happening
        if (collisionPoint.getY() <= rect.getUpperLeft().getY()
                || collisionPoint.getY() >= rect.getLowerLeft().getY()) {
            return new Velocity(currentVelocity.getX(), -currentVelocity.getY());
        } else {
            return new Velocity(-currentVelocity.getX(), currentVelocity.getY());
        }
    }
    /**
     * This method draws the the black.
     * @param d the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        // drawing a block
        d.setColor(this.fillColor);
        d.fillRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        // drawing a black outline
        d.setColor(this.scopeColor);
        d.drawRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * This method not realized for now.
     */
    public void timePassed() {

    }
    /**
     * This method adds the block to the game.
     * The method gets the game and adds the block to its Collidable and Sprite.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * This method removes the block from the game.
     * The method gets the game and removes the block from its Collidable and Sprite.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
