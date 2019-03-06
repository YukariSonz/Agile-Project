import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
    Test class for Player class,

    @author Alexandros Doganis
    25/11/18
 */

public class PlayerTest {

    public boolean excepted = false;   // should not be true

    /**
     *  test player construction given side
     */
    @Test
    public void pConstructionTest() {

        Player p1 = new Player(true);
        Player p2 = new Player(false);

        assertEquals(true, p1.getSide());
        assertEquals(false, p2.getSide());
        assertEquals(0, p1.getScore());
        assertEquals(0, p2.getScore());
    }

    /**
     *  test player score manipulation
     */
    @Test
    public void pScoreTests() {

        Player p1 = new Player(true);
        Player p2 = new Player(false);

        p1.setScore(10);
        assertEquals(10, p1.getScore());

        p1.incrementScore(20);
        assertEquals(30, p1.getScore());
        assertEquals(0, p2.getScore());
    }

    /**
     *  test move functionality
     */
    @Test
    public void pMoveTest() {
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        Board board = new Board();

        try {
            assertEquals(1, p1.move(board, 8).getBoardState()[8].getNumberOfKorgools());
            assertEquals(1, p2.move(board, 17).getBoardState()[17].getNumberOfKorgools());
        }
        catch(InvalidMoveException e) {
            excepted = true;
        }
        assertEquals(false, excepted);

    }

}