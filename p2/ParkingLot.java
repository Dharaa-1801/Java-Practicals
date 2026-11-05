public class ParkingLot {
    // Current occupancy of each vehicle section.
    private int twoWheelers;
    private int fourWheelers;

    // Maximum occupancy of each vehicle section.
    private final int twoCap;
    private final int fourCap;

    // Revenue is shared by all parking lots.
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        if (twoCap < 0 || fourCap < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }

    public ParkingLot() {
        this(2, 2);
    }

    // Park a vehicle only when its matching section has space.
    public boolean park(String type) {
        if ("two".equalsIgnoreCase(type)) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                return true;
            }
        } else if ("four".equalsIgnoreCase(type)) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                return true;
            }
        } else {
            System.out.println("Invalid vehicle type");
            return false;
        }

        System.out.println("Full");
        return false;
    }

    // Leaving reduces occupancy but never allows a negative count.
    public boolean leave(String type) {
        if ("two".equalsIgnoreCase(type)) {
            if (twoWheelers > 0) {
                twoWheelers--;
                return true;
            }
        } else if ("four".equalsIgnoreCase(type)) {
            if (fourWheelers > 0) {
                fourWheelers--;
                return true;
            }
        } else {
            System.out.println("Invalid vehicle type");
            return false;
        }
        return false;
    }

    // Parking fees are collected when vehicles enter; leaving does not refund them.
    public static long getRevenue() {
        return revenue;
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 1);

        // The third two-wheeler and second four-wheeler are refused as full.
        System.out.println("Park two: " + lot.park("two"));
        System.out.println("Park two: " + lot.park("two"));
        System.out.println("Park two: " + lot.park("two"));
        System.out.println("Park four: " + lot.park("four"));
        System.out.println("Park four: " + lot.park("four"));
        System.out.println("Leave two: " + lot.leave("two"));
        System.out.println("Two-wheelers: " + lot.twoWheelers);
        System.out.println("Four-wheelers: " + lot.fourWheelers);
        System.out.println("Revenue: " + getRevenue());
    }
}
