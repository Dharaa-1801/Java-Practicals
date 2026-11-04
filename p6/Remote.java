interface switchable
{
    public void turnOn();
    public void turnOff();
    boolean isOn();
    default void toggle()
    {
        if(isOn())
        {
            turnOff();
        }
        else
        {
            turnOn();
        }
    }
}
interface switchOnRule
{
    boolean mayTurnOn(switchable device,int hour);

}
/*class DayTimeRule implements switchOnRule
{
    public boolean mayTurnOn(switchable device,int hour)
    {
        return (hour>=6 && hour<=22);
    }
}*/
class Fan implements switchable
{
    boolean state;

    public void turnOn()
    {
        state=true;
        System.out.println("Fan is turned on");
    }

    public void turnOff()
    {
        state=false;
        System.out.println("Fan is turned off");
    }

    public boolean isOn()
    {
        return state;
    }
}

class Light implements switchable
{
    boolean state;

    public void turnOn()
    {
        state=true;
        System.out.println("Light is turned on");
    }

    public void turnOff()
    {
        state=false;
        System.out.println("Light is turned off");
    }

    public boolean isOn()
    {
        return state;
    }
}
public class Remote
{
    public static void main(String [] args)
    {
        switchable[] r={new Fan(),new Light()};
        for(switchable d:r)
        {
            d.turnOn();
            d.turnOff();
           System.out.println(d.isOn());
        }
        //switchOnRule s=new DayTimeRule();
        switchOnRule rule=new switchOnRule(){
             public boolean mayTurnOn(switchable device,int hour)
             {
                 return (hour>=6 && hour<=22);
             }
        };
        if(rule.mayTurnOn(r[1],50))
        {
            r[1].turnOn();
        }
        else
        {
            System.out.println("Cannot turn on light at this time....");
        }

    }
}