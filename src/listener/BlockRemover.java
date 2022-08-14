package listener;
import animation.GameLevel;
import other.Counter;
import interfaces.HitListener;
import object.Ball;
import object.Block;

/**
 * A class that in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 * The class implements the interface 'HitListener'.
 */
public class BlockRemover implements HitListener {
    // The BlockRemover's field.
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * A constructor method.
     * @param game the game.
     * @param removedBlocks the counter how mach blocks remain.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * The method removes the block from the game, updates the counter, and removes the black form the listener.
     * @param beingHit the block
     * @param hitter the ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}