package levels;

import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import object.Block;
import other.Constants;
import other.ListOfColors;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * A class of level Green.
 */
public class Green implements LevelInformation {
    // The Green's fields
    private final int paddleSpeed = 15;
    private final int paddleWidth = 100;
    private final int numberOfBalls = 2;
    private int speedOfBalls = 7;
    private double firstAngle = 250;
    private double addedAngle = 100;
    private double blockWidth = 50;
    private double blockHeight = 25;
    private double firstRowOfBlocks = 170;
    private java.util.List<Velocity> velocityList;
    private java.util.List<Block> blocksList;

    /**
     * A constructor method.
     */
    public Green() {
        this.blocksList = new ArrayList<Block>();
        this.velocityList = new ArrayList<Velocity>();
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        double angle = firstAngle;
        for (int i = 0; i < numberOfBalls; i++) {
            Velocity velocity= Velocity.fromAngleAndSpeed(angle, speedOfBalls);
            velocityList.add(velocity);
            angle -= addedAngle;
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
        return "Green";
    }
    @Override
    public Sprite getBackground() {
        return new BackgroundGreen();
    }
    @Override
    public java.util.List<Block> blocks() {
        List<Color> colors = ListOfColors.basicColorsSecond();
        double x = Constants.WINDOW_W - Constants.BORDER_W - blockWidth;
        double y = firstRowOfBlocks;
        // creating blocks by loop
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 10; j++) {
            geometry.Rectangle rect1 = new geometry.Rectangle(new geometry.Point(x, y), blockWidth, blockHeight);
            Block b1 = new Block(rect1, colors.get(i % 5), Color.BLACK);
            blocksList.add(b1);
            x -= blockWidth;
            }
            y += blockHeight;
            x = Constants.WINDOW_W - Constants.BORDER_W - blockWidth;
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocksList.size();
    }
}
