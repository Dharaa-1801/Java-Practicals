class CinemaShow
{
    private String title;
    private int seatsAvailable;
    private final int CAPACITY;
     private static int totalBooked=0;

     CinemaShow(String t, int c)
     {
        title=t;
        CAPACITY=c;
        seatsAvailable=c;
     }
     CinemaShow(String t)
     {
        this(t,100);
     }
     public int book(int n)
     {
        if (n <= seatsAvailable)
        {
         seatsAvailable=seatsAvailable- n;
         totalBooked=totalBooked+n; 
         return 1; 
        }
        else 
        {
            return 0;
        }
     }
     public int cancel(int n)
     {
        if(seatsAvailable==CAPACITY || (seatsAvailable+n)>CAPACITY)
        {
            System.out.println("Invalid!! Can not Cancel");
            return 0;
        }
        seatsAvailable=seatsAvailable+n;
        totalBooked=totalBooked-n;
        return seatsAvailable;


     }
    public void getSeatsAvailable()
     {
        System.out.println("SeatsAvailable:"+seatsAvailable);
     }
     public static void getTotalBooked()
     {
        System.out.println("Total Booked:"+totalBooked);
     }
     public static void main(String []args)
     {
        CinemaShow c1=new CinemaShow("ALPHA",100);

        c1.book(50);
        c1.cancel(10);
   

        c1.getSeatsAvailable();
        c1.getTotalBooked();


     }

}
