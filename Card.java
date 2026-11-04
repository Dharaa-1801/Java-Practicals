class Card
{
    private String rank;
     private String suit;

    Card(String rank,String suit)
    {
        this.rank=rank;
        this.suit=suit;
    }
      public String toString()
    {
       return rank + " of " + suit ;
    }
    public boolean equals(Object o)
    {
        if(this==o)
        {
            return true;
        }
        if(!(this instanceof Card))
        {
            return false;
        }
        Card c=(Card)o;
        if(this.rank.equals(c.rank) && this.suit.equals(c.suit))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}