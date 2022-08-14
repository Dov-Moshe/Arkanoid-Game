// ID: 205694441

package other;
import interfaces.Collidable;
import geometry.Point;

/**
 * Class of a CollisionInfo.
 * If there is a collision, the this class keep the information about this collision.
 * the class keep the object and the point that are involved in this collision.
 */
public class CollisionInfo {
    // The CollisionInfo's fields
    private Collidable collisionObj;
    private Point collisionPoi;
    /**
     * This is a constructor method.
     * @param collisionObj the object.
     * @param collisionPoi the point.
     */
    public CollisionInfo(Collidable collisionObj, Point collisionPoi) {
        this.collisionObj = collisionObj;
        this.collisionPoi = new Point(collisionPoi.getX(), collisionPoi.getY());
    }
    /**
     * This method returns the point at which the collision occurs.
     * @return the point.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoi.getX(), this.collisionPoi.getY());
    }
    /**
     * This method returns the collidable object that involved in the collision.
     * @return the object.
     */
    public Collidable collisionObject() {
        return this.collisionObj;
    }
}