import java.util.*;
class prac1_3
{
	enum Move {ROCK, PAPER, SCISSORS, LIZARD, SPOCK};
	public int winner(Move a,Move b)
	{
		if(a==b)
		{
			return 0;
		}
		switch(a){		
			case ROCK : if(Move.SCISSORS || Move.LIZARD)
					{
						return 1;
					}
				    else
					{
						return -1;
					}
					break;
			case PAPER : if(Move.ROCK || Move.SPOCK)
					{
						return 1;
					}
				    else
					{
						return -1;
					}
					break;
			case SCISSORS : if(Move.PAPER || Move.LIZARD)
					{
						return 1;
					}
				    else
					{
						return -1;
					}
					break;
			case LIZARD: if(Move.PAPER || Move.SPOCK)
					{
						return 1;
					}
				    else
					{
						return -1;
					}
					break;
			case SPOCK : if(Move.ROCK || Move.SCISSORS)
					{
						return 1;
					}
				    else
					{
						return -1;
					}
					break;
		}



	}
		
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		Random random=new Random();
		int playerScore = 0;
		int computerScore = 0;
		for(int i=1;i<=5;i++)
		{

		}
			

	}
}	