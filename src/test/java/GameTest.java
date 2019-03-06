

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  Yilei Liang
 */
public class GameTest
{
    public GameTest()
    {
    }

    @Test
    public void playerMoveTest()
    {
		Game aGame = new Game();
		Hole[] firstRound = aGame.round(3);
		assertEquals(1,firstRound[3].getNumberOfKorgools());
		assertEquals(10,firstRound[4].getNumberOfKorgools());
		assertEquals(0,firstRound[11].getNumberOfKorgools());
		
    }
	
	@Test
	public void aiMoveTest()
	{
		Game aGame = new Game(false); //Let the AI move first
		Hole[] firstRound = aGame.round(10); // This number is trivial 
		//HARD TO TEST DUE TO THE MOVE OF AI IS UNKNOWN
	}
	
	@Test
	public void playerAndAITest()
	{
		Game aGame = new Game();
		Hole[] firstRound = aGame.round(4);  //Player Move
		Hole[] secondRound = aGame.round(10); //AI Move
		//HARD TO TEST DUE TO THE MOVE OF AI IS UNKNOWN
	}
}
