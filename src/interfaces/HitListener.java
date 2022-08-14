// ID: 205694441

package interfaces;
import object.Ball;
import object.Block;

/**
 * A interface of listener to hitting.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block
     * @param hitter the ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}