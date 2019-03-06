/*
 * The Hole class represents a hole in the game board.
 * Each hole contains some number of korgools
 * Contains methods to change the number of korgools in the holes,
 * and to get isTuz(boolean) and numberOfKorgools(int)
 * 
 * Vitesh Dav Soni (12/11/2018)
 */
public class Hole 
{
	private boolean isTuz; //true if it is a tuz and false if not
	private int numberOfKorgools;
	
	public Hole()
	{
		isTuz = false;
		numberOfKorgools = 9;
	}
	
	/*	Method to add an amount of korgools to the hole
	 *  @return number of korgools in the hole after the change
	 *  @param the number of korgools to add to the hole (can also be negative to subtract 
	 *   some number of korgools. 
	 */
	public int addKorgools(int numberOfKorgoolsToAdd)
	{
		numberOfKorgools = numberOfKorgools + numberOfKorgoolsToAdd;
		if(numberOfKorgools < 0)
		{
			numberOfKorgools = 0;
		}
		return numberOfKorgools;
	}
	
	/*	Method to change the amount of korgools in the hole
	 *  @return number of korgools in the hole after the change
	 *  @param the number of korgools this hole shoud have
	 */
	public int setNumberOfKorgools(int numberOfKorgoolsIn)
	{
		numberOfKorgools = numberOfKorgoolsIn;
		if(numberOfKorgools < 0)
		{
			numberOfKorgools = 0;
		}
		return numberOfKorgools;
	}
	
	/*	Method to return number of korgools in the hole
	 *  @return Returns the number of korgools in the hole
	 */
	public int getNumberOfKorgools()
	{
		return numberOfKorgools;
	}
	
	/*	Method to change if the hole is a tuz or not.
	 *  @return Returns true if it is a tuz and false if it is not.
	 */
	public boolean setTuz()
	{
		if(isTuz)
		{
			isTuz = false;
		}
		else
		{
			isTuz = true;
		}
		return isTuz;
	}
	
	/*	Method to return isTuz
	 *  @return Returns true if it is a tuz and false if it is not.
	 */
	public boolean getTuz()
	{
		return isTuz;
	}
	
}
