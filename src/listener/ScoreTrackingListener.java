// ID: 205694441

package listener;
import other.Counter;
import interfaces.HitListener;
import object.Ball;
import object.Block;

/**
 * This class is in charge of updating the score.
 */
public class ScoreTrackingListener implements HitListener {
    // The ScoreTrackingListener's field
    private Counter currentScore;
    // Scoring for one hitting
    static final int SCORE_ONE_HITTING = 5;

    /**
     * A constructor method.
     * @param scoreCounter the counter that counts the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * The method increases the score.
     * @param beingHit the block
     * @param hitter the ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(SCORE_ONE_HITTING);
    }
}