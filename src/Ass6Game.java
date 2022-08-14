// ID: 205694441

import animation.AnimationRunner;
import interfaces.LevelInformation;
import levels.DirectHit;
import levels.WideEasy;
import levels.FinalFour;
import levels.Green;
import other.Constants;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with main method.
 * The class running the game.
 */
public class Ass6Game {
    /**
     * This is main method of the game.
     * the main creates a geme by class "Game", initializes and run the game.
     * @param args command line argument.
     */
    public static void main(String[] args) {
        // Initialization of some classes
        AnimationRunner animationRunner = new AnimationRunner(Constants.FRAMES_PER_SECOND);
        GUI gui = new GUI("Arkanoid", Constants.WINDOW_W, Constants.WINDOW_H);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, gui, keyboard);
        List<LevelInformation> levels = new ArrayList<>();
        //checking the arguments
        int[] numbers = new int[args.length];
        int j = 0;
        for (int i = 0; i < args.length; i++) {
            if (isNumber(args[i])) {
                double num = Integer.parseInt(args[i]);
                if (num == 1  || num == 2  || num == 3  || num == 4) {
                    numbers[j] = Integer.parseInt(args[i]);
                    j++;
                }
            }
        }
        // Initialization of the levels
        if (j == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green());
            levels.add(new FinalFour());
        } else {
            for (int i = 0; i < j; i++) {
                if (numbers[i] == 1) {
                    levels.add(new DirectHit());
                } else if (numbers[i] == 2) {
                    levels.add(new WideEasy());
                } else if (numbers[i] == 3) {
                    levels.add(new Green());
                } else if (numbers[i] == 4) {
                    levels.add(new FinalFour());
                }
            }
        }
        gameFlow.runLevels(levels);
    }

    /**
     *
     * @param s the string.
     * @return boolean value.
     */
    static boolean isNumber(String s) {
        try {
            int val = Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}