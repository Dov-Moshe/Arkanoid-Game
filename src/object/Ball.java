package object;
import animation.GameLevel;
import geometry.Point;
import geometry.Velocity;
import geometry.Line;
import interfaces.Sprite;
import other.GameEnvironment;
import other.CollisionInfo;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Class of a Ball.
 * The class support basic Ball operations - gets x and y of its coordinates,
 * gets size of the ball, gets its color, set x and y of its next location,
 * drawing the ball on draw surface and moving the to its next location.
 */
public class Ball implements Sprite {
    // The ball's fields
    private Point center;
    private int radius;
    private Color color;
    private Velocity v;
    private GameEnvironment environment;
    private Color colorCircle;
    /**
     * This is a constructor method.
     * The method set the point, radius and color of the ball.
     * @param center the location of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     * @param environment the objects.collidable.GameEnvironment of the ball.
     */
    public Ball(Point center, double r, java.awt.Color color, GameEnvironment environment) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = (int) r;
        this.color = color;
        this.environment = environment;
        // default
        this.v = new Velocity(0, 0);
        colorCircle = Color.BLACK;
    }
    /**
     * This is a default constructor method.
     * The method set the point, radius and color of the ball.
     * @param center the location of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, double r, java.awt.Color color) {
        this(center, r, color, new GameEnvironment());
    }
    /**
     * This is a default constructor method.
     * This method is a default constructor, instead of point gets x and y.
     * @param x a coordinate of the ball.
     * @param y a coordinate of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color, new GameEnvironment());
    }

    /**
     * This is a accessor method.
     * @return x a coordinate of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * This is a accessor method.
     * @return y a coordinate of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * This is a accessor method.
     * @return size of the ball.
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * This is a accessor method.
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * This method draws the ball on the given DrawSurface.
     * @param d the draw surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        d.setColor(this.colorCircle);
        d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }
    /**
     * This method gets a new velocity and sets them into the velocity of the ball.
     * @param newV a new velocity.
     */
    public void setVelocity(Velocity newV) {
        this.v = new Velocity(newV.getX(), newV.getY());
    }
    /**
     * This method gets a new dx and dy and sets them into the velocity of the ball.
     * @param dx a a new dx.
     * @param dy a a new dy.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }
    /**
     * This method return the Velocity of the ball.
     * @return Velocity of this ball.
     */
    public Velocity getVelocity() {
        return v;
    }
    /**
     * This method moves the ball.
     * The method add to x and y the dx and dy of velocity.
     */
    public void moveOneStep() {
        double epsilon = 5;
        // the x and y for next next move, to check if the next next move is right
        // it will cause that the ball will not get stuck into some object
        double checkX = this.getVelocity().applyToPoint(this.center).getX() + this.v.getX();
        double checkY = this.getVelocity().applyToPoint(this.center).getY() + this.v.getY();
        CollisionInfo c = this.environment.getClosestCollision(new Line(center, new Point(checkX, checkY)));
        if (c != null) {
            this.v = c.collisionObject().hit(this, c.collisionPoint(), this.v);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    /**
     * Default method to move the ball.
     * If the ball is not on the edge of the window then the method just add to x and y the velocity,
     * else, the method calls to method "newPosition" that changes the value of the velocity,
     * and add to x and y the velocity.
     * @param topX the coordinate x where the window start.
     * @param topY the coordinate y where the window start.
     * @param bottomX the coordinate x where the window end.
     * @param bottomY the coordinate y where the window end.
     */
    public void moveOneStep(int topX, int topY, int bottomX, int bottomY) {
        // the x and y for next move, to check if they are into the window
        double checkX = this.getVelocity().applyToPoint(this.center).getX();
        double checkY = this.getVelocity().applyToPoint(this.center).getY();
        moveOneStep();
        // if the ball is on the edge of the window then calling to method "newPosition"
        if ((checkX > bottomX - radius) || (checkY > bottomY - radius)
                || (checkX < topX + radius) || (checkY < topY + radius)) {
            newPosition(topX, topY, bottomX, bottomY, checkX, checkY);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    /**
     * Default method to move the ball.
     * If the coordinates of the top of the window (x and y) are equal,
     * and the coordinates of the bottom of the window (x and y) are equal.
     * @param topWindow a coordinates (x and y) where the window start.
     * @param bottomWindow a coordinates (x and y) where the window end.
     */
    public void moveOneStep(int topWindow, int bottomWindow) {
        moveOneStep(topWindow, topWindow, bottomWindow, bottomWindow);
    }
    /**
     * Default method to move the ball.
     * If the input of the metod is just a size of window that the ball need to be inside,
     * in that case the default of the top of the window (x and y) will be 0,
     * and the bottom of the window (x and y) will be the size of the window.
     * @param bottomWindow a coordinates (x and y) where the window end.
     */
    public void moveOneStep(int bottomWindow) {
        moveOneStep(0, 0, bottomWindow, bottomWindow);
    }
    /**
     * This method moves the ball to new direction.
     * If the next coordinate of the ball (x+dx or y+dy) will be outside the window,
     * then the method will change the value of this dx or dy to -dx or -dy,
     * and by that the direction of the ball will change.
     * @param topX the coordinate x of the top of the window
     * @param topY the coordinate y of the top of the window
     * @param bottomX the coordinate x of the bottom of the window
     * @param bottomY the coordinate y of the bottom of the window
     * @param newX option to be a new coordinate of the ball.
     * @param newY option to be a new coordinate of the ball.
     */
    private void newPosition(int topX, int topY, int bottomX, int bottomY, double newX, double newY) {
        if (newX > bottomX - radius) {
            // if the ball at the bottom (right) then X changes to -X
            this.setVelocity(-v.getX(), v.getY());
        } else if (newY > bottomY - radius) {
            // if the ball at the bottom (down) then Y changes to -Y
            this.setVelocity(v.getX(), -v.getY());
        } else if (newX < topX + radius) {
            // if the ball at the top (left) then X changes to -X
            this.setVelocity(-v.getX(), v.getY());
        } else if (newY < topY + radius) {
            // if the ball at the top (up) then Y changes to -Y
            this.setVelocity(v.getX(), -v.getY());
        }
    }
    /**
     * This method set the game environment to the ball.
     * @param gameEnvironment the GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }
    /**
     * This method calls to method "moveOneStep", and activated it.
     */
    public void timePassed() {
        moveOneStep();
    }
    /**
     * This method adds the ball to the game.
     * The method gets the game and adds the ball to its Sprite.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * This method removes the ball from the game.
     * The method gets the game and removes the ball from its Sprite.
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
    /**
     * This method sets a new color for the circle of the ball.
     * The method changes the default color.
     * @param color the new color.
     */
    public void setColorCircle(Color color) {
        this.colorCircle = color;
    }

}