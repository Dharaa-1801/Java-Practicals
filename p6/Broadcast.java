interface Notifier
{
    void send(String message);
}
interface Urgent{
    //blank
}
class SendEmail implements Notifier
{
    public void send(String message)
    {
        System.out.println("Email: "+message);
    }
}
class SendSMS implements Notifier,Urgent
{
    public void send(String message)
    {
        System.out.println("SMS: "+message);
    }

}
public class Broadcast
{
    public static void main(String[] args)
    {
        Notifier[] senders={new SendEmail(),new SendSMS()};
        String message="Server is Down...!!";
        for(Notifier s:senders)
        {
            s.send(message);

            if(s instanceof Urgent)
            {
                s.send("REPEAT MESSAGE: "+message);
            }
        }
    }

}