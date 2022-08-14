// ID: 205694441

package animation;
import geometry.Velocity;
import other.Counter;
import object.Ball;
import object.Block;
import object.Paddle;
import interfaces.Sprite;
import interfaces.Collidable;
import interfaces.Animation;
import interfaces.LevelInformation;
import other.SpriteCollection;
import other.GameEnvironment;
import other.ScoreIndicator;
import other.Constants;
import listener.BallRemover;
import listener.BlockRemover;
import listener.ScoreTrackingListener;
import geometry.Point;
import geometry.Rectangle;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of a Game.
 * This class creates game, initializes and runs it.
 */
public class GameLevel implements Animation {
    // The GameLevel's fields
    LevelInformation levelInformation;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;
    // creating list of blocks to add them the listener
    private List<Block> blocks;
    private GUI gui;
    // fields for class 'AnimationRunner'
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    // a final variables
    private final int paddleHeight = 20;

    /**
     * This is a constructor method.
     * The method set the SpriteCollection and the GameEnvironment.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui,  KeyboardSensor keyboard,
                     AnimationRunner runner, Counter scoreCounter) {
        this.levelInformation = levelInformation;
        this.gui = gui;
        this.keyboard = keyboard;
        this.runner = runner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.scoreCounter = scoreCounter;
    }

    /**
     * This method adds object to Collidable that in GameEnvironment.
     * @param c the object that is added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * This method adds object to Sprite that in SpriteCollection.
     * @param s the object that is added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * This method removes object from Collidable that in GameEnvironment.
     * @param c the object that is added.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * This method removes object from Sprite that in SpriteCollection.
     * @param s the object that is added.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This method initializes the game.
     * The method creating and adding blocks, balls, borders and paddle to the game.
     * by calling to methods of every type of object.
     */
    public void initialize() {
        // creating background
        addSprite(levelInformation.getBackground());
        // creating border and adding it to the game
        createBorder();
        // creating blocks and adding them to the game
        blocks = levelInformation.blocks();
        for(Block b : blocks) {
            b.addToGame(this);
        }
        // creating paddle and adding it to the game
        createPaddle();
        // adding listener
        addHitListener();
        // adding the display of the score
        Sprite scoreIndicator = new ScoreIndicator(this.gui, this.scoreCounter, levelInformation.levelName());
        addSprite(scoreIndicator);
    }

    /**
     * This method return a boolean value if the running should stop.
     * @return boolean value.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * This method drawing one frame of the animation.
     * The method support in option to pause the game.
     * @param d the DrawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        // drawing all the objects
        this.sprites.drawAllOn(d);
        // moving the objects
        this.sprites.notifyAllTimePassed();
        // if no blocks or balls remained, then the running should stop
        if (this.blocksCounter.getValue() == 0 || this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        // option to pause the game
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                            KeyboardSensor.SPACE_KEY, new PauseScreen()), this.gui);
        }
    }

    /**
     * This method running the level.
     */
    public void run() {
        // creating balls, adding them to the game and getting list of them
        List<Ball> balls = createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(1, 3, sprites), this.gui);
        this.running = true;
        // calling runner to run the animation.
        this.runner.run((Animation) this, this.gui);
    }

    /**
     * The method is in charge of adding listener.
     */
    private void addHitListener() {
        // for 'HitListener' of the blocks
        blocksCounter.increase(blocks.size());
        BlockRemover blockRemover = new BlockRemover(this, blocksCounter);
        // for 'HitListener' of the score
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        // adding the listeners to the blocks
        for (Block b : this.blocks) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
        }
        // death region
        Block deathRegion = new Block(new Point(0, Constants.WINDOW_H),
                Constants.WINDOW_W, Constants.BORDER_W, Color.LIGHT_GRAY , Color.LIGHT_GRAY);
        // adding the 'deathRegion' as collidable
        addCollidable(deathRegion);
        // for 'HitListener' of the balls
        ballsCounter.increase(levelInformation.numberOfBalls());
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        // adding the 'deathRegion' as listener to the balls
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * This method makes list of few colors, basic colors.
     * @return list of colors.
     */
    private List<Color> createColors() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.GREEN);
        return colors;
    }

    /**
     * This method creates balls.
     * The location of all the balls start from the lower of the window.
     * The method makes sure that every ball will moves to different direction.
     * @return list of balls.
     */
    private List<Ball> createBallsOnTopOfPaddle() {
        List<Ball> balls = new ArrayList<>();
        List<Velocity> velocities = levelInformation.initialBallVelocities();
        // creating the balls
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(Constants.WINDOW_W / 2,
                    Constants.WINDOW_H - 50), Constants.BALL_SIZE, Color.WHITE);
            ball.setVelocity(velocities.get(i));
            // adding the ball to the list
            balls.add(ball);
            // adding the ball to the game
            ball.addToGame(this);
            // adding the game environment to the ball
            ball.setGameEnvironment(this.environment);
        }
        return balls;
    }

    /**
     * This method creates paddle.
     */
    private void createPaddle() {
        // creating the paddle and adding it to the game
        double x = Constants.WINDOW_W / 2 -  levelInformation.paddleWidth() / 2;
        double y = Constants.WINDOW_H - paddleHeight * 2;
        Rectangle rectangle = new Rectangle(new Point(x, y), levelInformation.paddleWidth(), paddleHeight);
        Paddle paddle = new Paddle(gui, rectangle, Color.ORANGE, levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * This method creates border.
     */
    private void createBorder() {
        // creating the border
        Block block1 = new Block(new Point(0, 0), Constants.BORDER_W, Constants.WINDOW_H,
                Color.LIGHT_GRAY , Color.LIGHT_GRAY);
        Block block2 = new Block(new Point(0, Constants.BORDER_W), Constants.WINDOW_W, Constants.BORDER_W,
                Color.LIGHT_GRAY , Color.LIGHT_GRAY);
        Block block3 = new Block(new Point(Constants.WINDOW_W - Constants.BORDER_W, 0), Constants.BORDER_W,
                Constants.WINDOW_H, Color.LIGHT_GRAY , Color.LIGHT_GRAY);
        // adding the border to the game
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
    }

    /**
     * The method returns how many blocks remained.
     * @return how many blocks remained.
     */
    public int getBlocksRemained() {
        return blocksCounter.getValue();
    }

    /**
     * The method returns how many balls remained.
     * @return how many balls remained.
     */
    public int getBallsRemained() {
        return ballsCounter.getValue();
    }
}