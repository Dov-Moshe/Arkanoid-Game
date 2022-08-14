package geometry;
import java.util.List;

/**
 * Class of a Rectangle.
 * The class support basic line operations -
 */
public class Rectangle {
    // The Rectangle's fields
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * this is a constructor method.
     * the method creating a new rectangle with location and width/height.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the width of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }
    /**
     * This method checks intersection points with line.
     * The method gets line and checks if the rectangle there is intersection points with this line.
     * If there is no intersection points then returned 'null',
     * otherwise returned a list with the intersection points.
     * @param line the upper left point of the rectangle.
     * @return intersectionPoints a list with intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        // creating array of geometry.Point to keep the intersection points
        List<Point> intersectionPoints = new java.util.ArrayList<>();
        // creating array of geometry.Line to keep the lines of the rectangle
        List<Line> rectangleLines = new java.util.ArrayList<>();
        // creating the line of the rectangle
        rectangleLines.add(getUpperLine());
        rectangleLines.add(getLowerLine());
        rectangleLines.add(getLeftLine());
        rectangleLines.add(getRightLine());
        // checking intersection points of the argument "line" with lines of the rectangle
        for (Line rectLine : rectangleLines) {
            Point intersectionPoint = line.intersectionWith(rectLine);
            if (intersectionPoint != null) {
                intersectionPoints.add(intersectionPoint);
            }
        }
        // if there is a intersection point
        if (intersectionPoints.size() != 0) {
            return intersectionPoints;
        }
        return null;
    }
    /**
     * this method returns the width of the rectangle.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * this method returns the height of the rectangle.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * this method returns upper-left point of the rectangle.
     * @return upperLeft
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * this method returns upper-right point of the rectangle.
     * @return uppreRight
     */
    public Point getUppreRight() {
        return new Point(upperLeft.getX() + width, upperLeft.getY());
    }
    /**
     * this method returns lower-left point of the rectangle.
     * @return lowerLeft
     */
    public Point getLowerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + height);
    }
    /**
     * this method returns lower-right point of the rectangle.
     * @return lowerRight
     */
    public Point getLowerRight() {
        return new Point(getUppreRight().getX(), getLowerLeft().getY());
    }
    /**
     * this method sets the upper left point of the rectangle.
     * @param p the new upper-left point.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
    /**
     * this method returns the upper line of the rectangle.
     * @return upper line
     */
    public Line getUpperLine() {
        return new Line(upperLeft, getUppreRight());
    }
    /**
     * this method returns the lower line of the rectangle.
     * @return lower line
     */
    public Line getLowerLine() {
        return new Line(getLowerLeft(), getLowerRight());
    }
    /**
     * this method returns the right line of the rectangle.
     * @return right line
     */
    public Line getRightLine() {
        return new Line(getUppreRight(), getLowerRight());
    }
    /**
     * this method returns the left line of the rectangle.
     * @return left line
     */
    public Line getLeftLine() {
        return new Line(upperLeft, getLowerLeft());
    }
}
