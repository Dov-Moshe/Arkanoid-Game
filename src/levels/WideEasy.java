package levels;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import object.Block;
import other.Constants;
import other.ListOfColors;
import biuoop.GUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of level WideEasy.
 */
public class WideEasy implements LevelInformation {
    // The WideEasy's fields
    private int paddleSpeed = 10;
    private int paddleWidth = 600;
    private int numberOfBalls = 10;
    private int speedOfBalls = 10;
    private double firstAngle = 250;
    private double addedAngle = 15;
    private double blockWidth = 50;
    private double blockHeight = 25;
    private double rowOfBlocks = 250;
    private List<Velocity> velocityList;
    private List<Block> blocksList;

    /**
     * A constructor method.
     */
    public WideEasy() {
        this.blocksList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < numberOfBalls; i++) {
            Velocity velocity= Velocity.fromAngleAndSpeed(firstAngle, speedOfBalls);
            velocityList.add(velocity);
            firstAngle -= addedAngle;
        }
        return velocityList;
    }
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    @Override
    public int paddleWidth() {
        return paddleWidth;
    }
    @Override
    public String levelName() {
        return "Wide Easy";
    }
    @Override
    public Sprite getBackground() {
        return new BackgroundWideEasy();
    }
    @Override
    public List<Block> blocks() {
        List<Color> colors = ListOfColors.basicColorsFirst();
        double x = Constants.BORDER_W;
        // creating blocks by loop
        for (int i = 0; i < 7; i++) {
            // creating two blocks for each color
            Rectangle rect1 = new Rectangle(new Point(x, rowOfBlocks), blockWidth, blockHeight);
            Block b1 = new Block(rect1, colors.get(i % colors.size()), Color.BLACK);
            blocksList.add(b1);
            x += blockWidth;
            Rectangle rect2 = new Rectangle(new Point(x, rowOfBlocks), blockWidth, blockHeight);
            Block b2 = new Block(rect2, colors.get(i % colors.size()), Color.BLACK);
            blocksList.add(b2);
            x += blockWidth;
            // for the middle color there is three blocks
            if(i == 3) {
                Rectangle rect3 = new Rectangle(new Point(x, rowOfBlocks), blockWidth, blockHeight);
                Block b3 = new Block(rect3, colors.get(i % colors.size()), Color.BLACK);
                blocksList.add(b3);
                x += blockWidth;
            }
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocksList.size();
    }
}
