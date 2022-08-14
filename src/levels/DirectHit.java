// ID: 205694441

package levels;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import object.Block;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of level DirectHit.
 */
public class DirectHit implements LevelInformation {
    // The DirectHit's fields
    // a red color
    private Color red = new Color(223, 2, 32);
    private final int paddleSpeed = 10;
    private final int paddleWidth = 100;
    private final int numberOfBalls = 1;
    private int speedOfBalls = 5;
    private double Angle = 180;
    private double blockWidthAndHeight = 29;
    private double blockLocationX = 386;
    private double blockLocationY = 150;
    private List<Velocity> velocityList;
    private List<Block> blocksList;

    /**
     * A constructor method.
     */
    public DirectHit() {
        this.blocksList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity velocity1= Velocity.fromAngleAndSpeed(Angle, speedOfBalls);
        velocityList.add(velocity1);
        return velocityList;
    }

    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    public int paddleWidth() {
        return paddleWidth;
    }
    @Override
    public String levelName() {
        return "Direct Hit";
    }
    @Override
    public Sprite getBackground() {
        return new BackgroundDirectHit();
    }
    @Override
    public List<Block> blocks() {
        Rectangle rect = new Rectangle(new Point(blockLocationX, blockLocationY),
                blockWidthAndHeight, blockWidthAndHeight);
        Block oneBlock = new Block(rect, red, Color.BLACK);
        blocksList.add(oneBlock);
        return blocksList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blocksList.size();
    }
}
