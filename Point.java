class Point
{
    private int x;
    private int y;
    Point(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public String toString()
    {
       return "("+ x + "," + y +")";
    }
    public boolean equals(Object o)
    {
        if(this==o)
        {
            return true;
        }
        if(!(this instanceof Point))
        {
            return false;
        }
        Point p=(Point)o;
        if(this.x==p.x && this.y==p.y)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}