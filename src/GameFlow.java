import animation.AnimationRunner;
import animation.GameLevel;
import animation.EndGame;
import animation.KeyPressStoppableAnimation;
import interfaces.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import other.Counter;
import java.util.List;

/**
 * A class that in charge of running multiple levels.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter scoreCounter;
    private Boolean isWin;

    /**
     * A constructor method.
     * @param ar the Animation Runner.
     * @param gui the window.
     * @param ks the keyboard.
     */
    public GameFlow(AnimationRunner ar, GUI gui, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.gui = gui;
        this.keyboardSensor = ks;
        this.scoreCounter = new Counter();
        this.isWin = true;
    }

    /**
     * This method running levels by 'LevelInformation' that in the list.
     * @param levels a list of LevelInformation.
     */
    public void runLevels(List<LevelInformation> levels) {
        // loop to run lvel after level.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.gui, this.keyboardSensor,
                    this.animationRunner, this.scoreCounter);
            level.initialize();
            level.run();
            // running the current level if there is at least one ball and one block
            while (level.getBlocksRemained() > 0 && level.getBallsRemained() > 0) {
                level.run();
            }
            // if all the balls are over then the game is done
            if (level.getBallsRemained() == 0) {
                this.isWin = false;
                break;
            }
            // if the level is completed then there is a gain of 100 points
            this.scoreCounter.increase(100);
        }
        // the end screen of the game
        EndGame endGame = new EndGame(this.animationRunner, this.scoreCounter.getValue(), this.isWin);
        endGame.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, endGame), this.gui);
        gui.close();

    }
}