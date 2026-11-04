public class Stack
{
    private int SIZE;
    private int []arr=new int[SIZE];
    private int tos;//top of the stack

    Stack(int SIZE)
    {
        this.SIZE=SIZE;
        tos=-1;
    }

    public void push(int item)
    {
        arr[++tos]=item;
        if((SIZE+1)==tos)
        {
            System.out.println("Overflow");
            return;
        }
        arr[++tos]=item;
        System.out.println("Element "+item +" inserted at " + tos);
    }

    public int pop()
    {
        if(tos==(-1))
        {
            System.out.println("Underflow");
            return -1;
        }
        else
        {
            return arr[tos--];
        }
    }

    public void display()
    {
        for(int i=tos;i>=0;i--)
        {
            System.out.println(arr[i]);
        }
    }

    public int peek()
    {
        return arr[tos];
       
    }

    public static void main(String[] args)
    {
    Stack s1=new Stack(5);
    s1.push(21);
    s1.push(2);
    s1.push(26);
    s1.push(28);
    s1.push(29);
    s1.push(8);

    s1.display();
    }

}