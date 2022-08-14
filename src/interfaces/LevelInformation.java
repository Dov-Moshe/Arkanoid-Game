package interfaces;
import geometry.Velocity;
import object.Block;
import java.util.List;

/**
 * A interface of the information about a level.
 */
public interface LevelInformation {
    /**
     * This is a signature of method that returns the number of balls.
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * This is a signature of method that initializes and returns a list of velocity of the balls.
     * @return list of velocity
     */
    List<Velocity> initialBallVelocities();

    /**
     * This is a signature of method that returns the paddle's speed.
     * @return paddle's speed.
     */
    int paddleSpeed();

    /**
     * This is a signature of method that returns the paddle's width.
     * @return paddle's width.
     */
    int paddleWidth();

    /**
     * This is a signature of method that returns a string with the name of the level.
     * @return
     */
    String levelName();

    /**
     * This is a signature of method that returns a Sprite with the background of the level.
     * @return Sprite of background.
     */
    Sprite getBackground();

    /**
     * This is a signature of method that creates and returns a list with the blocks of the level.
     * Each block contains its size, color and location.
     * @return list with blocks
     */
    List<Block> blocks();

    /**
     * This is a signature of method that returns the number of blocks that should be removed.
     * @return number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
