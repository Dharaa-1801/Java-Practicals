import java.util.Objects;

// Represents a fraction stored in reduced form.
public class Fraction {
    private int num;
    private int den;

    public Fraction(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        int divisor = gcd(Math.abs(num), Math.abs(den));
        this.num = num / divisor;
        this.den = den / divisor;

        // Store a negative sign only in the numerator.
        if (this.den < 0) {
            this.num = -this.num;
            this.den = -this.den;
        }
    }

    // Use Euclid's algorithm to calculate the greatest common divisor.
    private static int gcd(int first, int second) {
        while (second != 0) {
            int remainder = first % second;
            first = second;
            second = remainder;
        }
        return first;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    // Reduced fractions are equal when both parts match.
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Fraction)) {
            return false;
        }

        Fraction other = (Fraction) object;
        return num == other.num && den == other.den;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }
}
