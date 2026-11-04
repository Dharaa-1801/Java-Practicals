class Fraction
{
    private int num;
    private int den;

    Fraction(int n,int d)
    {
        if(d==0)
        {
            System.out.println("Denominator cannot be zero");
            System.exit(0);
        }
        num=n;
        den=d;
      
      int g=gcd(Math.abs(num),Math.abs(den));
        num=num/g;
        den=den/g;

        if (den < 0) 
        {
             num = -num; den = -den; 
        }
    }

    private int gcd(int a,int b)
    {
        if(b==0)
        {
            return a;
        }
            return gcd(b,a%b);
    }

    public String toString()
    {
        return num+"/"+den;
    }
    public boolean equals(Object obj)
    {
        if(this==obj)
        {
            return true;
        }
        if(!(obj instanceof Fraction))
        {
             return false;
        }
        Fraction f=(Fraction)obj;
        return this.num == f.num && this.den == f.den;
    }
    
}