class Thermostat
{
    private String location;
    private int temperature;
    private static final int MIN=16;
    private static final int MAX=30;
    private static int activeCount=0;

    Thermostat(String l, int startTemp)
    {
        location=l;

        if(startTemp>MAX || startTemp<MIN)
        {
            startTemp=22;

        }
                temperature=startTemp;
                activeCount++;
    }
   Thermostat(String l)
    {
       this(l,22);

    }
    public void raise()
    {
        if(temperature < MAX)
        {
            temperature=temperature+1;
        }
        else
        {
            System.out.println("Already at maximum (30)");
        }

    }
    public void lower()
    {
        if (temperature > MIN) 
        {
            temperature=temperature-1;
        }
        else
        {
            System.out.println("Already at minimum (16)");
        }
    }
    void getTemperature()
    {
        System.out.println("Tempreture is:"+temperature);
    }
    void getActiveCount()
    {
        System.out.println("Count is:"+activeCount);
    }

    public static void main(String []args)
    {
        Thermostat t1=new Thermostat("Class Room:",28);
        Thermostat t2=new Thermostat("Staff Room:",25);
    
    for(int i=0;i<10;i++)
    {
            t1.raise();
            t1.getTemperature();
    }
        
       for(int i=0;i<20;i++)
       {
            t2.lower();
            t2.getTemperature();
       }
                    t1.getActiveCount();
    }
}
