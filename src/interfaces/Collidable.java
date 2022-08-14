package interfaces;
import geometry.Rectangle;
import geometry.Velocity;
import geometry.Point;
import object.Ball;

/**
 * This is interface of Collidable.
 */
public interface Collidable {
    /**
     * This is signature of method that return shape of object.
     * @return geometry.Rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * This is signature of that method changes the direction of object that hits in this object.
     * @param collisionPoint the collision point.
     * @param currentVelocity the velocity that need to be updating.
     * @param hitter the ball.
     * @return the updated velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}