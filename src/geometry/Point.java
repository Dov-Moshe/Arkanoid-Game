// ID: 205694441

package geometry;

/**
 * Class of a Point of double type.
 * The class support basic point operations - comparison with another point, get x and get y,
 * set x and y, and distance from another point.
 */
public class Point {
    // The coordinates of the point
    private double x;
    private double y;
    /**
     * this is a constructor method.
     * the method set the coordinates into the point.
     * @param x a coordinate of the point.
     * @param y a coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * return the distance of this point to the other point.
     * @param other is other point.
     * @return the distance.
     */
    public double distance(Point other) {
        double otherX = other.getX();
        double otherY = other.getY();
        return Math.sqrt(Math.pow(x - otherX, 2) + Math.pow(y - otherY, 2));
    }
    /**
     * return true is the points are equal, false otherwise.
     * @param other is other point.
     * @return boolean value.
     */
    public boolean equals(Point other) {
        double otherX = other.getX();
        double otherY = other.getY();
        if (this.x == otherX && this.y == otherY) {
            return true;
        }
        return false;
    }
    /**
     * this method return the x value of this point.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * this method return the y value of this point.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }
    /**
     * this method creates nicely string of point.
     * @return string of point.
     */
    public String toString() {
        return "(" + Double.toString(this.x) + ", " + Double.toString(this.y) + ")";
    }
}