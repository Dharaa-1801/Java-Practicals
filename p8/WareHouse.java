import java.util.Scanner;

class OutOfStockException extends Exception {
    String msg;
    int shortfall;

    OutOfStockException(String msg, int shortfall) {
        this.msg = msg;
        this.shortfall = shortfall;
    }

    public String toString() {
        return (msg + " " + shortfall + " is the shortfall");
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String msg) {
        super(msg);
    }
}

class WareHouse {
    String[] item = { "apple", "orange", "watermelon" };
    int[] qty = { 10, 7, 8 };

    void issue(String item, int qty) throws InvalidQuantityException, OutOfStockException {
        int index = 0;
        boolean found = false;
        if (qty <= 0)
            throw new InvalidQuantityException("Please Enter Valid Quantity. Zero or negative quantity is allowed..");
        else {

            for (int i = 0; i < this.item.length; i++) {
                if (this.item.equals(this.item[i])) {
                    index = i;
                    found = true;
                    break;
                }
            }

            if(!found){
                System.out.println("Entered Item is not found in the list");
            }
            else if(this.qty[index] < qty)
                throw new OutOfStockException("The Entered Number of items is not available in the stock.a", qty-this.qty[index]);
            else{
                System.out.println("Order Placed Successfully");
                this.qty[index] -= qty;
            }
        }

    }
}

public class WareHouseDemo {
    public static void main(String[] args) {
        WareHouse w1 = new WareHouse();
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Enter the Name of the item:- ");
            String item = sc.nextLine().toLowerCase();
            System.out.println("Enter the quantity you required for the entered item:- ");
            int qty = sc.nextInt();
            w1.issue(item, qty);
        }catch(InvalidQuantityException ie){
            System.out.println(ie);
        }catch(OutOfStockException oe){
            System.out.println(oe);
        }
    }
}