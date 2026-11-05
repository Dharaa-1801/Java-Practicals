import java.util.Objects;

// Represents a point on a two-dimensional coordinate plane.
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Display the point in the required coordinate format.
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Point)) {
            return false;
        }

        Point other = (Point) object;
        return x == other.x && y == other.y;
    }

    // Equal points must have the same hash code.
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
