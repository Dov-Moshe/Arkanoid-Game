package geometry;
import java.util.List;

/**
 * Class of a Line of double type.
 * The class support basic line operations - comparison with another line, length of line,
 * middle point of line, intersection point with other point, get start and end point of line.
 */
public class Line {
    // the start and end point of the line
    private Point start;
    private Point end;
    /**
     * this is a constructor method.
     * the method set two points into start and end points.
     * @param start a point of the start point.
     * @param end a point of the end point.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }
    /**
     * this is a constructor method.
     * the method set the coordinates of two points into start and end points.
     * @param x1 a coordinate of the start point.
     * @param y1 a coordinate of the start point.
     * @param x2 a coordinate of the end point.
     * @param y2 a coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * calculate the length of the line.
     * @return length of the line.
     */
    public double length() {
        double startX = this.start.getX();
        double startY = this.start.getY();
        double endX = this.end.getX();
        double endY = this.end.getY();
        return Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
    }
    /**
     * calculate the middle point of the line.
     * @return middle point of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }
    /**
     * Returns the start point of the line.
     * @return start point of the line
     */
    public Point start() {
        return this.start;
    }
    /**
     * Returns the end point of the line.
     * @return end point of the line
     */
    public Point end() {
        return this.end;
    }
    /**
     * the method returns true if the lines intersect, false otherwise.
     * the method helped by calling to method 'intersectionWith'.
     * @param other the other line.
     * @return boolean value if there is a intersection point or not
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }
    /**
     * the method returns the intersection point if the lines intersect. and null otherwise.
     * the method calculates the intersection point by calling to method 'intersectionWithHelp',
     * and to method 'isIntersectionPoint'.
     * @param other the other line.
     * @return the value of a intersection point or null.
     */
    public Point intersectionWith(Line other) {
        // the point with an option to be 'intersection point'
        Point intersectionPoint = intersectionWithHelp(other);
        // to check if 'intersectionPoint' is actually intersection point
        if (intersectionPoint == null) {
            return null;
        } else if (isIntersectionPoint(other, intersectionPoint)) {
            return intersectionPoint;
        }
        return null;
    }
    /**
     * the method returns true is the lines are equal, false otherwise.
     * @param other the other line.
     * @return boolean value.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }
    /**
     * the method checks if the intersection point is between both lines.
     * if the intersection point is between both lines, then the method returns true, false otherwise.
     * @param other the other line.
     * @param intersectionPoint the point that needs to be checked.
     * @return boolean value.
     */
    private boolean isIntersectionPoint(Line other, Point intersectionPoint) {
        // put all the x and y in variables in order
        double thisMaxX = Math.max(this.start.getX(), this.end.getX());
        double thisMinX = Math.min(this.end.getX(), this.start.getX());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double otherMaxX = Math.max(other.start.getX(), other.end.getX());
        double otherMinX = Math.min(other.start.getX(), other.end.getX());
        double otherMaxY = Math.max(other.start.getY(), other.end.getY());
        double otherMinY = Math.min(other.start.getY(), other.end.getY());
        double intersectionX = intersectionPoint.getX();
        double intersectionY = intersectionPoint.getY();
        // 'if' to check if the intersection point is between both lines
        if (!(thisMinX <= intersectionX && intersectionX <= thisMaxX)) {
            return false;
        } else if (!(thisMinY <= intersectionY && intersectionY <= thisMaxY)) {
            return false;
        } else if (!(otherMinX <= intersectionX && intersectionX <= otherMaxX)) {
            return false;
        } else if (!(otherMinY <= intersectionY && intersectionY <= otherMaxY)) {
            return false;
        }
        // if the intersection point is between both lines the returned true
        return true;
    }
    /**
     * This method manages the process to find the intersection point.
     * The method do it mostly by identify the case and just calling to others method,
     * and returned their return to method "intersectionWith".
     * If it is a simple case then the the x and y of the intersection point set in place
     * and returned.
     * @param other the other line.
     * @return the point with an option to be 'intersection point'
     */
    private Point intersectionWithHelp(Line other) {
        // this is my definition for a line with a vertical slope
        double inf = Double.POSITIVE_INFINITY;
        // this check the slope of the lines to catch the extreme cases
        if (slope(this) == slope(other)) {
            // return if one line start where the ather end
            // if is true then returned this point, else returned null
            return sameSlope(other);
        } else if (slope(this) == inf && slope(other) == inf) {
            // if both lines are vertical
            return null;
        } else if (slope(this) == 0 && slope(other) == 0) {
            // if both lines are horizontal
            return null;
        } else if (slope(this) == inf && slope(other) == 0) {
            // if this line is vertical and the ather line is horizontal
            return new Point(this.start.getX(), other.start.getY());
        } else if (slope(this) == 0 && slope(other) == inf) {
            // if this line is horizontal and the ather line is vertical
            return new Point(other.start.getX(), this.start.getY());
        } else if (slope(this) == 0 || slope(this) == inf
                || slope(other) == 0 || slope(other) == inf) {
            // if one line is horizontal or vertical and ather is regular line
            return specialIntersection(other);
        } else {
            // if both lines are regular
            return regularIntersection(other);
        }

    }
    /**
     * the method handles the case that both lines have same slope.
     * if one line start where the ather end the there is a intersection point.
     * @param other the other line.
     * @return the point of intersection or null.
     */
    private Point sameSlope(Line other) {
        if (this.start.equals(other.start) && !this.end.equals(other.end)) {
            // if this line start where the ather line start
            return new Point(this.start.getX(), this.start.getY());
        } else if (this.end.equals(other.end) && !this.start.equals(other.start)) {
            // if this line end where the ather line end
            return new Point(this.end.getX(), this.end.getY());
        } else if (this.start.equals(other.end) && !this.end.equals(other.start)) {
            // if this line start where the ather line end
            return new Point(this.start.getX(), this.start.getY());
        } else if (this.end.equals(other.start) && !this.start.equals(other.end)) {
            // if this line end where the ather line start
            return new Point(this.end.getX(), this.end.getY());
        }
        // if all cases above don't happen, then there is no a intersection point
        return null;
    }
    /**
     * the method handles the case that one if one line is horizontal or vertical, and other is not.
     * @param other the other line.
     * @return the point of intersection or null.
     */
    private Point specialIntersection(Line other) {
        double inf = Double.POSITIVE_INFINITY;
        double xFinal = 0;
        double yFinal = 0;
        double slope, yIntersection;
        // to calculate the slope of special intersection
        if (slope(this) == 0) {
            // if this line is horizontal.
            yFinal = this.start.getY();
            slope = slope(other);
            yIntersection = other.start.getY() - slope * other.start.getX();
            xFinal = (yFinal - yIntersection) / slope;
        } else if (slope(this) == inf) {
            // if this line is vertical.
            xFinal = this.start.getX();
            slope = slope(other);
            yIntersection = other.start.getY() - slope * other.start.getX();
            yFinal = slope * xFinal + yIntersection;
        } else if (slope(other) == 0) {
            // if the other line is horizontal.
            yFinal = other.start.getY();
            slope = slope(this);
            yIntersection = this.start.getY() - slope * this.start.getX();
            xFinal = (yFinal - yIntersection) / slope;
        } else if (slope(other) == inf) {
            // if the other line is vertical.
            xFinal = other.start.getX();
            slope = slope(this);
            yIntersection = this.start.getY() - slope * this.start.getX();
            yFinal = slope * xFinal + yIntersection;
        }
        return new Point(xFinal, yFinal);
    }
    /**
     * the method handles in the regular case.
     * If the slope of both lines are not horizontal or vertical.
     * The process is: find the equation Of both linse, then to compare them to each other'
     * by this to find the x and then to find the y of the intersection point.
     * @param other the other line.
     * @return the point of intersection or null.
     */
    private Point regularIntersection(Line other) {
        double xFinal = 0;
        double yFinal = 0;
        // m = (y2 - y1) / (x2 - x1)
        double slope1 = slope(other);
        double slope2 = slope(this);
        // y = m * x - b
        double yIntersection1 = other.start.getY() - slope1 * other.start.getX();
        double yIntersection2 = this.start.getY() - slope2 * this.start.getX();
        // m1 * x - b1 = m2 * x - b2
        // m1 * x - m2 * x = b1 - b2
        // x (m1 - m2) = b1 - b2
        // x = (b1 - b2) / (m1 - m2)
        xFinal = (yIntersection1 - yIntersection2) / (-(slope1 - slope2));
        // y = m * x + b
        yFinal = slope1 * xFinal + yIntersection1;
        return new Point(xFinal, yFinal);
    }
    /**
     * the method calculate a slope of point.
     * the method get two point, clculate the slope of the point, and return the value.
     * @param line some line.
     * @return the slope value.
     */
    private double slope(Line line) {
        if (line.start.getX() == line.end.getX()) {
            // if the line is vertical
            return Double.POSITIVE_INFINITY;
        } else if (line.start.getY() == line.end.getY()) {
            // if the line is horizontal
            return 0;
        }
        // if the line dosen't vertical or horizontal
        return (line.start.getY() - line.end.getY()) / (line.start.getX() - line.end.getX());
    }
    /**
     * This method returns the closest intersection point to the start.
     * The method gets rectangle and returns the closest intersection point of to the start of the line,
     * by calling to method "intersectionPoints" from Rectangle class.
     * If there is no intersection points then returned 'null'.
     * @param rect a rectangle
     * @return Point a intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints == null) {
            return null;
        } else {
            // if there is at least one intersection point
            if (intersectionPoints.size() == 2) {
                // if there is two intersection points
                double len1 = this.start.distance(intersectionPoints.get(0));
                double len2 = this.start.distance(intersectionPoints.get(1));
                // returned the closest intersection point to the start of the line
                if (len1 < len2) {
                    return intersectionPoints.get(0);
                } else {
                    return intersectionPoints.get(1);
                }
            } else {
                // if there is one intersection point
                return intersectionPoints.get(0);
            }
        }
    }
}