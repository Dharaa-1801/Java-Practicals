public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        if (length < 0 || width < 0) {
            throw new IllegalArgumentException("Dimensions cannot be negative");
        }
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}
