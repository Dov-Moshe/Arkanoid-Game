package other;
import interfaces.Collidable;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import java.util.List;
import java.util.ArrayList;

/**
 * Class of a GameEnvironment.
 * This class manages the data about object that are exists in the environment of the game,
 * and that there is a possibility of collision.
 */
public class GameEnvironment {
    // The GameEnvironment's fields
    private List<Collidable> collidables;
    /**
     * This is a constructor method.
     * The method array of Collidable.
     */
    public GameEnvironment() {
        collidables = new ArrayList<Collidable>();
    }

    /**
     * This method adding Collidable to environment.
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * This method removes object from the list of Collidable.
     * @param c the object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * This method gets line and looking for intersection point with object.
     * The method checks if specific line have a intersection points with some object,
     * if exists then returned the closest intersection point, else returned null.
     * @param trajectory the line.
     * @return intersection point or null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        for (Collidable c : collidables) {
            // choosing a object
            Rectangle r = c.getCollisionRectangle();
            // calling to method from geometry.Line class, to fine the closest intersection point
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(r);
            // check is there is intersection point or not
            if (intersectionPoint != null) {
                return new CollisionInfo(c, intersectionPoint);
            }
        }
        return null;
    }
}