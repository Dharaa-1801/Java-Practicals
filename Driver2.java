import java.util.*;
public class Driver2
{
    public static void main(String []args)
    {
        Card []c=new Card[5];
        Scanner Sc=new Scanner(System.in);
        for(int i=0;i<c.length;i++)
        {
            System.out.println("Enter rank and suit:");
            c[i]=new Card(Sc.next(),Sc.next());
            System.out.println("Card " + c[i]);
        }
        Card []in=new Card[5];
        int count=0;
        for(Card x:c)
        {
            boolean duplicate=false;
             for(int i=0;i<count;i++)
             {
                if(in[i].equals(x))
                {
                    duplicate=true;
                 
                    break;

                }
             }
                if(duplicate)
                {
                       System.out.println("Duplicate Card Found "+x);

                }
                else
                {
                    in[count++]=x;
                    System.out.println("Addded Card:"+x);
                }

             

        }
    }
}