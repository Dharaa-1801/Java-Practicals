public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        if (base < 0 || height < 0) {
            throw new IllegalArgumentException("Dimensions cannot be negative");
        }
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return 0.5 * base * height;
    }
}
