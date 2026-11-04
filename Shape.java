public abstract class Shape
{
    abstract double area();
}
class Circle extends Shape
{
    double r;
    Circle(double r)
    {
        this.r=r;
    }
    public double area()
    {
        double a=3.14*r*r;
        return a;
    }
}
class Rectangle extends Shape
{
    double length;
    double width;
    Rectangle(double length,double width)
    {
        this.length=length;
        this.width=width;
    }
    public double area()
    {
        double b=length*width;
        return b;
    }
}

class Triangle extends Shape
{
    double base;
    double height;
    Triangle(double base,double height)
    {
        this.base=base;
        this.height=height;
    }
    public double area()
    {
        double c=(base*height)/2;
        return c;
    }
}

class ShapeDemo
{
    
    public static void main(String args[])
    {
        
        Shape [] shapes = {new Circle(5), new Rectangle(4, 6), new Triangle(3, 4)};
        
        double total = 0;
        double largest = 0;
        String label="";
        for(Shape s:shapes)
        {
            System.out.println(s.area());

            total+=s.area();

            if(largest<s.area())
            {
                largest = s.area();
                label=s.getClass().getSimpleName();
            }

        }

        System.out.println("Total Area = "+total);
        System.out.println("Largest area  = "+largest);
        System.out.println("Name of class(Largest area): "+ label);
    }
}