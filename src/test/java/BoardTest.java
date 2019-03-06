import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class BoardTest {

	private Board board;

	@Before
	public void initialise()
	{
		board = new Board();
	}
	
	@Test
	public void boardConstructorTest() {
		assertEquals(18, board.getBoardState().length);
		assertEquals(9, board.getBoardState()[6].getNumberOfKorgools());
		assertEquals(9, board.getBoardState()[17].getNumberOfKorgools());
		assertEquals(9, board.getBoardState()[0].getNumberOfKorgools());
		assertEquals(9, board.getBoardState()[9].getNumberOfKorgools());
	}
	
	@Test
	public void setBoardStateTest() {
		assertEquals(10, board.setBoardState(2, 10)[2].getNumberOfKorgools());
		board.setBoardState(0, -1);
		assertEquals(0, board.getBoardState()[0].getNumberOfKorgools());
		assertEquals(10, board.getBoardState()[2].getNumberOfKorgools());
		board.setBoardState(17, 10);
		assertEquals(10, board.getBoardState()[17].getNumberOfKorgools());
		board.setBoardState(17, -10);
		assertEquals(0, board.getBoardState()[17].getNumberOfKorgools());
	}
	@Test
	public void compareBoardTest() {
		Board b1 = new Board();
		Board b2 = new Board();
		assertEquals(true, b1.compareBoards(b2));
		b2.setBoardState(0, 10);
		assertEquals(false, b1.compareBoards(b2));
		b1.setBoardState(0, 10);
		assertEquals(true, b2.compareBoards(b1));
	}
	
	@Test
	public void copyBoardTest() {
		Board b1 = new Board();
		Board b2 = b1.copyBoard();
		assertEquals(true, b1.compareBoards(b2));
		assertEquals(true, b2.compareBoards(b1));
		b2.setBoardState(5, 6);
		assertEquals(false, b2.compareBoards(b1));
	}
}
