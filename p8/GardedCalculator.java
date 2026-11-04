import java.util.*;
class DividebyZeroException extends Exception
{
    DividebyZeroException(String msg)
    {
        super(msg);
    }
}
public class GardedCalculator 
{
    static double n1=0,n2=0;
    static String op="NULL";
    static double result=0;

    public static void main(String []a)
    {
        Scanner sc=new Scanner(System.in);
        int attempt=0;
        boolean success=false;

        while(!success)
        {
            attempt++;

            try
            {
                n1=Double.parseDouble(sc.nextLine());
                op=sc.nextLine().trim();
                n2=Double.parseDouble(sc.nextLine());

                if(n2==0 && op.equals("/"))
                {
                    throw new DividebyZeroException("Division by zero is not allowed.");
                }

                switch(op)
                {
                    case "+":
                        result=n1+n2;
                        break;
                    case "-":
                        result=n1-n2;
                        break;
                    case "*":
                        result=n1*n2;
                        break;
                    case "/":
                        result=n1/n2;
                        break;
                    default:
                        System.out.println("Invalid operator.");
                        continue;
                }

                success=true;
                System.out.println("Result: " + result);

            }

            catch(DividebyZeroException e)
            {
                System.out.println(e);
            }
        
            catch(Exception e)
            {
                System.out.println("An error occurred: " + e.getMessage());
            }

            finally
            {
                System.out.println("Attempt " + attempt);

            }
        }
        
    }
}
