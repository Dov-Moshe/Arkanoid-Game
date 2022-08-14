package animation;
import interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private int i = 1;
    private Boolean isAlreadyPressed;
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
            //isAlreadyPressed = false;
        }
        if(!sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    private boolean isAlreadyPressed() {
        return isAlreadyPressed;
    }
}