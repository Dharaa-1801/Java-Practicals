import java.util.*;
class prac1_1{
    enum Coin {ONE, TWO, FIVE ,TEN};
    public static void main(String []args)
    {
        final int PRICE=15;
        int total=0;

        System.out.println("Price:"+PRICE);
        Scanner sc=new Scanner(System.in);
	Coin c=null;
	while(total<PRICE)
	{
        	System.out.println("Enter the coin value{ONE, TWO, FIVE, TEN}:");
        	String input=sc.next().toUpperCase();
		
		try{
		     c=Coin.valueOf(input);
		    }
		catch(Exception e)
		{
			System.out.println("Not a valid coin. Try again!!");
		}
		int value=0;
		switch(c)
		{
			case ONE:value=1;
				 break;
			case TWO: value=2;
				 break;
			case FIVE: value=5;
				 break;
			case TEN: value=10;
				 break;
		}
		total+=value;
		System.out.println("Total coin inserted:"+total);
		
	
    }
    System.out.println("Paid.. change="+(total-PRICE));
}
}