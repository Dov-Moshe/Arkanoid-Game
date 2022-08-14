package geometry;

/**
 * Class of a Velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * The class support basic objects.Ball operations - gets dx and dy of the velocity,
 * methot that convert angle and speed to dx and dy, and method that apply new point x and y.
 */
public class Velocity {
    // The ball fields
    private double dx;
    private double dy;
    /**
     * This is a constructor method.
     * @param dx of the velocity.
     * @param dy of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * This is a constructor method.
     * This method is a default constructor, instead of dx and dy gets point.
     * @param movePoint with dx dy of the velocity.
     */
    public Velocity(Point movePoint) {
        this.dx = movePoint.getX();
        this.dy = movePoint.getY();
    }
    /**
     * This method gets angle and speed and converts them to dx and dy.
     * The method calls to the constructor to set them into Velocity.
     * @param angle the angle of the movement.
     * @param speed the speed of the movement.
     * @return Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
    /**
     * This is a accessor method.
     * @return dx of the Velocity.
     */
    public double getX() {
        return this.dx;
    }
    /**
     * This is a accessor method.
     * @return dy of the Velocity.
     */
    public double getY() {
        return this.dy;
    }
    /**
     * This method gets point get x and y, and applys them.
     * The method take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     * @param p a point with coordinates.
     * @return geometry.Point, it is the new point after the updating.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}