package object;
import interfaces.Sprite;
import interfaces.Collidable;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import animation.GameLevel;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import other.Constants;

import java.awt.Color;

/**
 * Class of a Paddle.
 */
public class Paddle implements Collidable, Sprite {
    // The Paddle's fields
    private biuoop.KeyboardSensor keyboard;
    private biuoop.GUI gui;
    private Rectangle rect;
    private Color color;
    int speed;

    /**
     * This is a constructor method.
     * The method sets the rectangle and the color of the Paddle, sets the GUI.
     * @param gui the graphic window.
     * @param rect the rectangle of the paddle.
     * @param color the color of the paddle.
     */
    public Paddle(GUI gui, Rectangle rect, Color color, int speed) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.rect = rect;
        this.color = color;
        this.speed = speed;
    }
    /**
     * This method moves the paddle left.
     */
    public void moveLeft() {
        Point upperLeft = rect.getUpperLeft();
        // 'if' to be sure that the paddle will not leave the window from left side
        if (upperLeft.getX() > 0) {
            rect.setUpperLeft(new Point(upperLeft.getX() - speed, upperLeft.getY()));
        }
    }
    /**
     * This method moves the paddle right.
     */
    public void moveRight() {
        Point upperLeft = rect.getUpperLeft();
        // 'if' to be sure that the paddle will not leave the window from right side
        if (upperLeft.getX() + rect.getWidth() < gui.getDrawSurface().getWidth()) {
            rect.setUpperLeft(new Point(upperLeft.getX() + speed, upperLeft.getY()));
        }
    }
    /**
     * This method sensors the Keyboard presses.
     * If the button that is pressed is left, then calling to method "moveLeft" to move the paddle left,
     * if the button that is pressed is right, then calling to method "moveRight" to move the paddle right.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * This method draws the paddle on the DrawSurface.
     * @param d the DrawSurface
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        // drawing a paddle
        d.setColor(this.color);
        d.fillRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        // drawing a black outline
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }
    /**
     * This method returns the rectangle of the paddle.
     * @return the geometry.Rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }
    /**
     * This method change the direction of objects that hits the paddle.
     * The new direction is determined by the location of the hit.
     * @param collisionPoint the interaction point with the paddle.
     * @param currentVelocity the Velocity.
     * @param hitter the ball.
     * @return new Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // parting the width of the paddle to five parts
        double onePartLength = rect.getWidth() / 5;
        double part1 = rect.getUpperLeft().getX() + onePartLength;
        double part2 = part1 + onePartLength;
        double part3 = part2 + onePartLength;
        double part4 = part3 + onePartLength;
        // converting the Velocity to speed
        double speed = Math.sqrt(Math.pow(currentVelocity.getX(), 2) + Math.pow(currentVelocity.getY(), 2));

        if (collisionPoint.getX() <  part1) {
            // if the hitting is on the part 1
            Velocity v = currentVelocity.fromAngleAndSpeed(300, speed);
            return new Velocity(v.getX(), -v.getY());
        } else if (collisionPoint.getX() <  part2) {
            // if the hitting is on the part 2
            Velocity v = currentVelocity.fromAngleAndSpeed(330, speed);
            return new Velocity(v.getX(), -v.getY());
        } else if (collisionPoint.getX() <  part3) {
            // if the hitting is on the part 3
            if (collisionPoint.getY() <= rect.getUpperLeft().getY()
                    || collisionPoint.getY() >= rect.getLowerLeft().getY()) {
                return new Velocity(currentVelocity.getX(), -currentVelocity.getY());
            } else {
                return new Velocity(-currentVelocity.getX(), currentVelocity.getY());
            }
        } else if (collisionPoint.getX() <  part4) {
            // if the hitting is on the part 4
            Velocity v = currentVelocity.fromAngleAndSpeed(-30, speed);
            return new Velocity(-v.getX(), -v.getY());
        } else {
            // if the hitting is on the part 5
            Velocity v = currentVelocity.fromAngleAndSpeed(-60, speed);
            return new Velocity(-v.getX(), -v.getY());
        }
    }
    /**
     * This method add the paddle to the game.
     * @param g object of Game type.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}