import java.util.*;
class prac1_2
{
	public record Vehicle(String number,String type)
 	{
	}
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		int totalToll=0;
		int car=0,bike=0,truck=0;
		
		while(true)
		{
			System.out.println("Enter vehicle type or (done):");
			String number = sc.next();

			if(number.equalsIgnoreCase("done"))
    			break;

			System.out.print("Enter vehicle type (bike/car/truck): ");
			String type=sc.next().toLowerCase();
			Vehicle v = new Vehicle(number, type);

		  switch(v.type())
		  {
			case "bike":totalToll+=20;bike++;
				    break;
			case "car":totalToll+=50;car++;
				    break;
			case "truck":totalToll+=150;truck++;
				    break;
		  }
		}

		System.out.println("The total Toll:"+totalToll);
		if(bike > car && bike > truck)
		{
			System.out.println("Most frequent: bike");
		}
		else if(car > bike && car > truck)
		{
			System.out.println("Most frequent: car");	
		}
		else
		{
			System.out.println("Most frequent: truck");
		}
}
}