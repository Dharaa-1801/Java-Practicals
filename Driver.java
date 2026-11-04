import java.util.*;
public class Driver
{
    public static void main(String []args)
    {
        Point []p=new Point[5];
        Scanner Sc=new Scanner(System.in);
        for(int i=0;i<p.length;i++)
        {
            System.out.println("Enter the Point:"+(i+1));
            p[i]=new Point(Sc.nextInt(),Sc.nextInt());
            System.out.println("Point created" + p[i]);
        }
        int count=0;
        for(int i=0;i<5;i++)
        {
            for(int j=i+1;j<5;j++)
            {
                if(p[i].equals(p[j]))
                {
                    count++;
                }
            }
        }
        System.out.println("Total Equal Point:"+count);
        System.out.println("Total Distinct Point:"+(p.length-count));

    }
}