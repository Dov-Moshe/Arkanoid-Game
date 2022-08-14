// ID: 205694441

package listener;
import animation.GameLevel;
import other.Counter;
import interfaces.HitListener;
import object.Ball;
import object.Block;

/**
 * A class that in charge of removing balls from the game,
 * as well as keeping count of the number of balls that remain.
 * The class implements the interface 'HitListener'.
 */
public class BallRemover implements HitListener {
    // The BallRemover's field.
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * A constructor method.
     * @param game the game.
     * @param removedBalls the counter how mach balls remain.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * The method removes the ball from the game and updates the counter.
     * @param beingHit the block
     * @param hitter the ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}