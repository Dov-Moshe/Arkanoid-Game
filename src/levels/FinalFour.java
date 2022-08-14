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
 * A class of level FinalFour.
 */
public class FinalFour implements LevelInformation {
    // The FinalFour's fields
    private final int paddleSpeed = 10;
    private final int paddleWidth = 100;
    private final int numberOfBalls = 3;
    private int speedOfBalls = 5;
    private double firstAngle = 225;
    private double addedAngle = 41;
    private double blockWidth = 50;
    private double blockHeight = 25;
    private double firstRowOfBlocks = 100;
    private java.util.List<Velocity> velocityList;
    private java.util.List<Block> blocksList;

    /**
     * A constructor method.
     */
    public FinalFour() {
        this.blocksList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public java.util.List<Velocity> initialBallVelocities() {
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
        return "Final Four";
    }
    @Override
    public Sprite getBackground() {
        return new BackgroundFinalFour();
    }
    @Override
    public java.util.List<Block> blocks() {
        List<Color> colors = ListOfColors.basicColorsThird();
        double x = Constants.BORDER_W;
        double y = firstRowOfBlocks;
        // creating blocks by loop
        for (int i = 0; i < 7; i++) {
            for (int j  = 0; j < 15; j++) {
                Rectangle r = new Rectangle(new Point(x, y), blockWidth, blockHeight);
                Block b = new Block(r, colors.get(i % colors.size()), Color.BLACK);
                blocksList.add(b);
                x += blockWidth;
            }
            y += blockHeight;
            x = Constants.BORDER_W;
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocksList.size();
    }
}